package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.backoff

import com.rbkmoney.adapter.common.model.AdapterContext
import com.rbkmoney.adapter.common.model.PollingInfo
import com.rbkmoney.adapter.common.properties.CommonTimerProperties
import com.rbkmoney.adapter.common.utils.times.ExponentialBackOffPollingService
import com.rbkmoney.damsel.base.Timer
import com.rbkmoney.damsel.proxy_provider.FinishIntent
import com.rbkmoney.damsel.proxy_provider.FinishStatus
import com.rbkmoney.damsel.proxy_provider.Intent
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenFinishIntent
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenFinishStatus
import com.rbkmoney.damsel.proxy_provider.RecurrentTokenIntent
import com.rbkmoney.damsel.proxy_provider.SleepIntent
import com.rbkmoney.damsel.user_interaction.UserInteraction
import com.rbkmoney.error.mapping.ErrorMapping
import com.rbkmoney.java.damsel.constant.Error
import com.rbkmoney.java.damsel.utils.creators.ProxyProviderPackageCreators
import com.rbkmoney.java.damsel.utils.extractors.OptionsExtractors
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class SleepIntentHelper(
    private val exponentialBackOffPollingService: ExponentialBackOffPollingService<PollingInfo>,
    private val timerProperties: CommonTimerProperties,
    private val errorMapping: ErrorMapping,
) {

    @JvmOverloads
    fun build(
        adapterContext: AdapterContext,
        options: Map<String, String>,
        userInteraction: UserInteraction? = null,
    ): Intent {
        if (adapterContext.pollingInfo == null) {
            adapterContext.pollingInfo = PollingInfo()
        }
        if (adapterContext.pollingInfo.maxDateTimePolling == null) {
            adapterContext.pollingInfo.maxDateTimePolling = getMaxDateTimeInstant(options)
        }
        val isSleepTimeout = adapterContext
            .pollingInfo
            .maxDateTimePolling
            .isBefore(Instant.now())
        if (isSleepTimeout) {
            val failure = errorMapping.mapFailure(Error.SLEEP_TIMEOUT)
            return Intent.finish(FinishIntent(FinishStatus.failure(failure)))
        }
        if (adapterContext.pollingInfo.startDateTimePolling == null) {
            adapterContext.pollingInfo.startDateTimePolling = Instant.now()
        }
        val nextPollingInterval = exponentialBackOffPollingService.prepareNextPollingInterval(
            adapterContext.pollingInfo, options
        )

        return if (userInteraction != null) {
            ProxyProviderPackageCreators.createIntentWithSleepIntent(
                nextPollingInterval,
                userInteraction
            )
        } else {
            Intent.sleep(
                SleepIntent(
                    Timer(
                        Timer.timeout(nextPollingInterval)
                    )
                )
            )
        }
    }

    fun buildRecurrent(
        adapterContext: AdapterContext,
        options: Map<String, String>,
        userInteraction: UserInteraction?
    ): RecurrentTokenIntent {
        if (adapterContext.pollingInfo == null) {
            adapterContext.pollingInfo = PollingInfo()
        }
        if (adapterContext.pollingInfo.maxDateTimePolling == null) {
            adapterContext.pollingInfo.maxDateTimePolling = getMaxDateTimeInstant(options)
        }
        val isSleepTimeout = adapterContext
            .pollingInfo
            .maxDateTimePolling
            .isBefore(Instant.now())
        if (isSleepTimeout) {
            val failure = errorMapping.mapFailure(Error.SLEEP_TIMEOUT)
            return RecurrentTokenIntent.finish(RecurrentTokenFinishIntent(RecurrentTokenFinishStatus.failure(failure)))
        }
        if (adapterContext.pollingInfo.startDateTimePolling == null) {
            adapterContext.pollingInfo.startDateTimePolling = Instant.now()
        }
        val nextPollingInterval = exponentialBackOffPollingService.prepareNextPollingInterval(
            adapterContext.pollingInfo, options
        )
        return if (userInteraction != null) {
            RecurrentTokenIntent.sleep(
                ProxyProviderPackageCreators.createSleepIntent(
                    nextPollingInterval,
                    userInteraction
                )
            )
        } else RecurrentTokenIntent.sleep(
            ProxyProviderPackageCreators.createSleepIntent(
                Timer.timeout(
                    nextPollingInterval
                )
            )
        )
    }

    fun getMaxDateTimeInstant(entryStateOptions: Map<String, String>): Instant {
        val maxTimePolling = OptionsExtractors.extractMaxTimePolling(
            entryStateOptions,
            timerProperties.maxTimePolling
        )
        return Instant.now().plus(maxTimePolling.toLong(), ChronoUnit.SECONDS)
    }
}

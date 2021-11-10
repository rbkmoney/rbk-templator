package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.backoff;

import com.rbkmoney.adapter.common.model.AdapterContext;
import com.rbkmoney.adapter.common.model.PollingInfo;
import com.rbkmoney.adapter.common.properties.CommonTimerProperties;
import com.rbkmoney.adapter.common.utils.times.ExponentialBackOffPollingService;
import com.rbkmoney.damsel.base.Timer;
import com.rbkmoney.damsel.domain.Failure;
import com.rbkmoney.damsel.proxy_provider.*;
import com.rbkmoney.damsel.user_interaction.UserInteraction;
import com.rbkmoney.error.mapping.ErrorMapping;
import com.rbkmoney.java.damsel.constant.Error;
import com.rbkmoney.java.damsel.utils.creators.ProxyProviderPackageCreators;
import com.rbkmoney.java.damsel.utils.extractors.OptionsExtractors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static com.rbkmoney.java.damsel.utils.creators.ProxyProviderPackageCreators.createSleepIntent;

@Service
@RequiredArgsConstructor
public class SleepIntentHelper {

    private final ExponentialBackOffPollingService<PollingInfo> exponentialBackOffPollingService;

    private final CommonTimerProperties timerProperties;
    private final ErrorMapping errorMapping;

    public Intent build(AdapterContext adapterContext, Map<String, String> options) {
        return build(adapterContext, options, null);
    }

    public Intent build(AdapterContext adapterContext,
                        Map<String, String> options,
                        UserInteraction userInteraction) {
        if (adapterContext.getPollingInfo() == null) {
            adapterContext.setPollingInfo(new PollingInfo());
        }
        if (adapterContext.getPollingInfo().getMaxDateTimePolling() == null) {
            adapterContext.getPollingInfo().setMaxDateTimePolling(
                    getMaxDateTimeInstant(options));
        }

        boolean isSleepTimeout = adapterContext
                .getPollingInfo()
                .getMaxDateTimePolling()
                .isBefore(Instant.now());

        if (isSleepTimeout) {
            Failure failure = errorMapping.mapFailure(Error.SLEEP_TIMEOUT);

            return Intent.finish(new FinishIntent(FinishStatus.failure(failure)));
        }

        if (adapterContext.getPollingInfo().getStartDateTimePolling() == null) {
            adapterContext.getPollingInfo().setStartDateTimePolling(Instant.now());
        }

        int nextPollingInterval = exponentialBackOffPollingService.prepareNextPollingInterval(
                adapterContext.getPollingInfo(), options);

        if (userInteraction != null) {
            return ProxyProviderPackageCreators.createIntentWithSleepIntent(
                    nextPollingInterval,
                    userInteraction);
        }

        return Intent.sleep(new SleepIntent(new Timer(Timer.timeout(nextPollingInterval))));
    }

    public RecurrentTokenIntent buildRecurrent(AdapterContext adapterContext,
                                               Map<String, String> options,
                                               UserInteraction userInteraction) {
        if (adapterContext.getPollingInfo() == null) {
            adapterContext.setPollingInfo(new PollingInfo());
        }
        if (adapterContext.getPollingInfo().getMaxDateTimePolling() == null) {
            adapterContext.getPollingInfo().setMaxDateTimePolling(
                    getMaxDateTimeInstant(options));
        }

        boolean isSleepTimeout = adapterContext
                .getPollingInfo()
                .getMaxDateTimePolling()
                .isBefore(Instant.now());

        if (isSleepTimeout) {
            Failure failure = errorMapping.mapFailure(Error.SLEEP_TIMEOUT);

            return RecurrentTokenIntent.finish(
                    new RecurrentTokenFinishIntent(RecurrentTokenFinishStatus.failure(failure))
            );
        }

        if (adapterContext.getPollingInfo().getStartDateTimePolling() == null) {
            adapterContext.getPollingInfo().setStartDateTimePolling(Instant.now());
        }

        int nextPollingInterval = exponentialBackOffPollingService.prepareNextPollingInterval(
                adapterContext.getPollingInfo(), options);

        if (userInteraction != null) {
            return RecurrentTokenIntent.sleep(createSleepIntent(nextPollingInterval, userInteraction));
        }

        return RecurrentTokenIntent.sleep(createSleepIntent(Timer.timeout(nextPollingInterval)));
    }

    public Instant getMaxDateTimeInstant(Map<String, String> entryStateOptions) {
        int maxTimePolling = OptionsExtractors.extractMaxTimePolling(
                entryStateOptions,
                timerProperties.getMaxTimePolling());

        return Instant.now().plus(maxTimePolling, ChronoUnit.SECONDS);
    }

}

package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.flow

import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}model.CustomEntryStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.model.CustomExitStateModel
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.spring.boot.starter.flow.StepResolver
import com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}.spring.boot.starter.model.GeneralEntryStateModel
import com.rbkmoney.adapter.common.enums.Step
import com.rbkmoney.adapter.common.enums.TargetStatus
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.util.Objects

{{#if_eq doc true}}
/**
 * Здесь определяется следующий Step для работы адаптера на основе полей из Model.
 * Можно использовать статусы ответа 3ей стороны, какие-либо поля, на основании которых можно принять решение о
 * следующем шаге.
 *
 * @see com.rbkmoney.adapter.common.enums.Step
 */
{{/if_eq}}
@Component
class StepResolverImpl : StepResolver<CustomEntryStateModel, CustomExitStateModel> {

    private val log = KotlinLogging.logger {}

    override fun resolveEntry(entryStateModel: CustomEntryStateModel): Step {
        val targetStatus: TargetStatus = entryStateModel.targetStatus
        val step: Step = entryStateModel.adapterContext.step
        log.info("Entry resolver. Target status: {}. Step: {}", targetStatus, step)
        return when (targetStatus) {
            TargetStatus.PROCESSED -> {
                if (entryStateModel.recToken != null) {
                    Step.AUTH_RECURRENT
                } else Objects.requireNonNullElse(step, Step.PRE_AUTH)
            }
            TargetStatus.CAPTURED -> Step.CAPTURE
            TargetStatus.CANCELLED -> Objects.requireNonNullElse(step, Step.CANCEL)
            TargetStatus.REFUNDED -> Objects.requireNonNullElse(step, Step.REFUND)
            else -> throw IllegalStateException("Unknown target status: $targetStatus")
        }
    }

    override fun resolveExit(exitStateModel: CustomExitStateModel): Step {
        val entryStateModel: GeneralEntryStateModel = exitStateModel.generalEntryStateModel
        val step: Step = entryStateModel.adapterContext.step
        log.info("Exit resolver. Step: {}", step)
        return step
    }
}

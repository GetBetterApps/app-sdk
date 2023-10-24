package com.velkonost.getbetter.shared.core.vm.extension

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.vm.resource.Message
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.ResourceStringDesc
import dev.icerock.moko.resources.desc.StringDesc

inline fun <T : Any?> ResultState<T>.onFailureWithMsg(
    crossinline action: (throwable: Throwable?, errorMsg: Message?) -> Unit
): ResultState<T> {
    if (this is ResultState.Failure) {
        if (this.errorCode != null) {
            val message = Message.Builder()
                .id("error_code_message")
                .text(this.errorCode!!.asText)
                .messageType(MessageType.SnackBar.Builder().build())
                .build()

            action(this.throwable, message)
        } else {
            action(this.throwable, null)
        }
    }
    return this
}

val Int.asText: ResourceStringDesc
    get() {
        val resId = when (this) {
            901 -> SharedR.strings.error_901
            902 -> SharedR.strings.error_902
            903 -> SharedR.strings.error_903
            904 -> SharedR.strings.error_904
            905 -> SharedR.strings.error_905
            906 -> SharedR.strings.error_906
            1001 -> SharedR.strings.error_1001

            801 -> SharedR.strings.error_801
            802 -> SharedR.strings.error_802
            803 -> SharedR.strings.error_803
            804 -> SharedR.strings.error_804
            805 -> SharedR.strings.error_805
            806 -> SharedR.strings.error_806
            807 -> SharedR.strings.error_807
            808 -> SharedR.strings.error_808
            809 -> SharedR.strings.error_809
            810 -> SharedR.strings.error_810
            811 -> SharedR.strings.error_811
            812 -> SharedR.strings.error_812
            813 -> SharedR.strings.error_813
            else -> SharedR.strings.error_default
        }

        return StringDesc.Resource(resId)
    }
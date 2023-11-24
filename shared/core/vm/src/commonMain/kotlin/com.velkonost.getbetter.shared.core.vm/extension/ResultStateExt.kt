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
            907 -> SharedR.strings.error_907
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
            814 -> SharedR.strings.error_814

            699 -> SharedR.strings.error_699
            700 -> SharedR.strings.error_700
            701 -> SharedR.strings.error_701
            702 -> SharedR.strings.error_702
            703 -> SharedR.strings.error_703
            704 -> SharedR.strings.error_704
            705 -> SharedR.strings.error_705
            706 -> SharedR.strings.error_706
            707 -> SharedR.strings.error_707
            708 -> SharedR.strings.error_708
            709 -> SharedR.strings.error_709
            710 -> SharedR.strings.error_710
            711 -> SharedR.strings.error_711
            712 -> SharedR.strings.error_712
            713 -> SharedR.strings.error_713
            714 -> SharedR.strings.error_714
            715 -> SharedR.strings.error_715
            716 -> SharedR.strings.error_716
            717 -> SharedR.strings.error_717
            718 -> SharedR.strings.error_718
            719 -> SharedR.strings.error_719

            600 -> SharedR.strings.error_600
            601 -> SharedR.strings.error_601
            602 -> SharedR.strings.error_602
            603 -> SharedR.strings.error_603
            604 -> SharedR.strings.error_604
            605 -> SharedR.strings.error_605
            606 -> SharedR.strings.error_606
            607 -> SharedR.strings.error_607
            608 -> SharedR.strings.error_608

            650 -> SharedR.strings.error_650
            651 -> SharedR.strings.error_651
            652 -> SharedR.strings.error_652
            653 -> SharedR.strings.error_653
            654 -> SharedR.strings.error_654

            500 -> SharedR.strings.error_500
            501 -> SharedR.strings.error_501
            502 -> SharedR.strings.error_502

            550 -> SharedR.strings.error_550
            551 -> SharedR.strings.error_551
            552 -> SharedR.strings.error_552
            553 -> SharedR.strings.error_553
            554 -> SharedR.strings.error_554

            else -> SharedR.strings.error_default
        }

        return StringDesc.Resource(resId)
    }
package com.velkonost.getbetter.shared.core.model

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource

enum class Emoji(
    val id: Int,
    val icon: ImageResource
) {

    _1(
        id = 1,
        icon = SharedR.images.emoji_1
    ),

    _2(
        id = 2,
        icon = SharedR.images.emoji_2
    ),

    _3(
        id = 3,
        icon = SharedR.images.emoji_3
    ),

    _4(
        id = 4,
        icon = SharedR.images.emoji_4
    ),

    _5(
        id = 5,
        icon = SharedR.images.emoji_5
    ),

    _6(
        id = 6,
        icon = SharedR.images.emoji_6
    ),

    _7(
        id = 7,
        icon = SharedR.images.emoji_7
    ),

    _8(
        id = 8,
        icon = SharedR.images.emoji_8
    ),

    _9(
        id = 9,
        icon = SharedR.images.emoji_9
    ),

    _10(
        id = 10,
        icon = SharedR.images.emoji_10
    ),

    _11(
        id = 11,
        icon = SharedR.images.emoji_11
    ),

    _12(
        id = 12,
        icon = SharedR.images.emoji_12
    ),

    _13(
        id = 13,
        icon = SharedR.images.emoji_13
    ),

    _14(
        id = 14,
        icon = SharedR.images.emoji_14
    ),

    _15(
        id = 15,
        icon = SharedR.images.emoji_15
    ),

    _16(
        id = 16,
        icon = SharedR.images.emoji_16
    ),

    _17(
        id = 17,
        icon = SharedR.images.emoji_17
    ),

    _18(
        id = 18,
        icon = SharedR.images.emoji_18
    ),

    _19(
        id = 19,
        icon = SharedR.images.emoji_19
    ),

    _20(
        id = 20,
        icon = SharedR.images.emoji_20
    ),

    _21(
        id = 21,
        icon = SharedR.images.emoji_21
    ),

    _22(
        id = 22,
        icon = SharedR.images.emoji_22
    ),

    _23(
        id = 23,
        icon = SharedR.images.emoji_23
    ),

    _24(
        id = 24,
        icon = SharedR.images.emoji_24
    ),

    _25(
        id = 25,
        icon = SharedR.images.emoji_25
    ),

    _26(
        id = 26,
        icon = SharedR.images.emoji_26
    ),

    _27(
        id = 27,
        icon = SharedR.images.emoji_27
    ),

    _28(
        id = 28,
        icon = SharedR.images.emoji_28
    ),

    _29(
        id = 29,
        icon = SharedR.images.emoji_29
    ),

    _30(
        id = 30,
        icon = SharedR.images.emoji_30
    ),

    _31(
        id = 31,
        icon = SharedR.images.emoji_31
    ),

    _32(
        id = 32,
        icon = SharedR.images.emoji_32
    ),

    _33(
        id = 33,
        icon = SharedR.images.emoji_33
    ),

    _34(
        id = 34,
        icon = SharedR.images.emoji_34
    ),

    _35(
        id = 35,
        icon = SharedR.images.emoji_35
    ),

    _36(
        id = 36,
        icon = SharedR.images.emoji_36
    ),

    _37(
        id = 37,
        icon = SharedR.images.emoji_37
    ),

    _38(
        id = 38,
        icon = SharedR.images.emoji_38
    ),

    _39(
        id = 39,
        icon = SharedR.images.emoji_39
    ),

    _40(
        id = 40,
        icon = SharedR.images.emoji_40
    ),

    _41(
        id = 41,
        icon = SharedR.images.emoji_41
    ),

    _42(
        id = 42,
        icon = SharedR.images.emoji_42
    ),

    _43(
        id = 43,
        icon = SharedR.images.emoji_43
    ),

    _44(
        id = 44,
        icon = SharedR.images.emoji_44
    ),

    _45(
        id = 45,
        icon = SharedR.images.emoji_45
    ),

    _46(
        id = 46,
        icon = SharedR.images.emoji_46
    ),

    _47(
        id = 47,
        icon = SharedR.images.emoji_47
    ),

    _48(
        id = 48,
        icon = SharedR.images.emoji_48
    ),

    _49(
        id = 49,
        icon = SharedR.images.emoji_49
    ),

    _50(
        id = 50,
        icon = SharedR.images.emoji_50
    ),

    _51(
        id = 51,
        icon = SharedR.images.emoji_51
    ),

    _52(
        id = 52,
        icon = SharedR.images.emoji_52
    ),

    _53(
        id = 53,
        icon = SharedR.images.emoji_53
    ),

    _54(
        id = 54,
        icon = SharedR.images.emoji_54
    ),

    _55(
        id = 55,
        icon = SharedR.images.emoji_55
    ),

    _56(
        id = 56,
        icon = SharedR.images.emoji_56
    ),

    _57(
        id = 57,
        icon = SharedR.images.emoji_57
    ),

    _58(
        id = 58,
        icon = SharedR.images.emoji_58
    ),

    _59(
        id = 59,
        icon = SharedR.images.emoji_59
    ),

    _60(
        id = 60,
        icon = SharedR.images.emoji_60
    ),

    _61(
        id = 61,
        icon = SharedR.images.emoji_61
    ),

    _62(
        id = 62,
        icon = SharedR.images.emoji_62
    ),

    _63(
        id = 63,
        icon = SharedR.images.emoji_63
    ),

    _64(
        id = 64,
        icon = SharedR.images.emoji_64
    ),

    _65(
        id = 65,
        icon = SharedR.images.emoji_65
    ),

    _66(
        id = 66,
        icon = SharedR.images.emoji_66
    ),

    _67(
        id = 67,
        icon = SharedR.images.emoji_67
    ),

    _68(
        id = 68,
        icon = SharedR.images.emoji_68
    );

    companion object {
        fun getIconById(id: Int): ImageResource {
            return Emoji.values().first {
                it.id == id
            }.icon
        }

        fun getById(id: Int): Emoji {
            return Emoji.values().first {
                it.id == id
            }
        }


    }
}
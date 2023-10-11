package com.velkonost.getbetter.shared.core.model

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource

enum class Emoji(
    val id: Int,
    val icon: ImageResource
) {

    Emoji1(
        id = 1,
        icon = SharedR.images.emoji_1
    ),

    Emoji2(
        id = 2,
        icon = SharedR.images.emoji_2
    ),

    Emoji3(
        id = 3,
        icon = SharedR.images.emoji_3
    ),

    Emoji4(
        id = 4,
        icon = SharedR.images.emoji_4
    ),

    Emoji5(
        id = 5,
        icon = SharedR.images.emoji_5
    ),

    Emoji6(
        id = 6,
        icon = SharedR.images.emoji_6
    ),

    Emoji7(
        id = 7,
        icon = SharedR.images.emoji_7
    ),

    Emoji8(
        id = 8,
        icon = SharedR.images.emoji_8
    ),

    Emoji9(
        id = 9,
        icon = SharedR.images.emoji_9
    ),

    Emoji10(
        id = 10,
        icon = SharedR.images.emoji_10
    ),

    Emoji11(
        id = 11,
        icon = SharedR.images.emoji_11
    ),

    Emoji12(
        id = 12,
        icon = SharedR.images.emoji_12
    ),

    Emoji13(
        id = 13,
        icon = SharedR.images.emoji_13
    ),

    Emoji14(
        id = 14,
        icon = SharedR.images.emoji_14
    ),

    Emoji15(
        id = 15,
        icon = SharedR.images.emoji_15
    ),

    Emoji16(
        id = 16,
        icon = SharedR.images.emoji_16
    ),

    Emoji17(
        id = 17,
        icon = SharedR.images.emoji_17
    ),

    Emoji18(
        id = 18,
        icon = SharedR.images.emoji_18
    ),

    Emoji19(
        id = 19,
        icon = SharedR.images.emoji_19
    ),

    Emoji20(
        id = 20,
        icon = SharedR.images.emoji_20
    ),

    Emoji21(
        id = 21,
        icon = SharedR.images.emoji_21
    ),

    Emoji22(
        id = 22,
        icon = SharedR.images.emoji_22
    ),

    Emoji23(
        id = 23,
        icon = SharedR.images.emoji_23
    ),

    Emoji24(
        id = 24,
        icon = SharedR.images.emoji_24
    ),

    Emoji25(
        id = 25,
        icon = SharedR.images.emoji_25
    ),

    Emoji26(
        id = 26,
        icon = SharedR.images.emoji_26
    ),

    Emoji27(
        id = 27,
        icon = SharedR.images.emoji_27
    ),

    Emoji28(
        id = 28,
        icon = SharedR.images.emoji_28
    ),

    Emoji29(
        id = 29,
        icon = SharedR.images.emoji_29
    ),

    Emoji30(
        id = 30,
        icon = SharedR.images.emoji_30
    ),

    Emoji31(
        id = 31,
        icon = SharedR.images.emoji_31
    ),

    Emoji32(
        id = 32,
        icon = SharedR.images.emoji_32
    ),

    Emoji33(
        id = 33,
        icon = SharedR.images.emoji_33
    ),

    Emoji34(
        id = 34,
        icon = SharedR.images.emoji_34
    ),

    Emoji35(
        id = 35,
        icon = SharedR.images.emoji_35
    ),

    Emoji36(
        id = 36,
        icon = SharedR.images.emoji_36
    ),

    Emoji37(
        id = 37,
        icon = SharedR.images.emoji_37
    ),

    Emoji38(
        id = 38,
        icon = SharedR.images.emoji_38
    ),

    Emoji39(
        id = 39,
        icon = SharedR.images.emoji_39
    ),

    Emoji40(
        id = 40,
        icon = SharedR.images.emoji_40
    ),

    Emoji41(
        id = 41,
        icon = SharedR.images.emoji_41
    ),

    Emoji42(
        id = 42,
        icon = SharedR.images.emoji_42
    ),

    Emoji43(
        id = 43,
        icon = SharedR.images.emoji_43
    ),

    Emoji44(
        id = 44,
        icon = SharedR.images.emoji_44
    ),

    Emoji45(
        id = 45,
        icon = SharedR.images.emoji_45
    ),

    Emoji46(
        id = 46,
        icon = SharedR.images.emoji_46
    ),

    Emoji47(
        id = 47,
        icon = SharedR.images.emoji_47
    ),

    Emoji48(
        id = 48,
        icon = SharedR.images.emoji_48
    ),

    Emoji49(
        id = 49,
        icon = SharedR.images.emoji_49
    ),

    Emoji50(
        id = 50,
        icon = SharedR.images.emoji_50
    ),

    Emoji51(
        id = 51,
        icon = SharedR.images.emoji_51
    ),

    Emoji52(
        id = 52,
        icon = SharedR.images.emoji_52
    ),

    Emoji53(
        id = 53,
        icon = SharedR.images.emoji_53
    ),

    Emoji54(
        id = 54,
        icon = SharedR.images.emoji_54
    ),

    Emoji55(
        id = 55,
        icon = SharedR.images.emoji_55
    ),

    Emoji56(
        id = 56,
        icon = SharedR.images.emoji_56
    ),

    Emoji57(
        id = 57,
        icon = SharedR.images.emoji_57
    ),

    Emoji58(
        id = 58,
        icon = SharedR.images.emoji_58
    ),

    Emoji59(
        id = 59,
        icon = SharedR.images.emoji_59
    ),

    Emoji60(
        id = 60,
        icon = SharedR.images.emoji_60
    ),

    Emoji61(
        id = 61,
        icon = SharedR.images.emoji_61
    ),

    Emoji62(
        id = 62,
        icon = SharedR.images.emoji_62
    ),

    Emoji63(
        id = 63,
        icon = SharedR.images.emoji_63
    ),

    Emoji64(
        id = 64,
        icon = SharedR.images.emoji_64
    ),

    Emoji65(
        id = 65,
        icon = SharedR.images.emoji_65
    ),

    Emoji66(
        id = 66,
        icon = SharedR.images.emoji_66
    ),

    Emoji67(
        id = 67,
        icon = SharedR.images.emoji_67
    ),

    Emoji68(
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
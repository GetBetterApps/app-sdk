package com.velkonost.getbetter.shared.features.presentation.model

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class WisdomType(
    val title: StringDesc,
    val description: StringDesc,
    val backgroundImage: ImageResource
) {
    Affirmation(
        title = StringDesc.Resource(SharedR.strings.wisdom_affirmations_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_affirmations_desc),
        backgroundImage = SharedR.images.ic_wisdom_1
    ),

    Advices(
        title = StringDesc.Resource(SharedR.strings.wisdom_advices_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_advices_desc),
        backgroundImage = SharedR.images.ic_wisdom_2
    ),

    PsychologyOfSuccess(
        title = StringDesc.Resource(SharedR.strings.wisdom_psychology_of_success_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_psychology_of_success_desc),
        backgroundImage = SharedR.images.ic_wisdom_3
    ),

    Motivation(
        title = StringDesc.Resource(SharedR.strings.wisdom_motivation_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_motivation_desc),
        backgroundImage = SharedR.images.ic_wisdom_4
    ),

    Education(
        title = StringDesc.Resource(SharedR.strings.wisdom_education_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_education_desc),
        backgroundImage = SharedR.images.ic_wisdom_5
    ),

    PsychologyOfHappiness(
        title = StringDesc.Resource(SharedR.strings.wisdom_psychology_of_happiness_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_psychology_of_happiness_desc),
        backgroundImage = SharedR.images.ic_wisdom_6
    ),

    Plans(
        title = StringDesc.Resource(SharedR.strings.wisdom_plans_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_plans_desc),
        backgroundImage = SharedR.images.ic_wisdom_7
    ),

    Emotions(
        title = StringDesc.Resource(SharedR.strings.wisdom_emotions_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_emotions_desc),
        backgroundImage = SharedR.images.ic_wisdom_8
    ),

    SelfDiscipline(
        title = StringDesc.Resource(SharedR.strings.wisdom_self_discipline_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_self_discipline_desc),
        backgroundImage = SharedR.images.ic_wisdom_9
    ),

    Sport(
        title = StringDesc.Resource(SharedR.strings.wisdom_sport_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_sport_desc),
        backgroundImage = SharedR.images.ic_wisdom_10
    ),

    Creativity(
        title = StringDesc.Resource(SharedR.strings.wisdom_creativity_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_creativity_desc),
        backgroundImage = SharedR.images.ic_wisdom_11
    ),

    SelfAcceptance(
        title = StringDesc.Resource(SharedR.strings.wisdom_self_acceptance_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_self_acceptance_desc),
        backgroundImage = SharedR.images.ic_wisdom_12
    ),

    Family(
        title = StringDesc.Resource(SharedR.strings.wisdom_family_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_family_desc),
        backgroundImage = SharedR.images.ic_wisdom_13
    ),

    Finance(
        title = StringDesc.Resource(SharedR.strings.wisdom_finance_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_finance_desc),
        backgroundImage = SharedR.images.ic_wisdom_14
    ),

    PersonalEfficiency(
        title = StringDesc.Resource(SharedR.strings.wisdom_personal_efficiency_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_personal_efficiency_desc),
        backgroundImage = SharedR.images.ic_wisdom_15
    ),

    SelfReflection(
        title = StringDesc.Resource(SharedR.strings.wisdom_self_reflection_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_self_reflection_desc),
        backgroundImage = SharedR.images.ic_wisdom_16
    ),

    Meditation(
        title = StringDesc.Resource(SharedR.strings.wisdom_meditation_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_meditation_desc),
        backgroundImage = SharedR.images.ic_wisdom_17
    ),

    Carrier(
        title = StringDesc.Resource(SharedR.strings.wisdom_carrier_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_carrier_desc),
        backgroundImage = SharedR.images.ic_wisdom_18
    ),

    Social(
        title = StringDesc.Resource(SharedR.strings.wisdom_social_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_social_desc),
        backgroundImage = SharedR.images.ic_wisdom_19
    ),

    PersonalityTypes(
        title = StringDesc.Resource(SharedR.strings.wisdom_personality_types_title),
        description = StringDesc.Resource(SharedR.strings.wisdom_personality_types_desc),
        backgroundImage = SharedR.images.ic_wisdom_20
    )

}
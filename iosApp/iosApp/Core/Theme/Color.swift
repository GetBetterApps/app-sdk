//
//  Color.swift
//  iosApp
//
//  Created by velkonost on 10.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

public extension SwiftUI.Color {
    
    static let buttonGradientStart = SwiftUI.Color(SharedR.colors.shared.button_gradient_start.getUIColor())
    static let buttonGradientEnd = SwiftUI.Color(SharedR.colors.shared.button_gradient_end.getUIColor())
    
    static let buttonDisabled = SwiftUI.Color(SharedR.colors.shared.button_disabled_color.getUIColor())
    
    static let mainBackground = SwiftUI.Color(SharedR.colors.shared.main_background.getUIColor())
    static let textFieldBackground = SwiftUI.Color(SharedR.colors.shared.text_field_background.getUIColor())
    
    static let onboardingBackgroundGradientStart = SwiftUI.Color(SharedR.colors.shared.onboarding_background_gradient_start.getUIColor())
    static let onboardingBackgroundGradientEnd = SwiftUI.Color(SharedR.colors.shared.onboarding_background_gradient_end.getUIColor())
    
    static let onboardingHintColor = SwiftUI.Color(SharedR.colors.shared.onboarding_hint_color.getUIColor())
    static let onboardingTextField = SwiftUI.Color(SharedR.colors.shared.onboarding_text_field.getUIColor())
    
    static let backgroundItem = SwiftUI.Color(SharedR.colors.shared.background_item.getUIColor())
    static let backgroundIcon = SwiftUI.Color(SharedR.colors.shared.background_icon.getUIColor())
    
    static let hintColor = SwiftUI.Color(SharedR.colors.shared.hint_color.getUIColor())
    
    static let textLight = SwiftUI.Color(SharedR.colors.shared.text_light.getUIColor())
    static let textTitle = SwiftUI.Color(SharedR.colors.shared.text_title.getUIColor())
    static let textRegularTitle = SwiftUI.Color(SharedR.colors.shared.text_regular_title.getUIColor())
    static let textSecondaryTitle = SwiftUI.Color(SharedR.colors.shared.text_secondary.getUIColor())
    static let textPrimary = SwiftUI.Color(SharedR.colors.shared.text_primary.getUIColor())
    
    static let textButtonDisabled = SwiftUI.Color(SharedR.colors.shared.text_button_disabled.getUIColor())
    static let textButtonEnabled = SwiftUI.Color(SharedR.colors.shared.text_button_enabled.getUIColor())
    
    static let textUnimportantColor = SwiftUI.Color(SharedR.colors.shared.text_unimportant_color.getUIColor())
    
    static let iconActive = SwiftUI.Color(SharedR.colors.shared.icon_active.getUIColor())
    static let iconInactive = SwiftUI.Color(SharedR.colors.shared.icon_inactive.getUIColor())
    
    static let green = SwiftUI.Color(SharedR.colors.shared.green.getUIColor())
    static let red = SwiftUI.Color(SharedR.colors.shared.red.getUIColor())
}


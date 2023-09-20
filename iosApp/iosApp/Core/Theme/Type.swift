//
//  Type.swift
//  iosApp
//
//  Created by velkonost on 10.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

public extension Font {

    static let displayLarge = Font(SharedR.fontsSfpro.shared.rounded_regular.uiFont(withSize: 57))
    static let displayMedium = Font(SharedR.fontsSfpro.shared.rounded_regular.uiFont(withSize: 45))
    static let displaySmall = Font(SharedR.fontsSfpro.shared.rounded_regular.uiFont(withSize: 36))

    static let headlineLarge = Font(SharedR.fontsSfpro.shared.rounded_regular.uiFont(withSize: 32))
    static let headlineMedium = Font(SharedR.fontsSfpro.shared.rounded_regular.uiFont(withSize: 28))
    static let headlineSmall = Font(SharedR.fontsSfpro.shared.rounded_regular.uiFont(withSize: 24))

    static let titleLarge = Font(SharedR.fontsSfpro.shared.rounded_black.uiFont(withSize: 22))
    static let titleMedium = Font(SharedR.fontsSfpro.shared.rounded_bold.uiFont(withSize: 16))
    static let titleSmall = Font(SharedR.fontsSfpro.shared.rounded_bold.uiFont(withSize: 14))

    static let labelLarge = Font(SharedR.fontsSfpro.shared.rounded_semibold.uiFont(withSize: 16))
    static let labelMedium = Font(SharedR.fontsSfpro.shared.rounded_semibold.uiFont(withSize: 14))
    static let labelSmall = Font(SharedR.fontsSfpro.shared.rounded_semibold.uiFont(withSize: 11))

    static let bodyLarge = Font(SharedR.fontsSfpro.shared.rounded_semibold.uiFont(withSize: 16))
    static let bodyMedium = Font(SharedR.fontsSfpro.shared.rounded_regular.uiFont(withSize: 14))
    static let bodySmall = Font(SharedR.fontsSfpro.shared.rounded_regular.uiFont(withSize: 12))

}

public struct TextStyle {
    let font: FontResource
    let tracking: CGFloat
    let lineSpacing: CGFloat
    let size: CGFloat
    
    fileprivate init(_ font: FontResource, _ tracking: CGFloat, _ lineSpacing: CGFloat, _ size: CGFloat) {
        self.font = font
        self.tracking = tracking
        self.lineSpacing = lineSpacing
        self.size = size
    }
}

public extension TextStyle {
    
    static let displayLarge = TextStyle(SharedR.fontsSfpro.shared.rounded_bold, -2.5, 0, 57)
    static let displayMedium = TextStyle(SharedR.fontsSfpro.shared.rounded_regular, -2, 0, 45)
    static let displaySmall = TextStyle(SharedR.fontsSfpro.shared.rounded_regular, -1.5, 0, 36)
    
    static let headlineLarge = TextStyle(SharedR.fontsSfpro.shared.rounded_regular, -1, 0, 32)
    static let headlineMedium = TextStyle(SharedR.fontsSfpro.shared.rounded_regular, -0.5, 0, 28)
    static let headlineSmall = TextStyle(SharedR.fontsSfpro.shared.rounded_regular, -0.75, 0, 24)
    
    static let titleLarge = TextStyle(SharedR.fontsSfpro.shared.rounded_black, -0.1, 0, 22)
    static let titleMedium = TextStyle(SharedR.fontsSfpro.shared.rounded_bold, 0, 0, 16)
    static let titleSmall = TextStyle(SharedR.fontsSfpro.shared.rounded_semibold, 0.1, 0, 14)
    
    static let labelLarge = TextStyle(SharedR.fontsSfpro.shared.rounded_semibold, 0.1, 0, 16)
    static let labelMedium = TextStyle(SharedR.fontsSfpro.shared.rounded_semibold, 0.5, 0, 14)
    static let labelSmall = TextStyle(SharedR.fontsSfpro.shared.rounded_semibold, 0.5, 0, 11)
    
    static let bodyLarge = TextStyle(SharedR.fontsSfpro.shared.rounded_semibold, 0.5, 4, 16)
    static let bodyMedium = TextStyle(SharedR.fontsSfpro.shared.rounded_regular, 0.25, 3, 14)
    static let bodySmall = TextStyle(SharedR.fontsSfpro.shared.rounded_regular, 0.4, 2, 12)
}

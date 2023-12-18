//
//  OnboardingFifthStep.swift
//  iosApp
//
//  Created by velkonost on 18.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct OnboardingFifthStep: View {
    
    private let item: Affirmation
    private let text: String
    
    private let isActive: Bool
    private let isScaled: Bool
    private let isTextVisible: Bool
    
    init(item: Affirmation, isActive: Bool, text: String) {
        self.item = item
        self.text = text
        self.isActive = isActive
        self.isScaled = isActive
        self.isTextVisible = isActive
    }
    
    @State var blurRadius: CGFloat = 20
    
    var body: some View {
        ZStack {
            VStack {
                Spacer()
                OnboardingAffirmationText(text: text)
                    .opacity(isTextVisible ? 1 : 0)
                    .animation(.easeInOut.speed(0.5), value: isTextVisible)
                Spacer()
            }
        }
        .contentShape(Rectangle())
        .frame(width: UIScreen.screenWidth, height: UIScreen.screenHeight)
    }
}

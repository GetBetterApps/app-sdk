//
//  AppButton.swift
//  iosApp
//
//  Created by velkonost on 10.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AppButton: View {
    
    private let labelText: String
    private let isLoading: Bool
    private let onClick: () -> Void
    
    init(labelText: String, isLoading: Bool, onClick: @escaping () -> Void) {
        self.labelText = labelText
        self.isLoading = isLoading
        self.onClick = onClick
    }
    
    var body: some View {
        Button {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onClick()
        } label: {
            if (isLoading) {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: Color.textButtonEnabled))
                    .frame(
                        width: UIScreen.screenWidth * 0.8,
                        height: 42,
                        alignment: .center
                    )
                    .background(
                        RoundedRectangle(cornerRadius: 48)
                            .fill(
                                LinearGradient(
                                    colors: [.onboardingBackgroundGradientStart, .onboardingBackgroundGradientEnd],
                                    startPoint: .trailing, endPoint: .leading
                                )
                            )
                            .shadow(radius: 8)
                    )
            } else {
                Text(labelText.uppercased())
                    .foregroundColor(.textLight)
                    .style(.titleMedium)
                    .frame(
                        width: UIScreen.screenWidth * 0.8,
                        height: 42,
                        alignment: .center
                    )
                    .background(
                        RoundedRectangle(cornerRadius: 48)
                            .fill(
                                LinearGradient(
                                    colors: [.onboardingBackgroundGradientStart, .onboardingBackgroundGradientEnd],
                                    startPoint: .trailing, endPoint: .leading
                                )
                            )
                            .shadow(radius: 8)
                    )
                    .transition(.opacity)
                    .id("appButton")
            }
                
        }
        .disabled(isLoading)
        
    }
}


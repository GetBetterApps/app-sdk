//
//  OnboardingAbilityItem.swift
//  iosApp
//
//  Created by velkonost on 18.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct OnboardingAbilityItem: View {
    
    private let item: Ability
    
    init(item: Ability) {
        self.item = item
    }
    
    var body: some View {
        PrimaryBox {
            VStack(spacing: 0) {
                HStack {
                    Text(item.name)
                        .style(.titleMedium)
                        .foregroundColor(.textRegularTitle)
                    Spacer()
                }
                
                HStack {
                    Text(item.description_)
                        .style(.labelMedium)
                        .lineLimit(3)
                        .foregroundColor(.textPrimary)
                        .padding(.top, 12)
                    Spacer()
                }
                
                HStack {
                    Text(SharedR.strings().experience_your_title.desc().localized())
                        .style(.bodyMedium)
                        .foregroundColor(.textPrimary)
                    Spacer()
                    Text(item.experienceData.currentLevelStr.localized())
                        .style(.bodyMedium)
                        .foregroundColor(.textPrimary)
                }
                .padding(.top, 12)
                
                ProgressView(value: Double(item.experienceData.remainExperiencePercent))
                    .padding(.top, 6)
                    .accentColor(.buttonGradientStart)
                
            }
        }
    }
}

//
//  AbilityItem.swift
//  iosApp
//
//  Created by velkonost on 09.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AbilityItem: View {
    
    let item: Ability
    let onClick: (Ability) -> Void
    
    init(item: Ability, onClick: @escaping (Ability) -> Void) {
        self.item = item
        self.onClick = onClick
    }
    
    var body: some View {
        PrimaryBox {
            VStack(spacing: 0) {
                
                HStack {
                    Text(item.name)
                        .style(.titleMedium)
                        .foregroundColor(.textLight)
                    Spacer()
                }
                
                HStack {
                    Text(item.description_)
                        .style(.labelMedium)
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
            .contentShape(Rectangle())
            .onTapGesture {
                onClick(item)
            }
        }
        
    }
}

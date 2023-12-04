//
//  AbilityData.swift
//  iosApp
//
//  Created by velkonost on 04.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AbilityData : View {
    
    private let item: Ability
    private let onClick: (Ability) -> Void
    
    init(item: Ability, onClick: @escaping (Ability) -> Void) {
        self.item = item
        self.onClick = onClick
    }
    
    var body: some View {
        PrimaryBox {
            VStack(spacing: 0) {
                HStack {
                    Text(item.name)
                        .style(.labelMedium)
                        .foregroundColor(.textPrimary)
                    Spacer()
                    Text(item.experienceData.currentLevelStr.localized())
                        .style(.labelMedium)
                        .foregroundColor(.textPrimary)
                }
                
                ProgressView(value: Double(item.experienceData.remainExperiencePercent))
                    .padding(.top, 6)
                    .accentColor(.buttonGradientStart)
                    
            }
        }
    }
}

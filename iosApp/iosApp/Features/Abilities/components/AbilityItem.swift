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
    let isFavorite: Bool
    
    init(item: Ability, onClick: @escaping (Ability) -> Void) {
        self.item = item
        self.onClick = onClick
        self.isFavorite = item is FavoriteAbility
    }
    
    var body: some View {
        PrimaryBox(isBright: isFavorite) {
            VStack(spacing: 0) {
                HStack {
                    Text(isFavorite ? SharedR.strings().ability_favorite_title.desc().localized() : item.name)
                        .style(.titleMedium)
                        .foregroundColor(isFavorite ? .textLight : .textRegularTitle)
                    Spacer()
                }
                
                HStack {
                    Text(isFavorite ? SharedR.strings().ability_favorite_description.desc().localized() : item.description_)
                        .style(.labelMedium)
                        .foregroundColor(isFavorite ? .textLight : .textPrimary)
                        .padding(.top, 12)
                    Spacer()
                }
                
                if !isFavorite {
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
            .contentShape(Rectangle())
            .onTapGesture {
                onClick(item)
            }
        }
        
    }
}

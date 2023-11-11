//
//  LevelBlock.swift
//  iosApp
//
//  Created by velkonost on 09.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct LevelBlock: View {
    
    private let experienceData: ExperienceData
    private let topPadding: CGFloat
    
    init(experienceData: ExperienceData, topPadding: Int = 20) {
        self.experienceData = experienceData
        self.topPadding = CGFloat(topPadding)
    }
    
    var body: some View {
        
        PrimaryBox {
            VStack(spacing: 0) {
                HStack {
                    Text(SharedR.strings().experience_your_title.desc().localized())
                        .style(.labelMedium)
                        .foregroundColor(.textPrimary)
                    Spacer()
                    Text(experienceData.currentLevelStr.localized())
                        .style(.labelMedium)
                        .foregroundColor(.textPrimary)
                }
                
                ProgressView(value: Double(experienceData.remainExperiencePercent))
                    .padding(.top, 6)
                    .accentColor(.buttonGradientStart)
                    
            }
        }
        .padding(.top, topPadding)
    }
}

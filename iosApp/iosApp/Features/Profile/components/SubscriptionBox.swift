//
//  SubscriptionBox.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct SubscriptionBox: View {
    
    private let subscriptionPlan: String
    private let buttonText: String
    private let onUpgradeClick: () -> Void
    
    init(
        subscriptionPlan: String,
        buttonText: String,
        onUpgradeClick: @escaping () -> Void
    ) {
        self.subscriptionPlan = subscriptionPlan
        self.buttonText = buttonText
        self.onUpgradeClick = onUpgradeClick
    }
    
    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(subscriptionPlan)
                    .style(.titleMedium)
                    .foregroundColor(.textTitle)
                    .padding(.init(top: .zero, leading: .zero, bottom: 2, trailing: .zero))
                    
                Text(SharedR.strings().profile_sub_active_plan.desc().localized().uppercased())
                    .style(.labelSmall)
                    .foregroundColor(.textSecondaryTitle)
                    .padding(.init(top: 2, leading: .zero, bottom: .zero, trailing: .zero))
            }
            
            Spacer()
            
            VStack {
                Spacer()
                Text(buttonText.uppercased())
                    .style(.titleMedium, withSize: 14)
                    .foregroundColor(.textLight)
                    .padding(.init(top: 6, leading: 16, bottom: 6, trailing: 16))
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
                   
                Spacer()
            }
        }
        .padding(.init(top: 24, leading: 16, bottom: 24, trailing: 16))
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color.backgroundItem)
                .shadow(radius: 8)
                
        )
        .padding(.init(top: 12, leading: .zero, bottom: .zero, trailing: .zero))
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onUpgradeClick()
        }
        
    }
}

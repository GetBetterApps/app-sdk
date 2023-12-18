//
//  OnboardingThirdStep.swift
//  iosApp
//
//  Created by velkonost on 18.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import SwiftUIPager

struct OnboardingThirdStep: View {
    
    @Binding var textVisible: Bool
    let items: [Ability]
    
    init(textVisible: Binding<Bool>, items: [Ability]) {
        self._textVisible = textVisible
        self.items = items
    }
    
    @StateObject var page: Page = .first()
    
    var body: some View {
        Pager(
            page: page,
            data: items,
            id: \.self.id
        ) { item in
            OnboardingAbilityItem(item: item)
        }
        .interactive(rotation: true)
        .interactive(scale: 0.8)
        .alignment(.center)
        .preferredItemSize(CGSize(width: 300, height: 150))
        .frame(height: 150)
        .padding(.bottom, 16)
        .onAppear {
            withAnimation(.easeInOut(duration: 0.5).delay(1)) {
                textVisible = true
            }
        }
        
    }
}

//
//  OnboardingAffirmationText.swift
//  iosApp
//
//  Created by velkonost on 18.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct OnboardingAffirmationText: View {
    
    private let text: String
    
    init(text: String) {
        self.text = text
    }
    
    var body: some View {
        VStack(alignment: .center) {
            Text(text)
                .style(.headlineLarge, withLineSpacing: 12)
                .foregroundColor(.textTitle)
                .multilineTextAlignment(.center)
            
        }
        .padding(.init(16))
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color.backgroundItem).opacity(0.4)
                .shadow(radius: 8)
        )
        .padding(.init(16))
    }
}

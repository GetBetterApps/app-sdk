//
//  OnboardingFirstStep.swift
//  iosApp
//
//  Created by velkonost on 18.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct OnboardingFirstStep: View {
    var body: some View {
        
        ZStack {
            Image(uiImage: SharedR.images().ic_onboarding_1_3.toUIImage()!)
                .resizable()
                .scaledToFit()
                .frame(width: 300)
                .frame(alignment: .topLeading)
        }
        .frame(maxWidth: .infinity)
        .padding(.horizontal, 16)
    }
}

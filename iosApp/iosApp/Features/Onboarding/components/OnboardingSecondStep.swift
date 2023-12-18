//
//  OnboardingSecondStep.swift
//  iosApp
//
//  Created by velkonost on 18.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct OnboardingSecondStep: View {
    
    @State var imageIndex: Int = 0
    
    
    var body: some View {
        @State var rotation: Angle = Angle(degrees: Double(imageIndex) * Double(180))
        
        Image(uiImage: SharedR.images().ic_onboarding_2_1.toUIImage()!)
            .resizable()
            .shadow(radius: 8)
            .scaledToFit()
            .frame(maxWidth: .infinity)
            .padding(.horizontal, 32)
            .rotation3DEffect(
                rotation,
                axis: (x: 1.0, y: 0.0, z: 0.0)
            )
            .onAppear {
                withAnimation(.easeInOut(duration: 0.5).delay(3.5)) {
                    imageIndex += 1
                }
            }
    }
}

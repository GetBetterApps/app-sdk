//
//  OnboardingForthStep.swift
//  iosApp
//
//  Created by velkonost on 18.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct OnboardingForthStep: View {
    
    @Binding var textVisible: Bool
    
    init(textVisible: Binding<Bool>) {
        self._textVisible = textVisible
    }
    
    @State var imageIndex: Int = 0
    private let durationPerImage: Double = 3.5
    
    private let images = [
        SharedR.images().ic_onboarding_4_1,
        SharedR.images().ic_onboarding_4_2,
        SharedR.images().ic_onboarding_4_3
    ]
    
    var body: some View {
        Image(uiImage: images[imageIndex].toUIImage()!)
            .resizable()
            .shadow(radius: 8)
            .scaledToFit()
            .frame(maxWidth: .infinity)
            .animation(.easeInOut, value: imageIndex)
            .padding(.horizontal, 8)
            .onAppear {
                DispatchQueue.main.asyncAfter(deadline: .now() + durationPerImage) {
                    imageIndex += 1
                    textVisible = true
                }
                
                DispatchQueue.main.asyncAfter(deadline: .now() + 2 * durationPerImage) {
                    imageIndex += 1
                }
            }
    }
        
}

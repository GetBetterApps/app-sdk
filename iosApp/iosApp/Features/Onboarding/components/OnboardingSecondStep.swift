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
    private let durationPerImage: Double = 3.5
    
    @State var flipped: Bool = false
    
    private let images = [
        SharedR.images().ic_onboarding_2_1,
        SharedR.images().ic_onboarding_2_2,
        SharedR.images().ic_onboarding_2_3,
        SharedR.images().ic_onboarding_2_4,
        SharedR.images().ic_onboarding_2_5
    ]
    
    var body: some View {
        @State var rotation: Angle = Angle(degrees: Double(imageIndex) * Double(180))
        
        
        @State var imageRotationDegrees: Double = if imageIndex == 1 || imageIndex == 3 {
            180
        } else {
            0
        }
        
        @State var imageRotation: Angle = Angle(degrees: imageRotationDegrees)
        
        ZStack {
            if flipped {
                Image(uiImage: images[imageIndex].toUIImage()!)
                    .resizable()
                    .shadow(radius: 8)
                    .scaledToFit()
                    .frame(maxWidth: .infinity)
                    .padding(.horizontal, 32)
                    .rotation3DEffect(
                        Angle(degrees: Double(180)),
                        axis: (x: 0.0, y: 1.0, z: 0.0)
                    )
            } else {
                Image(uiImage: images[imageIndex].toUIImage()!)
                    .resizable()
                    .shadow(radius: 8)
                    .scaledToFit()
                    .frame(maxWidth: .infinity)
                    .padding(.horizontal, 32)
            }
           
        }
        
        .rotation3DEffect(
            rotation,
            axis: (x: 0.0, y: 1.0, z: 0.0)
        )
        .animation(.easeInOut(duration: 1), value: rotation)
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + durationPerImage) {
                imageIndex += 1
                flipped.toggle()
            }
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 2 * durationPerImage) {
                imageIndex += 1
                flipped.toggle()
            }
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 3 * durationPerImage) {
                imageIndex += 1
                flipped.toggle()
            }
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 4 * durationPerImage) {
                imageIndex += 1
                flipped.toggle()
            }
        }
    }
}

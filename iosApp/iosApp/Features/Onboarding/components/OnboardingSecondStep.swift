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
    
    
    var body: some View {
        @State var rotation: Angle = Angle(degrees: Double(imageIndex) * Double(180))
        
        @State var imageRotationDegrees: Double = if rotation.degrees <= 90 {
            0
        } else if rotation.degrees <= 180 {
            180
        } else if rotation.degrees <= 360 {
            0
        } else if rotation.degrees <= 540 {
            180
        } else {
            0
        }
        
        @State var imageRotation: Angle = Angle(degrees: imageRotationDegrees)
        
        ZStack {
            Image(uiImage: SharedR.images().ic_onboarding_2_1.toUIImage()!)
                .resizable()
                .shadow(radius: 8)
                .scaledToFit()
                .frame(maxWidth: .infinity)
                .padding(.horizontal, 32)
                .rotation3DEffect(
                    imageRotation,
                    axis: (x: 0.0, y: 1.0, z: 0.0)
                )
        }
       
            .rotation3DEffect(
                rotation,
                axis: (x: 0.0, y: 1.0, z: 0.0)
            )
//            .animation(.easeInOut(duration: 1).delay(durationPerImage), value: rotation)
            .onAppear {
//                withAnimation(.easeInOut(duration: 0.5).delay(durationPerImage)) {
                    imageIndex += 1
//                }
                
//                withAnimation(.easeInOut(duration: 0.5).delay(2 * durationPerImage)) {
//                    imageIndex += 1
//                }
                
//                withAnimation(.easeInOut(duration: 0.5).delay(3 * durationPerImage)) {
//                    imageIndex += 1
//                }
                
//                withAnimation(.easeInOut(duration: 0.5).delay(4 * durationPerImage)) {
//                    imageIndex += 1
//                }
            }
    }
}

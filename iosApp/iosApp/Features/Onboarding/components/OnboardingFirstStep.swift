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
    
    private let durationPerImage: Double = 0.5
    
    @State var firstImageShouldShow: Bool = false
    @State var firstImageShown: Bool = false
    
    var body: some View {
        
        
        ZStack {
            HStack {
                if firstImageShown {
                    Spacer()
                }
                
                Image(uiImage: SharedR.images().ic_onboarding_1_3.toUIImage()!)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 300)
                
                    .frame(alignment: .bottomTrailing)
                
                if !firstImageShown {
                    Spacer()
                }
            }
            .opacity(firstImageShouldShow ? 1 : 0)
        }
        .frame(maxWidth: .infinity)
        .padding(.horizontal, 16)
        .onAppear {
            withAnimation(.easeInOut) {
                firstImageShouldShow.toggle()
            }
            
            withAnimation(.easeInOut.delay(durationPerImage)) {
                firstImageShown = true
            }
            
        }
    }
}

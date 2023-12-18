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
    private let moveAnimationDelay: Double = 0.5
    
    @State var firstImageShouldShow: Bool = false
    @State var firstImageShown: Bool = false
    
    @State var secondImageShouldShow: Bool = false
    @State var secondImageShown: Bool = false
    
    @State var thirdImageShouldShow: Bool = false
    @State var thirdImageShown: Bool = false
    
    @State var forthImageShouldShow: Bool = false
    @State var forthImageShown: Bool = false
    
    @State var fifthImageShouldShow: Bool = false
    @State var fifthImageShown: Bool = false
    
    var body: some View {
        
        
        ZStack {
            
            HStack {
                if firstImageShown {
                    Spacer()
                }
                
                Image(uiImage: SharedR.images().ic_onboarding_1_3.toUIImage()!)
                    .resizable()
                    .shadow(radius: 8)
                    .scaledToFit()
                    .frame(width: firstImageShown ? 150 : 300)
                    .frame(alignment: .bottomTrailing)
                    .padding(.top, firstImageShown ? 230 : 0)
                
                if !firstImageShown {
                    Spacer()
                }
            }.opacity(firstImageShouldShow ? 1 : 0)
            
            HStack {
                if secondImageShown {
                    Spacer()
                }
                
                Image(uiImage: SharedR.images().ic_onboarding_1_2.toUIImage()!)
                    .resizable()
                    .shadow(radius: 8)
                    .scaledToFit()
                    .frame(width: secondImageShown ? 150 : 300)
                    .frame(alignment: .bottomTrailing)
                    .padding(.top, secondImageShown ? 210 : 0)
                    .padding(.trailing, secondImageShown ? 10 : 0)
                
                if !secondImageShown {
                    Spacer()
                }
            }.opacity(secondImageShouldShow ? 1 : 0)
            
            HStack {
                if thirdImageShown {
                    Spacer()
                }
                
                Image(uiImage: SharedR.images().ic_onboarding_1_1.toUIImage()!)
                    .resizable()
                    .shadow(radius: 8)
                    .scaledToFit()
                    .frame(width: thirdImageShown ? 150 : 300)
                    .frame(alignment: .bottomTrailing)
                    .padding(.top, thirdImageShown ? 180 : 0)
                    .padding(.trailing, thirdImageShown ? 20 : 0)
                
                if !thirdImageShown {
                    Spacer()
                }
            }.opacity(thirdImageShouldShow ? 1 : 0)
            
        }
        .frame(maxWidth: .infinity)
        .padding(.horizontal, 16)
        .onAppear {
            withAnimation(.easeInOut(duration: 0.5).delay(0.5)) {
                firstImageShouldShow = true
            }
        }
        .onChange(of: firstImageShouldShow) { _ in
            withAnimation(.easeInOut(duration: 0.5).delay(2)) {
                firstImageShown = true
            }
        }
        .onChange(of: firstImageShown) { _ in
            withAnimation(.easeInOut(duration: 0.5).delay(2.5)) {
                secondImageShouldShow = true
            }
        }
        .onChange(of: secondImageShouldShow) { _ in
            withAnimation(.easeInOut(duration: 0.5).delay(4)) {
                secondImageShown = true
            }
        }
        .onChange(of: secondImageShown) { _ in
            withAnimation(.easeInOut(duration: 0.5).delay(4.5)) {
                thirdImageShouldShow = true
            }
        }
        .onChange(of: thirdImageShouldShow) { _ in
            withAnimation(.easeInOut(duration: 0.5).delay(6)) {
                thirdImageShown = true
            }
        }
    }
}

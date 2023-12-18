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
    
    @Binding var textVisible: Bool
    
    init(textVisible: Binding<Bool>) {
        self._textVisible = textVisible
    }
    
    var body: some View {
        
        ZStack {
            
            Image(uiImage: SharedR.images().ic_getbetter_light_.toUIImage()!)
                .resizable()
                .scaledToFit()
                .frame(maxWidth: .infinity)
                .opacity(fifthImageShown ? 1 : 0)
            
            ZStack {
                HStack {
                    if firstImageShown {
                        Spacer()
                    }
                    
                    Image(uiImage: SharedR.images().ic_onboarding_1_3.toUIImage()!)
                        .resizable()
                        .shadow(radius: 8)
                        .scaledToFit()
                        .frame(width: firstImageShown ? 180 : 300)
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
                        .frame(width: secondImageShown ? 180 : 300)
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
                    
                    Image(uiImage: SharedR.images().ic_onboarding_1_4.toUIImage()!)
                        .resizable()
                        .shadow(radius: 8)
                        .scaledToFit()
                        .frame(width: thirdImageShown ? 180 : 300)
                        .frame(alignment: .bottomTrailing)
                        .padding(.top, thirdImageShown ? 190 : 0)
                        .padding(.trailing, thirdImageShown ? 20 : 0)
                    
                    if !thirdImageShown {
                        Spacer()
                    }
                }.opacity(thirdImageShouldShow ? 1 : 0)
                
                HStack {
                    if forthImageShown {
                        Spacer()
                    }
                    
                    Image(uiImage: SharedR.images().ic_onboarding_1_1.toUIImage()!)
                        .resizable()
                        .shadow(radius: 8)
                        .scaledToFit()
                        .frame(width: forthImageShown ? 180 : 300)
                        .frame(alignment: .bottomTrailing)
                        .padding(.top, forthImageShown ? 160 : 0)
                        .padding(.trailing, forthImageShown ? 30 : 0)
                    
                    if !forthImageShown {
                        Spacer()
                    }
                }.opacity(forthImageShouldShow ? 1 : 0)
                
                HStack {
                    
                    Image(uiImage: SharedR.images().ic_onboarding_1_5.toUIImage()!)
                        .resizable()
                        .shadow(radius: 8)
                        .scaledToFit()
                        .frame(width: 300)
                        .frame(alignment: .bottomTrailing)
                    
                    Spacer()
                    
                }.opacity(fifthImageShouldShow ? 1 : 0)
                
            }
            .opacity(fifthImageShown ? 0 : 1)
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
            .onChange(of: thirdImageShown) { _ in
                withAnimation(.easeInOut(duration: 0.5).delay(6.5)) {
                    forthImageShouldShow = true
                }
            }
            .onChange(of: forthImageShouldShow) { _ in
                withAnimation(.easeInOut(duration: 0.5).delay(7.5)) {
                    forthImageShown = true
                }
            }
            .onChange(of: forthImageShown) { _ in
                withAnimation(.easeInOut(duration: 0.5).delay(8)) {
                    fifthImageShouldShow = true
                }
            }
            .onChange(of: fifthImageShouldShow) { _ in
                withAnimation(.easeInOut(duration: 0.5).delay(9.5)) {
                    textVisible = true
                }
                
                withAnimation(.easeInOut(duration: 0.5).delay(10.5)) {
                    fifthImageShown = true
                }
            }
        }
    }
}

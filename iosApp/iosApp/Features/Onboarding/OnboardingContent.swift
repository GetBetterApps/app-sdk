//
//  OnboardingContent.swift
//  iosApp
//
//  Created by velkonost on 30.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import Lottie

struct OnboardingContent: View {
    
    private let isLoading: Bool
    private let title: String
    private let step: Int
    private let onNextClick: () -> Void
    private let onSkipClick: () -> Void
    
    @State private var firstPointVisible: Bool = false
    @State private var secondPointVisible: Bool = false
    @State private var thirdPointVisible: Bool = false
    @State private var forthPointVisible: Bool = false
    @State private var fifthPointVisible: Bool = false
    
    @State private var buttonVisible: Bool = false
    
    init(isLoading: Bool, title: String, step: Int, onNextClick: @escaping () -> Void, onSkipClick: @escaping () -> Void) {
        self.isLoading = isLoading
        self.title = title
        self.step = step
        self.onNextClick = onNextClick
        self.onSkipClick = onSkipClick
    }
    
    var body: some View {
        VStack(spacing: 0) {
            Spacer().frame(height: 60)
            
            HStack {
                Spacer()
                Text(SharedR.strings().skip_btn.desc().localized())
                    .style(.bodySmall)
                    .foregroundColor(.textPrimary)
                    .padding(.trailing, 16)
                    .onTapGesture {
                        onSkipClick()
                    }
            }
            .padding(.bottom, 20)
            
            Spacer().frame(height: 24)
            
            OnboardingLottieView(resource: SharedR.files().anim_onboarding_1.path, loopMode: .loop)
                .frame(width: 256, height: 256)
                .scaleEffect(0.3)
            
            Spacer().frame(height: 24)
            
            if firstPointVisible {
                OnboardingPoint(
                    title: SharedR.strings().onboarding_step_1.desc().localized(),
                    index: 0
                )
            }
            
            if secondPointVisible {
                OnboardingPoint(
                    title: SharedR.strings().onboarding_step_2.desc().localized(),
                    index: 1
                )
            }
            
            if thirdPointVisible {
                OnboardingPoint(
                    title: SharedR.strings().onboarding_step_3.desc().localized(),
                    index: 2
                )
            }
            
            if forthPointVisible {
                OnboardingPoint(
                    title: SharedR.strings().onboarding_step_4.desc().localized(),
                    index: 3
                )
            }
            
            if fifthPointVisible {
                OnboardingPoint(
                    title: SharedR.strings().onboarding_step_5.desc().localized(),
                    index: 4
                )
            }
         
            Spacer()
            
            if buttonVisible {
                AppButton(
                    labelText: step == 5 ? SharedR.strings().onboarding_btn.desc().localized() : SharedR.strings().continue_btn.desc().localized(),
                    isLoading: isLoading,
                    onClick: onNextClick
                )
            }
            
            Spacer().frame(height: 48)
        }
        .onAppear {
            withAnimation(.bouncy.delay(1)) {
                firstPointVisible = true
            }
            
            withAnimation(.bouncy.delay(3)) {
                secondPointVisible = true
            }
            
            withAnimation(.bouncy.delay(5)) {
                thirdPointVisible = true
            }
            
            withAnimation(.bouncy.delay(7)) {
                forthPointVisible = true
            }
            
            withAnimation(.bouncy.delay(9)) {
                fifthPointVisible = true
            }
            
            withAnimation(.bouncy.delay(11)) {
                buttonVisible = true
            }
        }
    }
}

struct OnboardingPoint: View {
    
    private let index: Int
    private let title: String
    
    init(title: String, index: Int) {
        self.title = title
        self.index = index
    }
    
    @State private var animVisible: Bool = false
    
    var body: some View {
        HStack(spacing: 0) {
            if animVisible {
                OnboardingLottieView(resource: SharedR.files().anim_mark.path)
                    .frame(width: 24, height: 24)
                    .scaleEffect(0.1)
            } else {
                Spacer().frame(width: 24, height: 24)
            }
            
            Text(title.uppercased())
                .style(.labelMedium)
                .foregroundColor(.textTitle)
                .multilineTextAlignment(.leading)
                .padding(.leading, 8)
            
            Spacer()
                
        }
        .padding(.top, 12)
        .padding(.horizontal, 32)
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 1.5 + 2.0 * Double(index)) {
                withAnimation(.bouncy) {
                    animVisible = true
                }
            }
            
        }
    }
}

struct OnboardingLottieView: UIViewRepresentable {
    
    var resource: String

    var loopMode: LottieLoopMode = .playOnce
    
    func updateUIView(_ uiView: UIViewType, context: Context) {}

    func makeUIView(context: Context) -> Lottie.LottieAnimationView {
        let animationView = LottieAnimationView(filePath: resource)
        
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = loopMode
        animationView.play()
        
        return animationView
    }
    
}

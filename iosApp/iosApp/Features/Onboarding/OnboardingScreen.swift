//
//  OnboardingScreen.swift
//  iosApp
//
//  Created by velkonost on 17.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct OnboardingScreen: View {
    
    @StateViewModel var viewModel: OnboardingViewModel
    
    @State var textVisible: Bool = false
    @State var moveTextToBottom: Bool = false
    @State var buttonVisible: Bool = false
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! OnboardingViewState
        
        ZStack {
            
            if state.affirmation != nil && state.step == 5 {
                OnboardingFifthStep(
                    item: state.affirmation!,
                    isActive: state.step == 5,
                    text: state.title.localized()
                )
            }
            
            VStack {
                Spacer().frame(height: 60)
                
                HStack {
                    Spacer()
                    Text(SharedR.strings().skip_btn.desc().localized())
                        .style(.bodyLarge)
                        .foregroundColor(.textPrimary)
                        .padding(.trailing, 16)
                        .opacity(state.step != 1 && state.step != 5 ? 1 : 0)
                        .onTapGesture {
                            viewModel.dispatch(action: OnboardingActionSkipClick())
                        }
                }
                .padding(.bottom, 20)
                
                ZStack {
                    OnboardingFirstStep(
                        textVisible: $textVisible
                    )
                    .opacity(state.step == 1 ? 1 : 0)
                    
                    if state.step == 2 {
                        OnboardingSecondStep(
                            textVisible: $textVisible,
                            buttonVisible: $buttonVisible
                        )
                        .frame(alignment: .center)
                    }
                    
                    if state.step == 3 {
                        OnboardingThirdStep(
                            textVisible: $textVisible,
                            items: state.abilities
                        )
                    }
                    
                    if state.step == 4 {
                        OnboardingForthStep(
                            textVisible: $textVisible
                        )
                    }
                }
                .animation(.easeInOut, value: state.step)
                .frame(minWidth: 0, maxWidth: .infinity)
                
                if moveTextToBottom {
                    Spacer()
                    Spacer()
                    Spacer()
                }
                
                if state.step != 5 {
                    Text(state.title.localized())
                        .style(.headlineLarge)
                        .foregroundColor(.textTitle)
                        .multilineTextAlignment(.center)
                        .opacity(textVisible ? 1 : 0)
                        .padding(.horizontal, 32)
                }
                
                Spacer()
                
                AppButton(
                    labelText: state.step == 5 ? SharedR.strings().onboarding_btn.desc().localized() : SharedR.strings().continue_btn.desc().localized(),
                    isLoading: state.isLoading,
                    onClick: {
                        withAnimation(.easeInOut) {
                            textVisible = false
                            
                            if state.step == 1 {
                                buttonVisible = false
                            }
                        }
                        
                        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
                            viewModel.dispatch(action: OnboardingActionNextClick())
                        }
                    }
                )
                .opacity(buttonVisible ? 1 : 0)
                
                Spacer().frame(height: 48)
            }
            .edgesIgnoringSafeArea(.all)
            .onChange(of: textVisible) { _ in
                withAnimation(.easeInOut(duration: 0.5).delay(9)) {
                    moveTextToBottom = true
                }
            }
            .onChange(of: moveTextToBottom) { _ in
                withAnimation(.easeInOut(duration: 0.5).delay(10)) {
                    buttonVisible = true
                }
            }
        }
        .edgesIgnoringSafeArea(.all)
    }
}

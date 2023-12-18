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
        
        VStack {
            
            Spacer().frame(height: 70)
            
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
            }
            
            Text(state.title.localized())
                .style(.headlineLarge)
                .foregroundColor(.textTitle)
                .multilineTextAlignment(.center)
                .opacity(textVisible ? 1 : 0)
                .padding(.horizontal, 32)
            
            Spacer()
            
            AppButton(
                labelText: SharedR.strings().continue_btn.desc().localized(),
                isLoading: false,
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
            
            Spacer().frame(height: 32)
        }
        .onChange(of: textVisible) { _ in
            withAnimation(.easeInOut(duration: 0.5).delay(11)) {
                moveTextToBottom = true
            }
        }
        .onChange(of: moveTextToBottom) { _ in
            withAnimation(.easeInOut(duration: 0.5).delay(12)) {
                buttonVisible = true
            }
        }
    }
}

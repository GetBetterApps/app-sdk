//
//  AuthScreen.swift
//  iosApp
//
//  Created by velkonost on 10.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import KMMViewModelSwiftUI
import SwiftUI


struct AuthScreen: View {
    
    @StateViewModel var viewModel: AuthViewModel
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! AuthViewState
        
        let switchRegisteringText = if state.isRegistering {
            SharedR.strings().auth_have_account.desc().localized().uppercased()
        } else {
            SharedR.strings().auth_dont_have_account.desc().localized().uppercased()
        }
        
        
        let authButtonText = if state.isRegistering {
            SharedR.strings().auth_signup_button.desc().localized()
        } else {
            SharedR.strings().auth_login_button.desc().localized()
        }
        
        
        ZStack(alignment: .topLeading) {
            
            LinearGradient(
                colors: [.onboardingBackgroundGradientStart, .onboardingBackgroundGradientEnd],
                startPoint: .top, endPoint: .bottom
            )
            
            VStack(alignment: .leading, spacing: .zero) {
                AuthAnonymousButton(enabled: !state.isLoading) {
                    viewModel.dispatch(action: AuthActionAnonymousLoginClick())
                }
                
                Image(uiImage: SharedR.images().ic_getbetter_light_.toUIImage()!)
                    .resizable()
                    .scaledToFit()
                    .opacity(0.4)
                
                Spacer().frame(height: 32)
                
                AuthTextField(
                    placeholderText: SharedR.strings().auth_email_label.desc().localized(),
                    inputType: .Email
                ) { newValue in
                    viewModel.dispatch(action: AuthActionEmailChanged(value: newValue))
                }
                
                AuthTextField(
                    placeholderText: SharedR.strings().auth_password_label.desc().localized(),
                    inputType: .Password
                ) { newValue in
                    viewModel.dispatch(action: AuthActionPasswordChanged(value: newValue))
                }
                
                SwitchRegisteringText(switchRegisteringText) {
                    viewModel.dispatch(action: AuthActionSwitchAuthClick())
                }
               
                Spacer()

                HStack() {
                    Spacer()
                    WhiteButton(
                        labelText: authButtonText,
                        isLoading: state.isLoading
                    ) {
                        viewModel.dispatch(action: AuthActionLoginClick())
                    }
                    Spacer()
                }.animation(.easeInOut, value: authButtonText)
                
                Spacer().frame(height: 32)
                
                Text(SharedR.strings().auth_footer_text.desc().localized())
                    .foregroundColor(.textLight)
                    .style(.bodySmall)
                    .multilineTextAlignment(.center)
                    .opacity(0.2)
                    .padding(.init(top: .zero, leading: 32, bottom: 48, trailing: 32))
            }
            .padding(.init(top: 50, leading: 16, bottom: 0, trailing: 16))
        }
        .navigationBarHidden(true)
        .edgesIgnoringSafeArea(.all)
        .onTapGesture {
            self.endTextEditing()
        }
    }
}

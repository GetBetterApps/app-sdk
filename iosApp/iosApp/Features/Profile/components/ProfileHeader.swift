//
//  ProfileHeader.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct ProfileHeader: View {
    
    let userName: String
    let avatarUrl: String?
    let isLoading: Bool
    let onAvatarClick: () -> Void
    let onSettingsClick: () -> Void
    let showSettings: Bool
    let onSignUpClick: (() -> Void)?
    
    init(userName: String, avatarUrl: String?, isLoading: Bool, onAvatarClick: @escaping () -> Void, onSettingsClick: @escaping () -> Void, showSettings: Bool = true, onSignUpClick: (() -> Void)? = nil) {
        self.userName = userName
        self.avatarUrl = avatarUrl
        self.isLoading = isLoading
        self.onAvatarClick = onAvatarClick
        self.onSettingsClick = onSettingsClick
        self.showSettings = showSettings
        self.onSignUpClick = onSignUpClick
    }
    
    var body: some View {
        HStack(alignment: .bottom) {
            Avatar(isLoading: isLoading, avatarUrl: avatarUrl) {
                onAvatarClick()
            }
            
            VStack {
                if showSettings {
                    HStack {
                        Spacer()
                        SettingsButton {
                            onSettingsClick()
                        }
                    }
                }
                
                Spacer()
                
                HStack {
                    if showSettings {
                        Text(userName)
                            .foregroundColor(.textTitle)
                            .style(.titleLarge)
                    } else {
                        AppButton(
                            labelText: SharedR.strings().auth_signup_button.desc().localized(),
                            isLoading: isLoading,
                            onClick: {
                                if onSignUpClick != nil {
                                    onSignUpClick!()
                                }
                            }
                        )
                    }
                    Spacer()
                }.padding(.init(top: .zero, leading: 16, bottom: .zero, trailing: .zero))
            }.frame(height: 128)
            
        }
        .padding(.init(top: 32, leading: .zero, bottom: .zero, trailing: .zero))
        .frame(height: 128)
    }
}

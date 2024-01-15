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
    let isAnonymous: Bool
    let onSignUpClick: (() -> Void)?
    
    init(
        userName: String, avatarUrl: String?,
        isLoading: Bool,
        showSettings: Bool = true,
        isAnonymous: Bool = false,
        onAvatarClick: @escaping () -> Void,
        onSettingsClick: @escaping () -> Void,
        onSignUpClick: (() -> Void)? = nil
    ) {
        self.userName = userName
        self.avatarUrl = avatarUrl
        self.isLoading = isLoading
        self.showSettings = showSettings
        self.isAnonymous = isAnonymous
        
        self.onAvatarClick = onAvatarClick
        self.onSettingsClick = onSettingsClick
        self.onSignUpClick = onSignUpClick
    }
    
    var body: some View {
        HStack(alignment: .bottom) {
            Avatar(isLoading: isLoading, avatarUrl: avatarUrl) {
                onAvatarClick()
            }
            
            VStack {
                if showSettings && !isAnonymous {
                    HStack {
                        Spacer()
                        SettingsButton {
                            onSettingsClick()
                        }
                    }
                }
                
                Spacer()
                
                HStack {
                    if !isAnonymous {
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
                            },
                            widthPercent: 0.4
                        )
                    }
                    Spacer()
                }
                .padding(.init(top: .zero, leading: 16, bottom: .zero, trailing: .zero))
            }.frame(height: 128)
            
        }
        .padding(.init(top: 32, leading: .zero, bottom: .zero, trailing: .zero))
        .frame(height: 128)
    }
}

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
    let onBlockUserClick: (() -> Void)?
    
    @State private var confirmBlockDialog: Bool = false
    
    init(
        userName: String, avatarUrl: String?,
        isLoading: Bool,
        showSettings: Bool = true,
        isAnonymous: Bool = false,
        onAvatarClick: @escaping () -> Void,
        onSettingsClick: @escaping () -> Void,
        onSignUpClick: (() -> Void)? = nil,
        onBlockUserClick: (() -> Void)? = nil
    ) {
        self.userName = userName
        self.avatarUrl = avatarUrl
        self.isLoading = isLoading
        self.showSettings = showSettings
        self.isAnonymous = isAnonymous
        
        self.onAvatarClick = onAvatarClick
        self.onSettingsClick = onSettingsClick
        self.onSignUpClick = onSignUpClick
        self.onBlockUserClick = onBlockUserClick
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
                } else if !showSettings {
                    BlockUserButton {
                        if onBlockUserClick != nil {
                            confirmBlockDialog = true
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
        .alert(
             SharedR.strings().note_detail_hide_title.desc().localized(), isPresented: $confirmBlockDialog) {
                Button(SharedR.strings().confirm.desc().localized()) {
                    viewModel.dispatch(action: NoteDetailActionHideClick())
                }
                Button(SharedR.strings().cancel.desc().localized(), role: .cancel) {}
            } message: {
                Text(SharedR.strings().note_detail_hide_text.desc().localized())
            }
    }
}

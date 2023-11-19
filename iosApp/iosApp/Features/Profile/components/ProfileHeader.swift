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
    let avatarBytes: KotlinByteArray?
    let isLoading: Bool
    let onAvatarClick: () -> Void
    let onSettingsClick: () -> Void
    let showSettings: Bool
    
    init(userName: String, avatarBytes: KotlinByteArray?, isLoading: Bool, onAvatarClick: @escaping () -> Void, onSettingsClick: @escaping () -> Void, showSettings: Bool = true) {
        self.userName = userName
        self.avatarBytes = avatarBytes
        self.isLoading = isLoading
        self.onAvatarClick = onAvatarClick
        self.onSettingsClick = onSettingsClick
        self.showSettings = showSettings
    }
    
    var body: some View {
        HStack(alignment: .bottom) {
            Avatar(isLoading: isLoading, avatarBytes: avatarBytes) {
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
                    Text(userName)
                        .foregroundColor(.textTitle)
                        .style(.titleLarge)
                    Spacer()
                }.padding(.init(top: .zero, leading: 16, bottom: .zero, trailing: .zero))
            }.frame(height: 128)
            
        }
        .padding(.init(top: 32, leading: .zero, bottom: .zero, trailing: .zero))
        .frame(height: 128)
    }
}

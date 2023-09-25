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
    
    init(userName: String, avatarUrl: String?, isLoading: Bool, onAvatarClick: @escaping () -> Void, onSettingsClick: @escaping () -> Void) {
        self.userName = userName
        self.avatarUrl = avatarUrl
        self.isLoading = isLoading
        self.onAvatarClick = onAvatarClick
        self.onSettingsClick = onSettingsClick
    }
    
    var body: some View {
        HStack(alignment: .bottom) {
            Avatar(isLoading: isLoading, avatarUrl: avatarUrl) {
                onAvatarClick()
            }
            
            VStack {
                HStack {
                    Spacer()
                    SettingsButton {
                        onSettingsClick()
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

//
//  ChangePasswordBottomSheet.swift
//  iosApp
//
//  Created by velkonost on 27.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct ChangePasswordBottomSheet: View {
    
    private let changePasswordState: ChangePasswordState
    
    private let onOldPasswordChanged: (String) -> Void
    private let onNewPasswordChanged: (String) -> Void
    private let onRepeatedNewPasswordChanged: (String) -> Void
    private let onChangedClick: () -> Void
    
    init(changePasswordState: ChangePasswordState, onOldPasswordChanged: @escaping (String) -> Void, onNewPasswordChanged: @escaping (String) -> Void, onRepeatedNewPasswordChanged: @escaping (String) -> Void, onChangedClick: @escaping () -> Void) {
        self.changePasswordState = changePasswordState
        self.onOldPasswordChanged = onOldPasswordChanged
        self.onNewPasswordChanged = onNewPasswordChanged
        self.onRepeatedNewPasswordChanged = onRepeatedNewPasswordChanged
        self.onChangedClick = onChangedClick
    }
    
    var body: some View {
        ZStack {
            Color.mainBackground
            VStack {
                Spacer().frame(height: 32)
                
                SingleLineTextField(
                    value: changePasswordState.oldPassword,
                    placeholderText: SharedR.strings().settings_old_password_hint.desc().localized(),
                    onValueChanged: onOldPasswordChanged
                )
                
                SingleLineTextField(
                    value: changePasswordState.newPassword,
                    placeholderText: SharedR.strings().settings_new_password_hint.desc().localized(),
                    onValueChanged: onNewPasswordChanged
                )
                
                SingleLineTextField(
                    value: changePasswordState.repeatedNewPassword,
                    placeholderText: SharedR.strings().settings_repeat_new_password_hint.desc().localized(),
                    onValueChanged: onRepeatedNewPasswordChanged
                )
                
                Spacer()
                
                AppButton(
                    labelText: SharedR.strings().settings_change_password_button.desc().localized(),
                    isLoading: false,
                    onClick: onChangedClick
                )
                
                Spacer().frame(height: 64)
                
            }
            .contentShape(Rectangle())
            .onTapGesture {
                endTextEditing()
            }
            .padding(.horizontal, 16)
        }
        .ignoresSafeArea(.all)
    }
}

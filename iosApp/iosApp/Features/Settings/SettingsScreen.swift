//
//  SettingsScreen.swift
//  iosApp
//
//  Created by velkonost on 27.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct SettingsScreen: View {
    @StateViewModel var viewModel: SettingsViewModel
    
    @State private var confirmDeleteAccountDialog = false
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! SettingsViewState
        
        ZStack {
            if state.isLoading {
                Loader().frame(alignment: .center)
            } else {
                VStack {
                    SettingsHeader()
                    
                    SingleLineTextField(
                        value: state.email,
                        placeholderText: SharedR.strings().auth_email_label.desc().localized(),
                        isEnabled: false,
                        paddings: .init(top: 16, leading: 16, bottom: 0, trailing: 16),
                        onValueChanged: { _ in }
                    )
                    
                    NameTextField(
                        value: Binding(get: { state.name }, set: { _ in }),
                        placeholderText: SharedR.strings().settings_name_hint.desc().localized(),
                        onValueChanged: { value in
                            viewModel.dispatch(action: SettingsActionNameChanged(value: value))
                        },
                        onSaveClick: {
                            viewModel.dispatch(action: SettingsActionSaveNameClick())
                        }
                    )
                    
                    Spacer()
                    
                    AppButton(
                        labelText: SharedR.strings().settings_change_password_button.desc().localized(),
                        isLoading: state.isLoading,
                        onClick: {
                            viewModel.dispatch(action: SettingsActionChangePasswordClick())
                        }
                    )
                    
                    WhiteButton(
                        labelText: SharedR.strings().settings_delete_account_button.desc().localized(),
                        isLoading: state.isLoading,
                        onClick: {
                            confirmDeleteAccountDialog = true
                        },
                        height: 42
                    ).padding(.top, 16)
                    
                    Spacer().frame(height: 64)
                }.frame(alignment: .center)
            }
        }
    }
}

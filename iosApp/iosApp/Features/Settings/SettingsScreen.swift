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
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @StateViewModel var viewModel: SettingsViewModel
    
    @State private var eventsObserver: Task<(), Error>? = nil
    @State private var confirmDeleteAccountDialog = false
    @State private var changePasswordSheetVisible = false
    
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
                            changePasswordSheetVisible = true
                        }
                    )
                    .ignoresSafeArea(.keyboard)
                    
                    WhiteButton(
                        labelText: SharedR.strings().settings_delete_account_button.desc().localized(),
                        isLoading: state.isLoading,
                        onClick: {
                            confirmDeleteAccountDialog = true
                        },
                        height: 42
                    ).padding(.top, 16)
                        .ignoresSafeArea(.keyboard)
                    
                    Spacer().frame(height: 64)
                }.frame(alignment: .center)
                    .alert(
                        SharedR.strings().settings_delete_account_title.desc().localized(), isPresented: $confirmDeleteAccountDialog) {
                            Button(SharedR.strings().confirm.desc().localized()) {
                                viewModel.dispatch(action: SettingsActionDeleteAccountConfirm())
                            }
                            Button(SharedR.strings().cancel.desc().localized(), role: .cancel) {}
                        } message: {
                            Text(SharedR.strings().settings_delete_account_text.desc().localized())
                        }
                        .sheet(isPresented: $changePasswordSheetVisible) {
                            ChangePasswordBottomSheet(
                                changePasswordState: state.changePasswordState,
                                onOldPasswordChanged: { value in
                                    viewModel.dispatch(action: ChangePasswordActionOldPasswordChanged(value: value))
                                },
                                onNewPasswordChanged: { value in
                                    viewModel.dispatch(action: ChangePasswordActionNewPasswordChanged(value: value))
                                },
                                onRepeatedNewPasswordChanged: { value in
                                    viewModel.dispatch(action: ChangePasswordActionRepeatedNewPasswordChanged(value: value))
                                },
                                onChangedClick: {
                                    changePasswordSheetVisible = false
                                    viewModel.dispatch(action: ChangePasswordActionChangeClick())
                                }
                            )
                        }
            }
        }
        .onTapGesture {
            endTextEditing()
        }
        .onAppear {
            observeEvents()
        }
        .onDisappear {
            eventsObserver?.cancel()
            eventsObserver = nil
        }
        .ignoresSafeArea(.keyboard)
    }
}

extension SettingsScreen {
    func observeEvents() {
        if eventsObserver == nil {
            eventsObserver = Task {
                for try await event in asyncSequence(for: viewModel.events) {
                    switch(event) {
                    case _ as SettingsEventPasswordChanged: do {
                        changePasswordSheetVisible = false
                    }
                        
                    case _ as SettingsEventAccountDeleted: do {
                        presentationMode.wrappedValue.dismiss()
                    }
                        
                    default:
                        break
                    }
                }
            }
        }
    }
}

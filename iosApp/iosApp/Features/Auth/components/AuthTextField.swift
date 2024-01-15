//
//  AuthTextField.swift
//  iosApp
//
//  Created by velkonost on 10.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AuthTextField: View {

    @State private var value: String = ""
    
    private let placeholderText: String
    private let inputType: InputType
    private let onValueChanged: (String) -> Void

    @FocusState private var isFocused: Bool
    
    init(placeholderText: String, inputType: InputType, onValueChanged: @escaping (String) -> Void) {
        self.placeholderText = placeholderText
        self.inputType = inputType
        self.onValueChanged = onValueChanged
    }
    
    
    var body: some View {
        if inputType == .Email {
            TextField("", text: $value)
                .style(.titleMedium)
                .keyboardType(inputType == .Email ? .emailAddress : .default)
                .foregroundColor(.textLight)
                .placeholder(when: value.isEmpty, placeholder: {
                    Text(placeholderText)
                        .style(.titleMedium)
                        .foregroundColor(.onboardingHintColor)
                })
                .autocorrectionDisabled()
                .focused($isFocused)
                .padding(.init(top: .zero, leading: 16, bottom: .zero, trailing: 16))
                .frame(height: 56)
                .background(isFocused ? Color.authTextFieldBackground : Color.onboardingTextField)
                .cornerRadius(12)
                .padding(.init(top: 12, leading: .zero, bottom: .zero, trailing: .zero))
                .onChange(of: value) { newValue in
                    onValueChanged(newValue)
                }
        } else {
            SecureField("", text: $value)
                .style(.titleMedium)
                .foregroundColor(.textLight)
                .placeholder(when: value.isEmpty, placeholder: {
                    Text(placeholderText)
                        .style(.titleMedium)
                        .foregroundColor(.onboardingHintColor)
                })
                .focused($isFocused)
                .padding(.init(top: .zero, leading: 16, bottom: .zero, trailing: 16))
                .frame(height: 56)
                .background(isFocused ? Color.authTextFieldBackground : Color.onboardingTextField)
                .cornerRadius(12)
                .padding(.init(top: 12, leading: .zero, bottom: .zero, trailing: .zero))
                .onChange(of: value) { newValue in
                    onValueChanged(newValue)
                }
        }
    
    }
}

enum InputType {
    case Email, Password
}

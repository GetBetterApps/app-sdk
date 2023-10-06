//
//  MultilineTextField.swift
//  iosApp
//
//  Created by velkonost on 28.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct MultilineTextField: View {
    
    let minLines: Int = 3
    @State private var value: String = ""
    private let placeholderText: String
    private let onValueChanged: (String) -> Void
    
    @FocusState private var isFocused: Bool
    
    init(value: String, placeholderText: String, onValueChanged: @escaping (String) -> Void) {
        self.value = value
        self.placeholderText = placeholderText
        self.onValueChanged = onValueChanged
    }
    
    var body: some View {
        TextField("", text: $value, axis: .vertical)
            .style(.titleMedium)
            .keyboardType(.default)
            .lineLimit(minLines...20)
            .foregroundColor(.textSecondaryTitle)
            .placeholder(when: value.isEmpty) {
                Text(placeholderText)
                    .style(.titleMedium)
                    .lineLimit(minLines...20)
                    .foregroundColor(.hintColor)
            }
            .focused($isFocused)
            .padding(16)
            .background(Color.textFieldBackground)
            .cornerRadius(12)
            .padding(.top, 12)
            .onChange(of: value) { newValue in
                onValueChanged(newValue)
            }
            .ignoresSafeArea(.keyboard, edges: .bottom)
    }
}
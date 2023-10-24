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
    
    let minLines: Int
    @State private var value: String = ""
    private let placeholderText: String
    private let isEnabled: Bool
    private let textAlign: TextAlignment
    private let paddings: EdgeInsets
    
    private let onValueChanged: (String) -> Void
    
    @FocusState private var isFocused: Bool
    
    init(
        value: String,
        placeholderText: String,
        minLines: Int = 3,
        isEnabled: Bool = true,
        textAlign: TextAlignment = .leading,
        paddings: EdgeInsets = .init(top: 12, leading: .zero, bottom: .zero, trailing: .zero),
        onValueChanged: @escaping (String) -> Void
    ) {
        self.value = value
        self.minLines = minLines
        self.textAlign = textAlign
        self.isEnabled = isEnabled
        self.paddings = paddings
        self.placeholderText = placeholderText
        self.onValueChanged = onValueChanged
    }
    
    var body: some View {
        TextField("", text: $value, axis: .vertical)
            .style(.titleMedium)
            .keyboardType(.default)
            .multilineTextAlignment(textAlign)
            .lineLimit(minLines...20)
            .foregroundColor(.textSecondaryTitle)
            .placeholder(when: value.isEmpty) {
                Text(placeholderText)
                    .style(.titleMedium)
                    .multilineTextAlignment(textAlign)
                    .lineLimit(minLines...20)
                    .foregroundColor(.hintColor)
                    .frame(minWidth: 0, maxWidth: .infinity)
            }
            .focused($isFocused)
            .padding(16)
            .background(Color.textFieldBackground)
            .cornerRadius(12)
            .padding(paddings)
            .onChange(of: value) { newValue in
                onValueChanged(newValue)
            }
            .disabled(!isEnabled)
            .ignoresSafeArea(.keyboard, edges: .bottom)
    }
}

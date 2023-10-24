//
//  SingleLineTextField.swift
//  iosApp
//
//  Created by velkonost on 28.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct SingleLineTextField: View {
    
    @State private var value: String
    private let placeholderText: String
    private let onValueChanged: (String) -> Void
    private let isEnabled: Bool
    private let textAlign: TextAlignment
    private let textStyle: TextStyle
    private let paddings: EdgeInsets
    
    @FocusState private var isFocused: Bool
    
    init(
        value: String,
        placeholderText: String,
        isEnabled: Bool = true,
        textAlign: TextAlignment = .leading,
        textStyle: TextStyle = .titleMedium,
        paddings: EdgeInsets = .init(top: .zero, leading: 12, bottom: .zero, trailing: .zero),
        onValueChanged: @escaping (String) -> Void
    ) {
        self._value = State(initialValue: value)
        self.placeholderText = placeholderText
        self.textStyle = textStyle
        self.textAlign = textAlign
        self.isEnabled = isEnabled
        self.paddings = paddings
        self.onValueChanged = onValueChanged
        
    }
    
    var body: some View {
    
        TextField("", text: $value)
            .style(textStyle)
            .foregroundColor(.textSecondaryTitle)
            .lineLimit(1)
            .multilineTextAlignment(textAlign)
            .focused($isFocused)
            .placeholder(when: value.isEmpty) {
                Text(placeholderText)
                    .style(textStyle)
                    .multilineTextAlignment(textAlign)
                    .foregroundColor(.hintColor)
                    .frame(minWidth: 0, maxWidth: .infinity)
            }
            .padding(.leading, 16)
            .padding(.trailing, 16)
            .frame(height: 64)
            .background(Color.textFieldBackground)
            .cornerRadius(12)
            .disabled(!isEnabled)
            .padding(paddings)
            .onChange(of: value) { newValue in
                onValueChanged(newValue)
            }
        
    }
}

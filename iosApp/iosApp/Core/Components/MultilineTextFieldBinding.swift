//
//  MultilineTextFieldBinding.swift
//  iosApp
//
//  Created by velkonost on 04.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

import SwiftUI
import SharedSDK

struct MultilineTextFieldBinding: View {
    
    let minLines: Int
    @Binding private var value: String
    private let placeholderText: String
    private let isEnabled: Bool
    private let textAlign: TextAlignment
    private let paddings: EdgeInsets
    
    private let onValueChanged: (String) -> Void
    
    @FocusState private var isFocused: Bool
    
    init(
        value: Binding<String>,
        placeholderText: String,
        minLines: Int = 3,
        isEnabled: Bool = true,
        textAlign: TextAlignment = .leading,
        paddings: EdgeInsets = .init(top: 12, leading: .zero, bottom: .zero, trailing: .zero),
        onValueChanged: @escaping (String) -> Void
    ) {
        self._value = value
        self.minLines = minLines
        self.textAlign = textAlign
        self.isEnabled = isEnabled
        self.paddings = paddings
        self.placeholderText = placeholderText
        self.onValueChanged = onValueChanged
        self.textFieldValue = value.wrappedValue
    }
    
    @State private var textFieldValue: String
    
    var body: some View {
        TextField(
            "",
            text: Binding(
                get: { value },
                set: { textFieldValue = $0 }
            ),
            axis: .vertical)
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
                    .frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        alignment: textAlign == .leading ? .leading : .center
                    )
                
                if textAlign == .leading {
                    Spacer()
                }
            }
            .focused($isFocused)
            .padding(16)
            .background(Color.textFieldBackground)
            .cornerRadius(12)
            .padding(paddings)
            .onChange(of: textFieldValue) { newValue in
                onValueChanged(newValue)
            }
            .disabled(!isEnabled)
            .ignoresSafeArea(.keyboard, edges: .bottom)
    }
}

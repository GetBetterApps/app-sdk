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
        TextField("", text: $value)
            .style(.titleMedium)
            .foregroundColor(.textSecondaryTitle)
            .lineLimit(1)
            .focused($isFocused)
            .placeholder(when: value.isEmpty) {
                Text(placeholderText)
                    .style(.titleMedium)
                    .foregroundColor(.hintColor)
            }
            .padding(.leading, 16)
            .padding(.trailing, 16)
            .frame(height: 64)
            .background(Color.textFieldBackground)
            .cornerRadius(12)
            .padding(.leading, 12)
            .onChange(of: value) { newValue in
                onValueChanged(newValue)
            }
        
    }
}

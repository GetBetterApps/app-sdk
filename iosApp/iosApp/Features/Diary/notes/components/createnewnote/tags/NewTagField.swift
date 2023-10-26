//
//  NewTagField.swift
//  iosApp
//
//  Created by velkonost on 26.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct NewTagField : View {
    
    @Binding var value: String
    
    let placeholderText: String
    let onValueChanged: (String) -> Void
    let onAddNewTag: () -> Void
    
    @FocusState private var isFocused: Bool
    
    init(value: Binding<String>, placeholderText: String, onValueChanged: @escaping (String) -> Void, onAddNewTag: @escaping () -> Void) {
        self._value = value
        self.placeholderText = placeholderText
        self.onValueChanged = onValueChanged
        self.onAddNewTag = onAddNewTag
    }
    
    var body: some View {
        TextField(
            "",
            text: Binding(
                get: { value },
                set: { newValue in
                    onValueChanged(newValue)
                }
            )
        )
        
        .style(.bodyMedium)
        .foregroundColor(.textSecondaryTitle)
        .lineLimit(1)
        .focused($isFocused)
        .placeholder(when: value.isEmpty) {
            Text(placeholderText)
                .style(.bodyMedium)
                .lineLimit(1)
                .foregroundColor(.hintColor)
                .frame(
                    minWidth: 0,
                    maxWidth: .infinity,
                    alignment: .leading
                )
        }
        .padding(.leading, 4)
        .padding(.trailing, 4)
        .frame(height: 24)
        .background(Color.textFieldBackground)
        .cornerRadius(8)
        .onSubmit {
            value = ""
            isFocused = true
            onAddNewTag()
        }
    }
}

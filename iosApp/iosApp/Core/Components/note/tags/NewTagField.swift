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
import Combine

struct NewTagField : View {
    
    @Binding var value: TagUI
    
    let placeholderText: String
    let onValueChanged: (String) -> Void
    let onAddNewTag: () -> Void
    
    @FocusState private var isFocused: Bool
    
    init(value: Binding<TagUI>, placeholderText: String, onValueChanged: @escaping (String) -> Void, onAddNewTag: @escaping () -> Void) {
        self._value = value
        self.placeholderText = placeholderText
        self.onValueChanged = onValueChanged
        self.onAddNewTag = onAddNewTag
        self.textFieldValue = value.wrappedValue.text
    }
    
    @State private var textFieldValue: String
    
    var body: some View {
        TextField("",
            text: Binding(
                get: { value.text },
                set: { textFieldValue = $0 }
            )
        )
        .style(.bodyMedium)
        .foregroundColor(.textSecondaryTitle)
        .lineLimit(1)
        .focused($isFocused)
        .placeholder(when: value.text.isEmpty) {
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
        
        .autocorrectionDisabled()
        .padding(.leading, 6)
        .padding(.trailing, 6)
        .frame(height: 30)
        .background(Color.textFieldBackground)
        .cornerRadius(8)
        .onChange(of: textFieldValue) { newValue in
            onValueChanged(newValue)
        }
        .onSubmit {
            isFocused = true
            onAddNewTag()
        }
    }
}

//
//  NameTextField.swift
//  iosApp
//
//  Created by velkonost on 27.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct NameTextField: View {
    
    @Binding var value: String
    @State private var textFieldValue: String
    
    let placeholderText: String
    let onValueChanged: (String) -> Void
    let onSaveClick: () -> Void
    
    init(value: Binding<String>, placeholderText: String, onValueChanged: @escaping (String) -> Void, onSaveClick: @escaping () -> Void) {
        self._value = value
        self.textFieldValue = value.wrappedValue
        self.placeholderText = placeholderText
        self.onValueChanged = onValueChanged
        self.onSaveClick = onSaveClick
    }
    
    @FocusState private var isFocused: Bool
    
    var body: some View {
        HStack {
            TextField("",
                text: Binding(
                    get: { value },
                    set: { textFieldValue = $0 }
                )
            )
            .style(.titleSmall)
            .foregroundColor(.textSecondaryTitle)
            .lineLimit(1)
            .focused($isFocused)
            .placeholder(when: value.isEmpty) {
                Text(placeholderText)
                    .style(.titleSmall)
                    .lineLimit(1)
                    .foregroundColor(.hintColor)
                    .frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        alignment: .leading
                    )
            }
            .padding(.leading, 12)
            .padding(.trailing, 6)
            .frame(height: 56)
            .background(Color.textFieldBackground)
            .cornerRadius(8)
            .onChange(of: textFieldValue) { newValue in
                onValueChanged(newValue)
            }
            
            Spacer()
            
            ZStack {
                Image(uiImage: SharedR.images().ic_save.toUIImage()!)
                    .resizable()
                    .renderingMode(.template)
                    .foregroundColor(.textLight)
                    .frame(width: 24, height: 24, alignment: .center)
                    .onTapGesture {
                        onSaveClick()
                    }
            }
            .frame(width: 36, height: 36, alignment: .center)
            .background(
                RoundedRectangle(cornerRadius: 12)
                    .fill(Color.buttonGradientStart)
            )
            .padding(.trailing, 12)
        }
        .frame(height: 64)
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color.textFieldBackground)
        )
        .padding(.horizontal, 16)
    }
}

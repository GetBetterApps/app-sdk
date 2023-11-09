//
//  NewCommentTextField.swift
//  iosApp
//
//  Created by velkonost on 09.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
 
struct NewCommentTextField: View {
    
    let minLines: Int
    @Binding private var value: String
    
    private let isEnabled: Bool
    private let textAlign: TextAlignment
    
    
    private let onValueChanged: (String) -> Void
    private let onSendClick: () -> Void
    
    @FocusState private var isFocused: Bool
    
    init(
        value: Binding<String>,
        minLines: Int = 1,
        isEnabled: Bool = true,
        textAlign: TextAlignment = .leading,
        onValueChanged: @escaping (String) -> Void,
        onSendClick: @escaping () -> Void
    ) {
        self._value = value
        self.minLines = minLines
        self.textAlign = textAlign
        self.isEnabled = isEnabled
        
        self.onValueChanged = onValueChanged
        self.onSendClick = onSendClick
        
        self.textFieldValue = value.wrappedValue
    }
    
    @State private var textFieldValue: String
    
    var body: some View {
        
        VStack(spacing: 0) {
            ZStack {}
            .frame(minWidth: 0, maxWidth: .infinity)
            .frame(height: 1)
            .background(
                Rectangle()
                    .fill(Color.buttonGradientStart)
            )
            
            HStack(spacing: 0) {
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
                        Text(SharedR.strings().note_detail_add_comment_hint.desc().localized())
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
                    .onChange(of: textFieldValue) { newValue in
                        onValueChanged(newValue)
                    }
                    .disabled(!isEnabled)
//                    .ignoresSafeArea(.keyboard, edges: .bottom)
                
                Spacer()
                
                ZStack {
                    Image(uiImage: SharedR.images().ic_send.toUIImage()!)
                        .resizable()
                        .renderingMode(.template)
                        .foregroundColor(.textLight)
                        .frame(width: 18, height: 18)
                }
                .frame(width: 32, height: 32, alignment: .center)
                .background(
                    RoundedRectangle(cornerRadius: 12)
                        .fill(Color.buttonGradientStart)
                        .shadow(radius: 8)
                )
                .onTapGesture {
                    onSendClick()
                }
                
                Spacer()
            
            }
            
            Spacer()
                .frame(height: 40)
                .ignoresSafeArea(.all, edges: .bottom)
            
            
        }
        .background(
            Rectangle().fill(Color.textFieldBackground)
        )
  
    }
}

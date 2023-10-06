//
//  DisabledAppButton.swift
//  iosApp
//
//  Created by velkonost on 06.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct DisabledAppButton: View {
    
    private let labelText: String
    
    init(labelText: String) {
        self.labelText = labelText
    }
    
    var body: some View {
        Button {
        } label: {
            
            Text(labelText.uppercased())
                .foregroundColor(.textButtonDisabled)
                .style(.titleMedium)
                .frame(
                    width: UIScreen.screenWidth * 0.8,
                    height: 42,
                    alignment: .center
                )
                .background(
                    RoundedRectangle(cornerRadius: 48)
                        .fill(Color.buttonDisabled)
                )
                .transition(.opacity)
                .id("disabledAppButton")
        }
        .disabled(true)
        
    }
}

//
//  AppButton.swift
//  iosApp
//
//  Created by velkonost on 10.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AppButton: View {
    
    private let labelText: String
    private let onClick: () -> Void
    
    init(labelText: String, onClick: @escaping () -> Void) {
        self.labelText = labelText
        self.onClick = onClick
    }
    
    var body: some View {
        Button {
            //onclick
        } label: {
            Text(labelText)
//                .foregroundColor(
//                    SwiftUI.Color(SharedR.colors.shared.button_text_color.getUIColor())
//                )
                .style(.titleMedium)
                .frame(
                    maxWidth: .infinity,
                    minHeight: 64,
                    maxHeight: 64
                )
                
        }
        
        .foregroundColor(.white)
    
        .background(
            LinearGradient(
                gradient: .init(colors: [
                    SwiftUI.Color(SharedR.colors.shared.button_gradient_start.getUIColor()),
                    SwiftUI.Color(SharedR.colors.shared.button_gradient_end.getUIColor())
                ]),
                startPoint: .leading,
                endPoint: .trailing
            )
        )
        .clipShape(RoundedRectangle(cornerRadius: 24))
    }
}


//
//  WhiteButton.swift
//  iosApp
//
//  Created by velkonost on 13.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct WhiteButton: View {
    
    private let labelText: String
    private let isLoading: Bool
    private let onClick: () -> Void
    private let height: Int
    
    init(labelText: String, isLoading: Bool, onClick: @escaping () -> Void, height: Int = 64) {
        self.labelText = labelText
        self.isLoading = isLoading
        self.onClick = onClick
        self.height = height
    }
    
    var body: some View {
        Button {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onClick()
        } label: {
            
            if (isLoading) {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: Color.textButtonEnabled))
                    .frame(
                        width: UIScreen.screenWidth * 0.8,
                        height: CGFloat(height),
                        alignment: .center
                    )
                    .background(
                        RoundedRectangle(cornerRadius: 48)
                            .fill(Color.textLight)
                            .shadow(radius: 8)
                        
                    )
            } else {
                Text(labelText.uppercased())
                    .foregroundColor(.textButtonEnabled)
                    .style(.titleMedium)
                    .frame(
                        width: UIScreen.screenWidth * 0.8,
                        height: 64,
                        alignment: .center
                    )
                    .background(
                        RoundedRectangle(cornerRadius: 48)
                            .fill(Color.textLight)
                            .shadow(radius: 8)
                        
                    )
                    .transition(.opacity)
                    .id("whiteButton")
            }
        }
        .disabled(isLoading)
    }
}

//
//  AvatarPlaceholder.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AvatarPlaceholder: View {
    
    let onClick: () -> Void
    
    init(onClick: @escaping () -> Void) {
        self.onClick = onClick
    }
    
    var body: some View {
        
        ZStack {
            Image(uiImage: SharedR.images().ic_image_placeholder.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .frame(width: 64, height: 64, alignment: .center)
                .foregroundColor(.textLight).opacity(0.5)
        }
        .frame(width: 128, height: 128)
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(
                    LinearGradient(
                        colors: [.onboardingBackgroundGradientStart, .onboardingBackgroundGradientEnd],
                        startPoint: .trailing, endPoint: .leading
                    )
                )
                .shadow(radius: 8)
        )
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onClick()
        }
        
    }
}

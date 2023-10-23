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

struct Avatar: View {
    
    let isLoading: Bool
    let avatarBytes: KotlinByteArray?
    let onClick: () -> Void
    
    init(isLoading: Bool, avatarBytes: KotlinByteArray?, onClick: @escaping () -> Void) {
        self.isLoading = isLoading
        self.avatarBytes = avatarBytes
        self.onClick = onClick
    }
    
    var body: some View {
        
        ZStack {
            if (isLoading) {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: Color.textLight))
                    .opacity(0.5)
                    .frame(width: 64, height: 64, alignment: .center)
                    .scaleEffect(2)
            } else if (avatarBytes != nil) {
                Image(uiImage: UIImage(data: avatarBytes!.toData()!)!)
                    .resizable()
                    .scaledToFill()
                    .frame(width: 128, height: 128)
                    .clipped()
                    .cornerRadius(12)
            } else {
                Placeholder()
            }
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
            if !isLoading {
                let impactMed = UIImpactFeedbackGenerator(style: .medium)
                impactMed.impactOccurred()
                onClick()
            }
        }
        
    }
}

struct Placeholder: View {
    var body: some View {
        Image(uiImage: SharedR.images().ic_image_placeholder.toUIImage()!)
            .resizable()
            .renderingMode(.template)
            .frame(width: 64, height: 64, alignment: .center)
            .foregroundColor(.textLight).opacity(0.5)
    }
}

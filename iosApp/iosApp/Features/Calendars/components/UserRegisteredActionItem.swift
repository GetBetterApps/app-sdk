//
//  UserRegisteredActionItem.swift
//  iosApp
//
//  Created by velkonost on 23.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import Lottie

struct UserRegisteredActionItem: View {
    var body: some View {
        PrimaryBox(
            padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero),
            topPadding: 0
        ) {
            
            ZStack {
                Image(uiImage: SharedR.images().logo.toUIImage()!)
                    .resizable()
                    .frame(width: 128, height: 128)
                    .clipped()
                    .cornerRadius(8)
                LottieView(isDark: false)
//                    .frame(width: size, height: size)
//                    .scaleEffect(0.2)
            }
            .frame(height: 180, alignment: .center)
            
        }
    }
}


struct ConfettiLottieView: UIViewRepresentable {
    
    var isDark: Bool

    func updateUIView(_ uiView: UIViewType, context: Context) {
        
    }

    func makeUIView(context: Context) -> Lottie.LottieAnimationView {
        
        let animationView = LottieAnimationView(
            filePath: SharedR.files().confetti.path
        )
        
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = .loop
        animationView.play()
        
        return animationView
    }
}

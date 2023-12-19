//
//  PlaceholderView.swift
//  iosApp
//
//  Created by velkonost on 19.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import Lottie
import SharedSDK

struct PlaceholderView: View {
    
    private let text: String
    init(text: String) {
        self.text = text
    }
    
    
    var body: some View {
        VStack(spacing: 0) {
            Spacer()
            PlaceholderLottieView()
                .scaleEffect(0.4)
            Text(text)
                .style(.bodyMedium)
                .foregroundColor(.textTitle)
                .padding(.top, 12)
            Spacer()
        }
    }
}


struct PlaceholderLottieView: UIViewRepresentable {
    
    let animations = [
        SharedR.files().anim_placeholder_1,
        SharedR.files().anim_placeholder_2,
        SharedR.files().anim_placeholder_3,
        SharedR.files().anim_placeholder_4,
        SharedR.files().anim_placeholder_5,
        SharedR.files().anim_placeholder_6
    ]

    func updateUIView(_ uiView: UIViewType, context: Context) {
        
    }

    func makeUIView(context: Context) -> Lottie.LottieAnimationView {
        
        let animationView = LottieAnimationView(
            filePath: animations.randomElement()!.path
        )
        
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = .loop
        animationView.play()
        
        return animationView
    }
}

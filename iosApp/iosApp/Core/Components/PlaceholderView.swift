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
        VStack {
            Spacer()
            PlaceholderLottieView()
                .scaleEffect(0.4)
            Spacer()
        }
    }
}


struct PlaceholderLottieView: UIViewRepresentable {

    func updateUIView(_ uiView: UIViewType, context: Context) {
        
    }

    func makeUIView(context: Context) -> Lottie.LottieAnimationView {
        
        let animationView = LottieAnimationView(
            filePath: SharedR.files().anim_placeholder_6.path,
            
            configuration: .init(renderingEngine: .mainThread)
        )
        
        
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = .loop
        animationView.play()
        
        return animationView
    }
}

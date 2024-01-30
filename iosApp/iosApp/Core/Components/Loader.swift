//
//  Loader.swift
//  iosApp
//
//  Created by velkonost on 28.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import Lottie
    
struct Loader: View {
    @Environment(\.colorScheme) var colorScheme
    
    let size: CGFloat
    init(size: Int = 40) {
        self.size = CGFloat(size)
    }
    
    var body: some View {
        let isDark: Bool = colorScheme == .dark
        
        ZStack {
            if isDark {
                LottieView(isDark: true)
                    .frame(width: size, height: size)
                    .scaleEffect(0.3)
            } else{
                LottieView(isDark: false)
                    .frame(width: size, height: size)
                    .scaleEffect(0.3)
            }
            
        }
    }
}

struct LottieView: UIViewRepresentable {
    
    var isDark: Bool

    func updateUIView(_ uiView: UIViewType, context: Context) {
        
    }

    func makeUIView(context: Context) -> Lottie.LottieAnimationView {
        
        let animationView = LottieAnimationView(
            filePath: !isDark ? SharedR.files().loader_new_light.path : SharedR.files().loader_new_dark.path
        )
        
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = .loop
        animationView.play()
        
        return animationView
    }
}

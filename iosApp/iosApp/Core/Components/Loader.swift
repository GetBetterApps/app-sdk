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
    
    var body: some View {
        var isDark: Bool = colorScheme == .dark
        
        ZStack {
            if isDark {
                LottieView(isDark: true)
                    .frame(width: 40, height: 40)
                    .scaleEffect(0.2)
            } else{
                LottieView(isDark: false)
                    .frame(width: 40, height: 40)
                    .scaleEffect(0.2)
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
            filePath: isDark ? SharedR.files().loader_light.path : SharedR.files().loader_dark.path
        )
        
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = .loop
        animationView.play()
        
        return animationView
    }
}

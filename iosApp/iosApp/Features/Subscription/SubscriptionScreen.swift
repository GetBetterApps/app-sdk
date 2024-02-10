//
//  SubscriptionScreen.swift
//  iosApp
//
//  Created by velkonost on 10.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync
import Lottie

struct SubscriptionScreen: View {
    
    @StateViewModel var viewModel: SubscriptionViewModel
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! SubscriptionViewState
        
        ZStack(alignment: .topLeading) {
            
            LinearGradient(
                colors: [.onboardingBackgroundGradientStart, .onboardingBackgroundGradientEnd],
                startPoint: .top, endPoint: .bottom
            )
            
            
            VStack(alignment: .leading, spacing: .zero) {
                HStack {
                    Image(uiImage: SharedR.images().ic_close.toUIImage()!)
                        .resizable()
                        .renderingMode(.template)
                        .frame(width: 24, height: 24)
                        .foregroundColor(.textLight.opacity(0.4))
                    
                    Spacer()
                    
                    Text(SharedR.strings().paywall_restore.desc().localized())
                        .style(.titleSmall)
                        .foregroundColor(.textLight.opacity(0.4))
                }
                
                Image(uiImage: SharedR.images().ic_getbetter_light_.toUIImage()!)
                    .resizable()
                    .scaledToFit()
                    .opacity(0.4)
                    .padding(.top, 16)
                
            }
            .padding(.init(top: 50, leading: 16, bottom: 0, trailing: 16))
            
        }
        .edgesIgnoringSafeArea(.all)
    }
}

struct SubscriptionPoint: View {
    
    private let index: Int
    private let title: String
    
    init(title: String, index: Int) {
        self.title = title
        self.index = index
    }
    
    @State private var animVisible: Bool = false
    
    var body: some View {
        HStack(spacing: 0) {
            if animVisible {
                OnboardingLottieView(resource: SharedR.files().anim_mark.path)
                    .frame(width: 24, height: 24)
                    .scaleEffect(0.1)
            } else {
                Spacer().frame(width: 24, height: 24)
            }
            
            Text(title.uppercased())
                .style(.labelMedium)
                .foregroundColor(.textTitle)
                .multilineTextAlignment(.leading)
                .padding(.leading, 8)
            
            Spacer()
                
        }
        .padding(.top, 16)
        .padding(.horizontal, 16)
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 1 + 2 * Double(index)) {
                withAnimation(.bouncy) {
                    animVisible = true
                }
            }
            
        }
    }
}

struct SubscriptionLottieView: UIViewRepresentable {
    
    var resource: String

    var loopMode: LottieLoopMode = .playOnce
    
    func updateUIView(_ uiView: UIViewType, context: Context) {}

    func makeUIView(context: Context) -> Lottie.LottieAnimationView {
        let animationView = LottieAnimationView(filePath: resource)
        
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = loopMode
        animationView.play()
        
        return animationView
    }
}

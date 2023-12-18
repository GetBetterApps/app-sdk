//
//  OnboardingFifthStep.swift
//  iosApp
//
//  Created by velkonost on 18.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct OnboardingFifthStep: View {
    
    private let item: Affirmation
    private let text: String
    
    private let isActive: Bool
    @State var isScaled: Bool = false
    @State var isBlurred: Bool = false
    @State var isTextVisible: Bool = false
    
    init(item: Affirmation, isActive: Bool, text: String) {
        self.item = item
        self.text = text
        self.isActive = isActive
    }
    
    @State var blurRadius: CGFloat = 20
    
    var body: some View {
        ZStack {
            AsyncImage(
                url: URL(string: item.imageUrl),
                transaction: .init(animation: .easeInOut)
            ) { phase in
                
                switch phase {
                case .empty:
                    ProgressView()
                case .success(let image):
                    image
                        .resizable()
                        .scaledToFill()
                        .edgesIgnoringSafeArea(.all)
                        .blur(radius: isBlurred ? 0 : 20)
                        .scaleEffect(isScaled ? 1.2 : 1.02)
                        .frame(width: UIScreen.screenWidth, height: UIScreen.screenHeight)
                        .clipped()
                    
                case .failure(_):
                    EmptyView()
                @unknown default:
                    EmptyView()
                }
            }.edgesIgnoringSafeArea(.all)
            
            VStack {
                Spacer()
                OnboardingAffirmationText(text: text)
                    .opacity(isTextVisible ? 1 : 0)
                    .animation(.easeInOut.speed(0.5), value: isTextVisible)
                Spacer()
            }
        }
        .contentShape(Rectangle())
        .frame(width: UIScreen.screenWidth, height: UIScreen.screenHeight)
        .onAppear {
//            withAnimation(.easeInOut) {
                isTextVisible = true
//            }
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
                withAnimation(Animation.linear.speed(0.5)) {
                    isBlurred = true
                }
                
                withAnimation(Animation.linear.speed(0.3)) {
                    isScaled = true
                }
            }
        }
    }
}

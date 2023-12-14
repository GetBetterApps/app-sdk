//
//  AbilityMotivationItem.swift
//  iosApp
//
//  Created by velkonost on 14.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AbilityMotivationItem: View {
    
    private let item: Affirmation
    
    private let isActive: Bool
    private let isScaled: Bool
    private let isTextVisible: Bool
    
    init(item: Affirmation, isActive: Bool) {
        self.item = item
        self.isActive = isActive
        self.isScaled = isActive
        self.isTextVisible = isActive
    }
    
    @State var blurRadius: CGFloat = 20
    
    var body: some View {
        ZStack {
            AsyncImage(url: URL(string: item.imageUrl)) { image in
                image
                    .resizable()
                    .scaledToFill()
                    .edgesIgnoringSafeArea(.all)
                    .blur(radius: isActive ? 0 : 20)
                    .scaleEffect(isScaled ? 1.2 : 1.02)
                    .frame(width: UIScreen.screenWidth, height: UIScreen.screenHeight)
                    .clipped()
                    
            } placeholder: {
                
            }
            
            VStack {
                Spacer()
                AffirmationText(text: item.text)
                    .opacity(isTextVisible ? 1 : 0)
                Spacer()
            }
        }
        .frame(width: UIScreen.screenWidth, height: UIScreen.screenHeight)
        .animation(.easeInOut.delay(0.5).speed(0.5), value: isActive)
        .animation(.easeInOut.delay(1.5).speed(0.5), value: isScaled)
        .animation(.easeInOut.speed(2), value: isTextVisible)
    }
}

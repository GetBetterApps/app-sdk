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
    
    init(item: Affirmation, isActive: Bool) {
        self.item = item
        self.isActive = isActive
        self.blurRadius = blurRadius
    }
    
    @State var blurRadius: CGFloat = 20
    
    var body: some View {
        ZStack {
            AsyncImage(url: URL(string: item.imageUrl)) { image in
                
                image
                    .resizable()
                    .frame(width: UIScreen.screenWidth, height: UIScreen.screenHeight)
//                            .scaledToFit()
                    .scaledToFill()
                    .blur(radius: isActive ? 0 : 20)
                    .edgesIgnoringSafeArea(.all)
                    
            } placeholder: {
                
            }
        }
        .animation(.easeInOut, value: isActive)
        
    }
}

//
//  AbilityMotivationContent.swift
//  iosApp
//
//  Created by velkonost on 14.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import SwiftUIPager

struct AbilityMotivationContent: View {
    
    private let items: [Affirmation]
    private let isActive: Bool
    private let itemFavoriteClick: (Affirmation) -> Void
    
    init(items: [Affirmation], isActive: Bool, itemFavoriteClick: @escaping (Affirmation) -> Void) {
        self.items = items
        self.isActive = isActive
        self.itemFavoriteClick = itemFavoriteClick
        self.isBlurred = isBlurred
        self.isScaled = isScaled
    }
    
    @StateObject var page: Page = .first()
    
    @State var isBlurred: Bool = true
    @State var isScaled: Bool = true
    
    var body: some View {
        ZStack {
            Color.mainBackground.edgesIgnoringSafeArea(.all)
            
            AsyncImage(url: URL(string: items[page.index].imageUrl)) { image in
                image
                    .resizable()
                    .scaledToFill()
                    .edgesIgnoringSafeArea(.all)
                    .blur(radius: isBlurred ? 0 : 20)
                    .scaleEffect(isScaled ? 1.2 : 1.02)
                    .transition(.opacity.animation(.default))
                    .frame(width: UIScreen.screenWidth, height: UIScreen.screenHeight)
                    .clipped()
            } placeholder: {
                
            }.edgesIgnoringSafeArea(.all)
            
            Pager(
                page: page,
                data: items,
                id: \.self.id
            ) { item in
                AbilityMotivationItem(
                    item: item,
                    isActive: items[page.index].id == item.id
                )
            }
            .vertical()
            .sensitivity(.medium)
            .preferredItemSize(UIScreen.screenSize)
            .onPageWillChange({ page in
                isBlurred = false
                isScaled = false
                
                DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
                    withAnimation(Animation.linear.speed(0.5)) {
                        isBlurred = true
                    }
                    
                    withAnimation(Animation.linear.speed(0.3)) {
                        isScaled = true
                    }
                }
            })
            .edgesIgnoringSafeArea(.all)
            
            if !items.isEmpty {
                VStack {
                    Spacer()
                    HStack(spacing: 0) {
                        Spacer()
                        
                        Image(uiImage: items[page.index].isFavorite ? SharedR.images().ic_star.toUIImage()! : SharedR.images().ic_empty_star.toUIImage()!)
                            .resizable()
                            .renderingMode(.template)
                            .foregroundColor(.iconActive)
                            .frame(width: 26, height: 26)
                            .padding(10)
                            .background(
                                RoundedRectangle(cornerRadius: 12)
                                    .fill(Color.backgroundIcon.opacity(0.6))
                                    .shadow(radius: 8)
                            )
                            .padding(.trailing, 12)
                            .onTapGesture {
                                itemFavoriteClick(items[page.index])
                            }
                        
                        Image(uiImage: SharedR.images().ic_share.toUIImage()!)
                            .resizable()
                            .renderingMode(.template)
                            .foregroundColor(.iconActive)
                            .frame(width: 26, height: 26)
                            .padding(10)
                            .background(
                                RoundedRectangle(cornerRadius: 12)
                                    .fill(Color.backgroundIcon.opacity(0.6))
                                    .shadow(radius: 8)
                            )
                            .padding(.trailing, 24)
                    }
                    .padding(.bottom, 48)
                }
            }
        }
        
    }
}

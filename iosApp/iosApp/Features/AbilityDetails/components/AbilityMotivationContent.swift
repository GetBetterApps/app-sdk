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
    
    init(items: [Affirmation], isActive: Bool) {
        self.items = items
        self.isActive = isActive
    }
    
    @StateObject var page: Page = .first()
    
    var body: some View {
        ZStack {
            Color.mainBackground.edgesIgnoringSafeArea(.all)
            
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
            .edgesIgnoringSafeArea(.all)
            
            VStack {
                Spacer()
                HStack(spacing: 0) {
                    Spacer()
                    
                    Image(uiImage: SharedR.images().ic_empty_star.toUIImage()!)
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
                .padding(.bottom, 32)
            }
        }
        
    }
}

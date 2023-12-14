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
            .sensitivity(.low)
//            .interactive(rotation: true)
//            .preferredItemSize(UIScreen.screenSize)
            .edgesIgnoringSafeArea(.all)
            
            
//            .alignment(.center)
        }
        
       
        
    }
}

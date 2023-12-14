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
    @StateObject var page: Page = .first()
    
    var body: some View {
        Pager(
            page: page,
            data: items,
            id: \.self.id
        ) { item in
            
            
        }
        .vertical()
        .interactive(rotation: true)
                .alignment(.center)
        
       
        
    }
}

//
//  AreaPickerItem.swift
//  iosApp
//
//  Created by velkonost on 25.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AreaPickerItem: View {
    
    let area: Area
    
    init(area: Area) {
        self.area = area
    }
    
    var body: some View {
        ZStack {
            VStack {
                Image(uiImage: Emoji.companion.getIconById(id: area.emojiId as! Int32).toUIImage()!)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 64, height: 64)
                
                Text(area.name)
                    .style(.titleLarge)
                    .foregroundColor(.textPrimary)
                    .padding(.top, 12)
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity)
        .padding(16)
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color.textFieldBackground)
        )
    }
}

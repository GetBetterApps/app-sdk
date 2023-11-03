//
//  AreaData.swift
//  iosApp
//
//  Created by velkonost on 03.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AreaData : View {
    
    let area: Area
    let onClick: () -> Void
    
    init(area: Area, onClick: @escaping () -> Void) {
        self.area = area
        self.onClick = onClick
    }
    
    var body: some View {
        PrimaryBox {
            HStack {
                Image(uiImage: Emoji.companion.getIconById(id: Int32(truncating: area.emojiId!)).toUIImage()!)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 32, height: 32)
                
                Text(area.name)
                    .style(.titleMedium)
                    .lineLimit(1)
                    .foregroundColor(.textPrimary)
                    .padding(.leading, 6)
                    .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                
            }
            .padding(16)
            .frame(minWidth: 0, maxWidth: .infinity)
            .contentShape(Rectangle())
            .onTapGesture {
                let impactMed = UIImpactFeedbackGenerator(style: .medium)
                impactMed.impactOccurred()
                onClick()
            }
        }
        .padding(0)
    }
}

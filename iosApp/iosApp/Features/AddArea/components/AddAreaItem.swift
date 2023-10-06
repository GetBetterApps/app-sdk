//
//  AddAreaItem.swift
//  iosApp
//
//  Created by velkonost on 06.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AddAreaItem: View {
    
    private let item: Area
    
    init(item: Area) {
        self.item = item
    }
    
    var body: some View {
        PrimaryBox {
            HStack {
                Image(uiImage: Emoji.companion.getIconById(id: item.emojiId as! Int32).toUIImage()!)
                    .resizable()
                    .padding(8)
                    .frame(width: 64, height: 64)
                    .scaledToFill()
                    
                
                VStack(alignment: .leading) {
                    Spacer()
                    Text(item.name)
                        .style(.labelLarge)
                        .foregroundColor(.textTitle)
                        .multilineTextAlignment(.leading)
                    
                    Text(item.description_)
                        .style(.bodySmall)
                        .foregroundColor(.textSecondaryTitle)
                        .lineLimit(2)
                        .fixedSize(horizontal: false, vertical: true)
                        .padding(.top, 4)
                        .multilineTextAlignment(.leading)
                    
                    Spacer()
                }
                .padding(.leading, 12)
                Spacer()
            }.frame(minWidth: 0, maxWidth: .infinity)
        }
        .frame(minWidth: 0, maxWidth: .infinity)
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
//            onClick()
        }
    }
}

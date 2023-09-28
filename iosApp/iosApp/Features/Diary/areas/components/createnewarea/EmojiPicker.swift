//
//  EmojiPicker.swift
//  iosApp
//
//  Created by velkonost on 28.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct EmojiPicker: View {
    
    @Binding private var isVisible: Bool
    let items: [Emoji]
    let onEmojiClick: (Emoji) -> Void
    
    init(isVisible: Binding<Bool>, items: [Emoji], onEmojiClick: @escaping (Emoji) -> Void) {
        self._isVisible = isVisible
        self.items = items
        self.onEmojiClick = onEmojiClick
    }
    
    var body: some View {
        ZStack {
            if isVisible {
                PrimaryBox {
                    
                    ScrollView(.horizontal, showsIndicators: false) {
                        LazyHGrid(rows: [GridItem(.adaptive(minimum: 40, maximum: 50))]) {
                            ForEach(items, id: \.self) { item in
                                Image(uiImage: item.icon.toUIImage()!)
                                    .resizable()
                                    .padding(8)
                                    .frame(width: 50, height: 50)
                                    .onTapGesture {
                                        let impactMed = UIImpactFeedbackGenerator(style: .medium)
                                        impactMed.impactOccurred()
                                        onEmojiClick(item)
                                    }
                            }
                        }.frame(height: 150)
                    }
                    .mask(LinearGradient(gradient: Gradient(stops: [
                        .init(color: .clear, location: 0),
                        .init(color: .black, location: 0.1),
                        .init(color: .black, location: 0.75),
                        .init(color: .clear, location: 1)
                    ]), startPoint: .leading, endPoint: .trailing))
                }
            }
        }
    }
}

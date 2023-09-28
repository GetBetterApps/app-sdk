//
//  SelectedEmojiImage.swift
//  iosApp
//
//  Created by velkonost on 28.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct SelectedEmojiImage: View {
    
    let selectedEmojiImage: UIImage
    let onClick: () -> Void
    
    init(selectedEmoji: UIImage, onClick: @escaping () -> Void) {
        self.selectedEmojiImage = selectedEmoji
        self.onClick = onClick
    }
    
    var body: some View {
        ZStack {
            Image(uiImage: selectedEmojiImage)
                .resizable()
                .padding(8)
                .frame(width: 48, height: 48)
                
                .background(
                    RoundedRectangle(cornerRadius: 12)
                        .fill(Color.textFieldBackground)
                )
        }
        .frame(width: 64, height: 64)
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color.backgroundItem)
                .shadow(radius: 8)
        )
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onClick()
        }
    }
}

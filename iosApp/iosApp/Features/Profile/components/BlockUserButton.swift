//
//  BlockUserButton.swift
//  iosApp
//
//  Created by velkonost on 19.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct BlockUserButton: View {
    let onClick: () -> Void
    
    init(onClick: @escaping () -> Void) {
        self.onClick = onClick
    }
    
    var body: some View {
        ZStack {
            Image(uiImage: SharedR.images().ic_warning.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .frame(width: 24, height: 24, alignment: .center)
                .foregroundColor(.iconActive).opacity(0.5)
        }
        .frame(width: 48, height: 48)
        .background(
            RoundedRectangle(cornerRadius: 8)
                .fill(Color.backgroundIcon)
                .shadow(radius: 8)
        )
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onClick()
        }
    }
}

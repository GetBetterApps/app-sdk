//
//  MenuItem.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct MenuItem: View {
    let title: String
    let icon: UIImage
    let onClick: () -> Void
    
    init(title: String, icon: UIImage, onClick: @escaping () -> Void) {
        self.title = title
        self.icon = icon
        self.onClick = onClick
    }
    
    var body: some View {
        HStack {
            Image(uiImage: icon)
                .resizable()
                .renderingMode(.template)
                .padding(.init(top: 8, leading: 8, bottom: 8, trailing: 8))
                .frame(width: 42, height: 42, alignment: .center)
                .foregroundColor(.iconInactive)

                .background(
                    RoundedRectangle(cornerRadius: 12)
                        .fill(Color.backgroundIcon)
                )
            
            Text(title)
                .style(.labelLarge)
                .foregroundColor(.textPrimary)
                .padding(.init(top: .zero, leading: 12, bottom: .zero, trailing: .zero))
            
            Spacer()
            
            Image(uiImage: SharedR.images().ic_arrow_right.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .foregroundColor(.iconInactive)
                .frame(width: 24, height: 24)
        }
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onClick()
        }
    }
}

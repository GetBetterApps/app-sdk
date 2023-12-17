//
//  PrimaryBox.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct PrimaryBox<Content: View>: View {
    
    var padding: EdgeInsets = .init(top: 16, leading: 16, bottom: 16, trailing: 16)
    var topPadding = 8
    var isBright: Bool = false
    @ViewBuilder let content: Content
    
    var body: some View {
        ZStack {
            content
        }
        .padding(padding)
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(isBright ? Color.buttonGradientStart : Color.backgroundItem)
                .shadow(radius: 8)
        )
        .padding(.init(top: CGFloat(topPadding), leading: .zero, bottom: .zero, trailing: .zero))
        
    }
}

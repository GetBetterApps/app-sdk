//
//  TagItem.swift
//  iosApp
//
//  Created by velkonost on 26.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TagItem : View {
    
    let tag: String
    let onTagDelete: (String) -> Void
    
    init(tag: String, onTagDelete: @escaping (String) -> Void) {
        self.tag = tag
        self.onTagDelete = onTagDelete
    }
    
    var body: some View {
        HStack(spacing: 0) {
            Text(tag)
                .style(.bodyMedium)
                .foregroundColor(.textSecondaryTitle)
                .frame(height: 24, alignment: .center)
            
            Image(uiImage: SharedR.images().ic_close.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .foregroundColor(.textSecondaryTitle)
                .scaledToFit()
                .frame(width: 14, height: 14, alignment: .center)
                .padding(.leading, 4)
                .onTapGesture {
                    onTagDelete(tag)
                }
        }
        .padding(.init(top: 3, leading: 6, bottom: 3, trailing: 6))
        .frame(height: 24)
        .background(
            RoundedRectangle(cornerRadius: 8)
                .fill(Color.buttonGradientStart)
        )
    }
}

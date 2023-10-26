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
    
    let tag: TagUI
    let onTagDelete: (String) -> Void
    
    init(tag: TagUI, onTagDelete: @escaping (String) -> Void) {
        self.tag = tag
        self.onTagDelete = onTagDelete
    }
    
    var body: some View {
        HStack(spacing: 0) {
            Text(tag.text)
                .style(.bodyMedium)
                .foregroundColor(.textSecondaryTitle)
                .frame(height: 30, alignment: .center)
            
            Image(uiImage: SharedR.images().ic_close.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .foregroundColor(.textSecondaryTitle)
                .scaledToFit()
                .frame(width: 18, height: 18, alignment: .center)
                .padding(.leading, 4)
                .onTapGesture {
                    onTagDelete(tag.text)
                }
        }
        .padding(.init(top: 3, leading: 6, bottom: 3, trailing: 6))
        .frame(height: 30)
        .background(
            RoundedRectangle(cornerRadius: 8)
                .fill(Color.buttonGradientStart)
        )
    }
}

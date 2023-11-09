//
//  CommentItem.swift
//  iosApp
//
//  Created by velkonost on 09.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct CommentItem: View {
    
    let item: Comment
    let onDeleteClick: (Comment) -> Void
    
    init(item: Comment, onDeleteClick: @escaping (Comment) -> Void) {
        self.item = item
        self.onDeleteClick = onDeleteClick
    }
    
    var body: some View {
        VStack(spacing: 0) {
            HStack(spacing: 0) {
                if item.author.avatar != nil {
                    Image(uiImage: UIImage(data: item.author.avatar!.toData()!)!)
                        .resizable()
                        .scaledToFill()
                        .frame(width: 28, height: 28)
                        .clipped()
                        .cornerRadius(8)
                } else {
                    Image(uiImage: SharedR.images().logo.toUIImage()!)
                        .resizable()
                        .frame(width: 28, height: 28)
                        .clipped()
                        .cornerRadius(8)
                }
                
                if item.author.displayName != nil {
                    Text(item.author.displayName!)
                        .style(.bodyLarge)
                        .foregroundColor(.textPrimary)
                        .padding(.leading, 6)
                }
                
                Spacer()
                
                if item.allowEdit {
                    Image(uiImage: SharedR.images().ic_trash.toUIImage()!)
                        .resizable()
                        .renderingMode(.template)
                        .foregroundColor(.iconInactive)
                        .padding(4)
                        .frame(width: 24, height: 24)
                }
            }
            .padding(.top, 6)
            .padding(.horizontal, 4)
            
            HStack {
                Text(item.text)
                    .style(.bodyMedium)
                    .foregroundColor(.textSecondaryTitle)
                    .padding(.top, 12)
                    .padding(.leading, 12)
                Spacer()
            }
            
            HStack {
                Text(item.createdDateStr.localized())
                    .style(.labelSmall)
                    .foregroundColor(.textPrimary)
                    .padding(.top, 6)
                    .padding(.leading, 12)
                Spacer()
            }
        }
//        .padding(.top, 16)
        .padding(.leading, 8)
    
    }
}

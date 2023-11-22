//
//  NoteActionItem.swift
//  iosApp
//
//  Created by velkonost on 22.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import SwiftUIFlow

struct NoteActionItem: View {
    
    let item: Note
    let comment: Comment?
    let subGoalText: String?
    let onClick: (Note) -> Void
    let onLikeClick: (Note) -> Void
    
    init(item: Note, comment: Comment? = nil, subGoalText: String? = nil, onClick: @escaping (Note) -> Void, onLikeClick: @escaping (Note) -> Void) {
        self.item = item
        self.comment = comment
        self.subGoalText = subGoalText
        self.onClick = onClick
        self.onLikeClick = onLikeClick
    }
    
    var body: some View {
        PrimaryBox(topPadding: 0) {
            VStack(spacing: 0) {
                
                NoteItemHeader(
                    areaName: item.area.name,
                    taskName: nil,
                    areaIcon: Emoji.companion.getIconById(id: Int32(truncating: item.area.emojiId!)).toUIImage()!,
                    showLikes: !item.isPrivate,
                    likesData: item.likesData,
                    onLikeClick: {
                        onLikeClick(item)
                    }
                )
                
                VStack(alignment: .leading) {
                    
                    NoteItemData(
                        noteType: item.noteType,
                        completionDate: item.completionDateStr?.localized(),
                        expectedCompletionDate: item.expectedCompletionDateStr?.localized(),
                        expectedCompletionDateExpired: item.expectedCompletionDateExpired,
                        subNotes: item.subNotes,
                        mediaAmount: item.mediaUrls.count,
                        isPrivate: item.isPrivate
                    )
                    
                    Text(item.text)
                        .style(.bodyMedium)
                        .foregroundColor(.textTitle)
                        .lineLimit(10)
                        .multilineTextAlignment(.leading)
                        .frame(minWidth: 0, maxWidth: .infinity)
                        .padding(.top, 12)
                    
                    VFlow(alignment: .leading, spacing: 3) {
                        ForEach(item.tags, id: \.self) { tag in
                            TagItem(tag: TagUI(id: "0", text: tag))
                        }
                    }
                    .padding(.top, 12)
                }
                .padding(8)
                .background(
                    RoundedRectangle(cornerRadius: 12)
                        .fill(Color.textFieldBackground)
                )
                .padding(.top, 12)
                
                if comment != nil {
                    CommentItem
                }
                
                if subGoalText != nil {
                    SubGoalItem
                }
            }
        }
        .onTapGesture {
            onClick(item)
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
        }
    }
}

extension NoteActionItem {
    var CommentItem: some View {
        HStack(spacing: 0) {
            if comment!.author.avatarUrl != nil {
                AsyncImage(url: URL(string: comment!.author.avatarUrl!)) { image in
                    image
                        .resizable()
                        .scaledToFill()
                        .frame(width: 28, height: 28)
                        .clipped()
                        .cornerRadius(8)
                } placeholder: {
                    Image(uiImage: SharedR.images().logo.toUIImage()!)
                        .resizable()
                        .frame(width: 28, height: 28)
                        .clipped()
                        .cornerRadius(8)
                }
               
            } else {
                Image(uiImage: SharedR.images().logo.toUIImage()!)
                    .resizable()
                    .frame(width: 28, height: 28)
                    .clipped()
                    .cornerRadius(8)
            }
            
            Text(comment!.text)
                .style(.bodySmall)
                .foregroundColor(.textLight)
                .padding(6)
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .fill(Color.buttonGradientStart)
                )
                .padding(.leading, 6)
                
            
            Spacer()
        }
        .padding(.top, 12)
    }
    
    var SubGoalItem: some View {
        HStack(spacing: 0) {
            Image(uiImage: SharedR.images().ic_save.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .foregroundColor(.textLight)
                .frame(width: 16, height: 16)
            
            Text(subGoalText!)
                .style(.bodySmall)
                .foregroundColor(.textLight)
                .padding(.leading, 6)
            
            Spacer()
        }
        .padding(6)
        .background(
            RoundedRectangle(cornerRadius: 8)
                .fill(Color.buttonGradientStart)
        )
        .padding(.top, 12)
    }
}

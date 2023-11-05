//
//  NoteItemData.swift
//  iosApp
//
//  Created by velkonost on 29.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct NoteItemData: View {

    let noteType: NoteType
    let completionDate: String?
    let expectedCompletionDate: String?
    let expectedCompletionDateExpired: Bool
    let subNotes: [SubNote]
    let mediaAmount: Int
    let isPrivate: Bool
    
    init(noteType: NoteType, completionDate: String?, expectedCompletionDate: String?, expectedCompletionDateExpired: Bool, subNotes: [SubNote], mediaAmount: Int, isPrivate: Bool) {
        self.noteType = noteType
        self.completionDate = completionDate
        self.expectedCompletionDate = expectedCompletionDate
        self.expectedCompletionDateExpired = expectedCompletionDateExpired
        self.subNotes = subNotes
        self.mediaAmount = mediaAmount
        self.isPrivate = isPrivate
    }

    var body: some View {
        HStack(spacing: 0) {
            let noteTypeImage = if noteType == NoteType.default_ {
                SharedR.images().ic_note.toUIImage()!
            } else {
                SharedR.images().ic_goal.toUIImage()!
            }
            
            Image(uiImage: noteTypeImage)
                .resizable()
                .renderingMode(.template)
                .scaledToFit()
                .padding(4)
                .frame(width: 24, height: 24)
                .foregroundColor(.iconInactive)
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .fill(Color.backgroundItem)
                )
            
            if (completionDate != nil || expectedCompletionDate != nil) && noteType == NoteType.goal {
                let completionDateBgColor =
                if completionDate != nil {
                    Color.green
                } else if expectedCompletionDateExpired {
                    Color.red
                } else {
                    Color.backgroundItem
                }
                
                let completionDateTextColor =
                if completionDate != nil || expectedCompletionDateExpired {
                    Color.textLight
                } else {
                    Color.textPrimary
                }
                
                Text((completionDate != nil) ? completionDate! : expectedCompletionDate!)
                    .style(.labelSmall)
                    .foregroundColor(completionDateTextColor)
                    .padding(.horizontal, 4)
                    .frame(height: 24)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .fill(completionDateBgColor)
                    )
                    .padding(.leading, 4)
            }
            
            if !subNotes.isEmpty && noteType == NoteType.goal {
                Text("\(subNotes.filter { $0.completionDate != nil }.count) / \(subNotes.count)")
                    .style(.labelSmall)
                    .foregroundColor(.textPrimary)
                    .padding(.horizontal, 4)
                    .frame(height: 24)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .fill(Color.backgroundItem)
                    )
                    .padding(.leading, 4)
            }
            Spacer()
            
            if mediaAmount != 0 {
                HStack(spacing: 0) {
                    Text("\(mediaAmount)")
                        .style(.labelSmall)
                        .foregroundColor(.textPrimary)
                        .padding(.trailing, 2)
                    
                    Image(uiImage: SharedR.images().ic_media.toUIImage()!)
                        .resizable()
                        .renderingMode(.template)
                        .scaledToFit()
                        .padding(4)
                        .frame(width: 24, height: 24)
                        .foregroundColor(.iconInactive)
                    
                }
                .padding(.horizontal, 4)
                .frame(height: 24)
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .fill(Color.backgroundItem)
                )
                .padding(.trailing, 4)
            }
            
            if isPrivate {
                Image(uiImage: SharedR.images().ic_lock.toUIImage()!)
                    .resizable()
                    .renderingMode(.template)
                    .scaledToFit()
                    .padding(4)
                    .frame(width: 24, height: 24)
                    .foregroundColor(.iconInactive)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .fill(Color.backgroundItem)
                    )
            }
        }
    }
}

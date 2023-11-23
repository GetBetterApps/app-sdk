//
//  ActionItem.swift
//  iosApp
//
//  Created by velkonost on 22.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct ActionItem: View {
    
    let item: ActionUIItem<AnyObject, AnyObject>
    let onAreaClick: (Int32) -> Void
    let onNoteClick: (Note) -> Void
    let onUserClick: () -> Void
    
    init(item: ActionUIItem<AnyObject, AnyObject>, onAreaClick: @escaping (Int32) -> Void, onNoteClick: @escaping (Note) -> Void, onUserClick: @escaping () -> Void) {
        self.item = item
        self.onAreaClick = onAreaClick
        self.onNoteClick = onNoteClick
        self.onUserClick = onUserClick
    }
    
    var body: some View {
        VStack(spacing: 0) {
            if item.description_ != nil {
                HStack {
                    Text(item.description_!.localized())
                        .style(.labelMedium)
                        .foregroundColor(.textLight)
                        .padding(.vertical, 4)
                        .padding(.horizontal, 20)
                        .background(
                            Rectangle()
                                .fill(Color.buttonGradientStart)
                                .clipShape(
                                    .rect(
                                        topLeadingRadius: 8,
                                        bottomLeadingRadius: 0,
                                        bottomTrailingRadius: 0,
                                        topTrailingRadius: 8
                                    )
                                )
                                .shadow(radius: 8)
                        )
                        .padding(.leading, 32)
                        .padding(.top, 12)
                    Spacer()
                }
            }
            
            if item.data is Int64 {
                UserRegisteredActionItem()
            } else if item.data is UserInfoShort {
                UserActionItem(
                    isLoading: false,
                    item: (item.data as! UserInfoShort),
                    onClick: onUserClick
                )
            } else if item.relatedData is Note {
                NoteActionItem(
                    item: (item.relatedData as! Note),
                    comment: item.data is Comment ? item.data as? Comment : nil,
                    subGoalText: item.data is SubNote ? (item.data as! SubNote).text : nil,
                    onClick: onNoteClick,
                    onLikeClick: { value in
                        
                    }
                )
            } else if item.data is Area {
                AreaActionItem(
                    item: (item.data as! Area),
                    onClick: onAreaClick,
                    onLikeClick: { value in
                        
                    }
                )
            } else if item.data is Note {
                NoteActionItem(
                    item: (item.data as! Note),
                    onClick: onNoteClick,
                    onLikeClick: { value in
                        
                    }
                )
            } else if item.relatedData is Area {
                AreaActionItem(
                    item: (item.relatedData as! Area),
                    onClick: onAreaClick,
                    onLikeClick: { value in
                        
                    }
                )
            }
        }
    }
}

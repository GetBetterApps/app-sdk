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
                Text(item.description_!.localized())
                    .style(.labelMedium)
                    .foregroundColor(.textLight)
                    .padding(.leading, 32)
                    .padding(.top, 12)
                    .background(
                        Rectangle()
                            .fill(Color.buttonGradientStart)
                            .clipShape(
                                .rect(
                                    topLeadingRadius: 0,
                                    bottomLeadingRadius: 20,
                                    bottomTrailingRadius: 0,
                                    topTrailingRadius: 20
                                )
                            )
                            .shadow(radius: 8)
                    )
            }
            
            
            if ((item.data?.isInstance(value: UserInfoShort.self)) != nil) {
                    UserActionItem(
                        isLoading: false,
                        item: (item.data as! UserInfoShort),
                        onClick: onUserClick
                    )
            } else if ((item.relatedData?.isInstance(value: Note.self)) != nil) {
                NoteActionItem(
                    item: (item.data as! Note),
                    onClick: onNoteClick,
                    onLikeClick: { value in
                        
                    }
                )
            } else if ((item.data?.isInstance(value: Area.self)) != nil) {
                AreaActionItem(
                    item: (item.data as! Area),
                    onClick: onAreaClick,
                    onLikeClick: { value in
                        
                    }
                )
            } else if ((item.data?.isInstance(value: Note.self)) != nil) {
                NoteActionItem(
                    item: (item.data as! Note),
                    onClick: onNoteClick,
                    onLikeClick: { value in
                        
                    }
                )
            } else if ((item.relatedData?.isInstance(value: Area.self)) != nil) {
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

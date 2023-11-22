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
    let onAreaClick: (Int) -> Void
    let onNoteClick: (Note) -> Void
    let onUserClick: () -> Void
    
    init(item: ActionUIItem<AnyObject, AnyObject>, onAreaClick: @escaping (Int) -> Void, onNoteClick: @escaping (Note) -> Void, onUserClick: @escaping () -> Void) {
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
            
            if item.data!.isInstance(value: UserInfoShort.self) {
                UserActionItem(isLoading: false, author: <#T##UserInfoShort?#>, onClick: <#T##() -> Void#>)
            }
            
//            switch(item) {
//            case item.data!.isInstance(value: UserInfoShort): do {
//                
//            }
//            default: EmptyView()
//            }
        }
    }
}

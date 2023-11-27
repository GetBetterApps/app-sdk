//
//  FeedbackItem.swift
//  iosApp
//
//  Created by velkonost on 25.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct FeedbackItem: View {
    
    let item: Feedback
    let onClick: () -> Void
    
    init(item: Feedback, onClick: @escaping () -> Void) {
        self.item = item
        self.onClick = onClick
    }
    
    var body: some View {
        PrimaryBox {
            VStack {
                HStack {
                    Text(item.type.uiContent.localized())
                        .style(.labelMedium)
                        .foregroundColor(.textPrimary)
                        .padding(.vertical, 4)
                        .padding(.horizontal, 6)
                        .background(
                            RoundedRectangle(cornerRadius: 8)
                                .fill(Color.backgroundIcon)
                        )
                        
                    
                    Spacer()
                    
                    Circle()
                        .fill(item.status == FeedbackStatus.opened ? Color.green : Color.red)
                        .frame(width: 12, height: 12)
                        
                }
                
                HStack {
                    Text(item.messages.first!.text)
                        .style(.bodyMedium)
                        .foregroundColor(.textPrimary)
                        .lineLimit(3)
                        .multilineTextAlignment(.leading)
                        .padding(.top, 12)
                    Spacer()
                }
            }  
        }
        .onTapGesture {
            onClick()
        }
        
    }
}

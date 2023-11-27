//
//  FeedbackMessageView.swift
//  iosApp
//
//  Created by velkonost on 27.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct FeedbackMessageView: View {
    
    let message: FeedbackMessage
    init(message: FeedbackMessage) {
        self.message = message
    }
    
    var body: some View {
        HStack(spacing: 0) {
            if message.fromUser {
                Spacer()
            }
            
            VStack(spacing: 0) {
                Text(message.text)
                    .style(.bodyMedium)
                    .foregroundColor(.textSecondaryTitle)
                    .multilineTextAlignment(message.fromUser ? .leading : .trailing)
                
                Text(message.datetimeStr.localized())
                    .style(.labelSmall)
                    .foregroundColor(.textPrimary)
                    .multilineTextAlignment(message.fromUser ? .leading : .trailing)
                    .padding(.top, 8)
            }
            .padding(6)
            .background(
                RoundedRectangle(cornerRadius: 12)
                    .fill(message.fromUser ? Color.buttonGradientStart : Color.backgroundItem)
                    .shadow(radius: 8)
            )
            
            if !message.fromUser {
                Spacer()
            }
            
        }
        .frame(maxWidth: .infinity)
        .padding(.top, 12)
        .padding(.horizontal, 16)
    }
}

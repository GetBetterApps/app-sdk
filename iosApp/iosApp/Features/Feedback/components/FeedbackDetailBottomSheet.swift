//
//  FeedbackDetailBottomSheet.swift
//  iosApp
//
//  Created by velkonost on 26.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct FeedbackDetailBottomSheet: View {
    
    let item: Feedback?
    let feedbackDetailsState: FeedbackDetailsState
    let onAnswerTextChanged: (String) -> Void
    let onAnswerSendClick: () -> Void
    
    init(item: Feedback?, feedbackDetailsState: FeedbackDetailsState, onAnswerTextChanged: @escaping (String) -> Void, onAnswerSendClick: @escaping () -> Void) {
        self.item = item
        self.feedbackDetailsState = feedbackDetailsState
        self.onAnswerTextChanged = onAnswerTextChanged
        self.onAnswerSendClick = onAnswerSendClick
    }
    
    var body: some View {
        ZStack {
            Color.mainBackground
            ScrollView(.vertical, showsIndicators: false) {
                VStack(spacing: 0) {
                    Spacer().frame(height: 32)
                    
                    ForEach(item?.messages ?? [], id: \.self.datetime) { message in
                        FeedbackMessageView(message: message)
                    }
                }
                .onTapGesture {
                    endTextEditing()
                }
            }
            
            VStack(spacing: 0) {
                Spacer()
                NewCommentTextField(
                    value: Binding(get: { feedbackDetailsState.answerText }, set: { value in }),
                    onValueChanged: onAnswerTextChanged,
                    onSendClick: onAnswerSendClick
                )
            }
            .ignoresSafeArea(.container, edges: .bottom)
        }
    }
}

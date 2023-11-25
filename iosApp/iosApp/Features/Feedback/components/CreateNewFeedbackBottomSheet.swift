//
//  CreateNewFeedbackBottomSheet.swift
//  iosApp
//
//  Created by velkonost on 25.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct CreateNewFeedbackBottomSheet: View {
    
    let newFeedbackState: NewFeedbackState
    let onTypeChanged: (FeedbackType) -> Void
    let onTextChanged: (String) -> Void
    let onCreateClick: () -> Void
    
    init(newFeedbackState: NewFeedbackState, onTypeChanged: @escaping (FeedbackType) -> Void, onTextChanged: @escaping (String) -> Void, onCreateClick: @escaping () -> Void) {
        self.newFeedbackState = newFeedbackState
        self.onTypeChanged = onTypeChanged
        self.onTextChanged = onTextChanged
        self.onCreateClick = onCreateClick
    }
    
    var body: some View {
        ZStack {
            Color.mainBackground
            
            if newFeedbackState.isLoading {
                VStack {
                    Loader()
                }
            } else {
                
                ScrollView(.vertical, showsIndicators: false) {
                    VStack {
                        Spacer().frame(height: 32)
                        
                        HStack {
                            FeedbackTypeView(
                                feedbackType: FeedbackType.feature,
                                selected: newFeedbackState.type == FeedbackType.feature,
                                onClick: {
                                    onTypeChanged(FeedbackType.feature)
                                }
                            )
                            
                            FeedbackTypeView(
                                feedbackType: FeedbackType.report,
                                selected: newFeedbackState.type == FeedbackType.report,
                                onClick: {
                                    onTypeChanged(FeedbackType.report)
                                }
                            )
                        }
                        
                        MultilineTextFieldBinding(
                            value: Binding(get: { newFeedbackState.text }, set: { _ in }),
                            placeholderText: 
                                newFeedbackState.type == FeedbackType.feature ? SharedR.strings().support_feature_hint.desc().localized() : SharedR.strings().support_report_hint.desc().localized()
                            ,
                            onValueChanged: onTextChanged
                        )
                    }
                    .padding(.horizontal, 16)
                    .onTapGesture {
                        endTextEditing()
                    }
                }
                
                VStack {
                    Spacer()
                    
                    VStack {
                        Spacer()
                            .frame(height: 20)
                        
                        AppButton(
                            labelText: SharedR.strings().diary_areas_create_button.desc().localized(),
                            isLoading: false,
                            onClick: onCreateClick
                        ) 
                        .padding(.bottom, 70)
                    }
                    .frame(minWidth: 0, maxWidth: .infinity)
                    .background(
                        Rectangle()
                            .fill(LinearGradient(gradient: Gradient(colors: [
                                .mainBackground,
                                .mainBackground,
                                .mainBackground,
                                .clear
                            ]), startPoint: .bottom, endPoint: .top)
                            )
                    )
                }.ignoresSafeArea(.all)
            }
        }
    }
}


struct FeedbackTypeView: View {
    let feedbackType: FeedbackType
    let selected: Bool
    let onClick: () -> Void
    
    init(feedbackType: FeedbackType, selected: Bool, onClick: @escaping () -> Void) {
        self.feedbackType = feedbackType
        self.selected = selected
        self.onClick = onClick
    }
    
    var body: some View {
        Text(feedbackType.uiContent.localized())
            .style(.labelMedium)
            .foregroundColor(selected ? .textLight : .textPrimary)
            .frame(maxWidth: .infinity)
            .padding(.vertical, 10)
            .background(
                RoundedRectangle(cornerRadius: 8)
                    .fill(selected ? Color.buttonGradientStart : Color.backgroundItem)
            )
            
            .onTapGesture {
                onClick()
            }
    }
}

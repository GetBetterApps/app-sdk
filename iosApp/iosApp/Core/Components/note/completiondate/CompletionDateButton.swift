//
//  CompletionDateButton.swift
//  iosApp
//
//  Created by velkonost on 04.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct CompletionDateButton: View {
    
    let label: String?
    let isLoading: Bool
    let onCompleteClick: () -> Void
    
    init(label: String?, isLoading: Bool, onCompleteClick: @escaping () -> Void) {
        self.label = label
        self.isLoading = isLoading
        self.onCompleteClick = onCompleteClick
    }
    
    var body: some View {
        HStack {
            if label == nil {
                Spacer()
                AppButton(
                    labelText: SharedR.strings().note_detail_complete_goal_text.desc().localized(),
                    isLoading: isLoading,
                    onClick: onCompleteClick
                )
                Spacer()
            }
        }
        .padding(.horizontal, 16)
        .padding(.bottom, 16)
        .animation(.easeInOut, value: label)
    }
}

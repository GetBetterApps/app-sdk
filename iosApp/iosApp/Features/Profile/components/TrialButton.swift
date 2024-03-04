//
//  TrialButton.swift
//  iosApp
//
//  Created by velkonost on 04.03.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TrialButton: View {
    
    private let isLoading: Bool
    private let onClick: () -> Void
    
    init(
        isLoading: Bool,
        onClick: @escaping () -> Void
    ) {
        self.isLoading = isLoading
        self.onClick = onClick
    }
    
    var body: some View {
        PrimaryBox(isBright: true) {
            HStack {
                Spacer()
                if isLoading {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle(tint: Color.textLight))
                        .frame(
                            width: UIScreen.screenWidth * 0.8,
                            height: 24,
                            alignment: .center
                        )
                } else {
                    Text(SharedR.strings().start_trial_button.desc().localized().uppercased())
                        .style(.titleMedium)
                        .foregroundColor(.textLight)
                }
                Spacer()
            }
        }
        .onTapGesture {
            onClick()
        }
    }
}

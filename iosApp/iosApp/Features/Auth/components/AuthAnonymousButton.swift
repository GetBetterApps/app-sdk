//
//  AuthAnonymousButton.swift
//  iosApp
//
//  Created by velkonost on 15.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AuthAnonymousButton: View {
    
    @State private var enabled: Bool = true
    private let onClick: () -> Void
    
    init(enabled: Bool, onClick: @escaping () -> Void) {
        self.enabled = enabled
        self.onClick = onClick
    }
    
    var body: some View {
        HStack {
            Spacer()
            Text(SharedR.strings().auth_later.desc().localized())
                .style(.titleSmall)
                .foregroundColor(.textLight)
                .padding(.init(top: 6, leading: 32, bottom: 6, trailing: 32))
                .background(
                    RoundedRectangle(cornerRadius: 48)
                        .fill(
                            LinearGradient(
                                colors: [.onboardingBackgroundGradientStart, .onboardingBackgroundGradientEnd],
                                startPoint: .trailing, endPoint: .leading
                            )
                        )
                        .shadow(radius: 8)
                )
                .onTapGesture {
                    onClick()
                }.disabled(!enabled)
        }
    }
}

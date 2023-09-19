//
//  SwitchRegisteringText.swift
//  iosApp
//
//  Created by velkonost on 15.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct SwitchRegisteringText: View {
    
    private let switchRegisteringText: String
    private let onClick: () -> Void
    
    init(_ switchRegisteringText: String, onClick: @escaping () -> Void) {
        self.switchRegisteringText = switchRegisteringText
        self.onClick = onClick
    }
    
    var body: some View {
        HStack {
            Spacer()
            Text(switchRegisteringText)
                .style(.titleSmall)
                .foregroundColor(.textLight).opacity(0.5)
                .frame(alignment: .trailing)
                .padding(.init(top: 12, leading: .zero, bottom: .zero, trailing: .zero))
                .onTapGesture {
                    let impactMed = UIImpactFeedbackGenerator(style: .medium)
                    impactMed.impactOccurred()
                    onClick()
                }
        }.animation(.easeInOut, value: switchRegisteringText)
    }
}

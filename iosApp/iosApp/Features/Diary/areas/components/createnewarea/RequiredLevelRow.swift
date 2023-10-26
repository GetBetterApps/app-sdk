//
//  RequiredLevelRow.swift
//  iosApp
//
//  Created by velkonost on 28.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct RequiredLevelRow: View {
    
    private let title: String
    private let level: Int
    private let onRequiredLevelChanged: (Int) -> Void
    
    init(title: String, level: Int, onRequiredLevelChanged: @escaping (Int) -> Void) {
        self.title = title
        self.level = level
        self.onRequiredLevelChanged = onRequiredLevelChanged
    }
    
    var body: some View {
        HStack(alignment: .center) {
            Text(title)
                .style(.titleMedium)
                .foregroundColor(.textPrimary)
            
            Spacer()
            
            Image(uiImage: SharedR.images().ic_plus.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .foregroundColor(.iconInactive)
                .frame(width: 32, height: 32)
                .padding(4)
                .background(Color.backgroundIcon)
                .cornerRadius(12)
                .onTapGesture {
                    let impactMed = UIImpactFeedbackGenerator(style: .medium)
                    impactMed.impactOccurred()
                    onRequiredLevelChanged(level - 1)
                }
            
            Text(String(level))
                .style(.titleMedium)
                .foregroundColor(.textSecondaryTitle)
                .padding(.leading, 16)
                .padding(.trailing, 16)
            
            Image(uiImage: SharedR.images().ic_plus.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .foregroundColor(.iconInactive)
                .frame(width: 32, height: 32)
                .padding(4)
                .background(Color.backgroundIcon)
                .cornerRadius(12)
                .onTapGesture {
                    let impactMed = UIImpactFeedbackGenerator(style: .medium)
                    impactMed.impactOccurred()
                    onRequiredLevelChanged(level + 1)
                }
                
        }
        .padding(.top, 12)
    }
}

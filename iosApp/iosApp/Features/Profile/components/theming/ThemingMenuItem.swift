//
//  ThemingMenuItem.swift
//  iosApp
//
//  Created by velkonost on 24.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct ThemingMenuItem: View {
    let selected: UIMode
    let onClick: (UIMode) -> Void
    
    init(selected: UIMode, onClick: @escaping (UIMode) -> Void) {
        self.selected = selected
        self.onClick = onClick
    }
    
    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Image(uiImage: SharedR.images().ic_theming.toUIImage()!)
                    .resizable()
                    .renderingMode(.template)
                    .padding(.init(top: 8, leading: 8, bottom: 8, trailing: 8))
                    .frame(width: 42, height: 42, alignment: .center)
                    .foregroundColor(.iconInactive)
                
                    .background(
                        RoundedRectangle(cornerRadius: 12)
                            .fill(Color.backgroundIcon)
                    )
                
                Text(SharedR.strings().profile_app_settings_theming.desc().localized())
                    .style(.labelLarge)
                    .foregroundColor(.textPrimary)
                    .padding(.init(top: .zero, leading: 12, bottom: .zero, trailing: .zero))
                
                Spacer()
                
                
            }
            ThemingTabs(
                selected: selected,
                onClick: onClick
            )
        }
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            
        }
    }
}

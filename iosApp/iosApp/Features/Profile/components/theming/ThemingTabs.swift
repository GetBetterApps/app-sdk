//
//  ThemingTabs.swift
//  iosApp
//
//  Created by velkonost on 24.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct ThemingTabs: View {
    
    let selected: UIMode
    let onClick: (UIMode) -> Void
    
    init(selected: UIMode, onClick: @escaping (UIMode) -> Void) {
        self.selected = selected
        self.onClick = onClick
    }

    var body: some View {
        HStack {
            ThemingButton(
                text: UIMode.light.text.localized(),
                selected: selected == UIMode.light,
                onClick: {
                    onClick(UIMode.light)
                }
            )
            Spacer()
            RoundedRectangle(cornerRadius: 20)
                .fill(Color.textPrimary)
                .frame(width: 1, height: 16)
            Spacer()
            ThemingButton(
                text: UIMode.system.text.localized(),
                selected: selected == UIMode.system,
                onClick: {
                    onClick(UIMode.system)
                }
            )
            Spacer()
            RoundedRectangle(cornerRadius: 20)
                .fill(Color.textPrimary)
                .frame(width: 1, height: 16)
            Spacer()
            ThemingButton(
                text: UIMode.dark.text.localized(),
                selected: selected == UIMode.dark,
                onClick: {
                    onClick(UIMode.dark)
                }
            )
        }
        
        .padding(.top, 12)
    }
}

struct ThemingButton: View {
    
    let text: String
    let selected: Bool
    let onClick: () -> Void
    
    init(text: String, selected: Bool, onClick: @escaping () -> Void) {
        self.text = text
        self.selected = selected
        self.onClick = onClick
    }
    
    var body: some View {
        HStack {
            Spacer()
            Text(text)
                .style(.labelSmall)
                .foregroundColor(selected ? .textLight : .iconInactive)
            Spacer()
        }
        .padding(.vertical, 6)
        .background(
            RoundedRectangle(cornerRadius: 8)
                .fill(selected ? Color.buttonGradientStart : Color.backgroundItem)
        )
        .onTapGesture {
            onClick()
        }
            
    }
}

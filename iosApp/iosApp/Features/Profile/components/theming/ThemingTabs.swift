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
    
    let selected: UIThemeMode
    let onClick: (UIThemeMode) -> Void
    
    init(selected: UIThemeMode, onClick: @escaping (UIThemeMode) -> Void) {
        self.selected = selected
        self.onClick = onClick
    }

    var body: some View {
        HStack {
            ThemingButton(
                text: UIThemeMode.lighttheme.text.localized(),
                selected: selected == UIThemeMode.lighttheme,
                onClick: {
                    onClick(UIThemeMode.lighttheme)
                }
            )
            Spacer()
            RoundedRectangle(cornerRadius: 20)
                .fill(Color.textPrimary)
                .frame(width: 1, height: 16)
            Spacer()
            ThemingButton(
                text: UIThemeMode.systemtheme.text.localized(),
                selected: selected == UIThemeMode.systemtheme,
                onClick: {
                    onClick(UIThemeMode.systemtheme)
                }
            )
            Spacer()
            RoundedRectangle(cornerRadius: 20)
                .fill(Color.textPrimary)
                .frame(width: 1, height: 16)
            Spacer()
            ThemingButton(
                text: UIThemeMode.darktheme.text.localized(),
                selected: selected == UIThemeMode.darktheme,
                onClick: {
                    onClick(UIThemeMode.darktheme)
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

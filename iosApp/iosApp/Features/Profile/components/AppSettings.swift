//
//  AppSettings.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AppSettings : View {
    
    let selectedTheme: UIMode
    let onThemeChanged: (UIMode) -> Void
    
    init(selectedTheme: UIMode, onThemeChanged: @escaping (UIMode) -> Void) {
        self.selectedTheme = selectedTheme
        self.onThemeChanged = onThemeChanged
    }
    
    var body: some View {
        SectionTitle(text: SharedR.strings().profile_app_settings_title.desc().localized())
        
        PrimaryBox {
            VStack {
                ThemingMenuItem(
                  selected: selectedTheme,
                  onClick: { value in
                      if value != selectedTheme {
                          onThemeChanged(value)
                      }
                  }
                )
                
//                MenuItem(
//                    title: SharedR.strings().profile_app_settings_notifications.desc().localized(),
//                    icon: SharedR.images().ic_notifications.toUIImage()!
//                ) {
//                    
//                }
            }
        }
    }
}

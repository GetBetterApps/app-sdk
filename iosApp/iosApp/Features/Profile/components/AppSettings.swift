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
    var body: some View {
        SectionTitle(text: SharedR.strings().profile_app_settings_title.desc().localized())
        
        PrimaryBox {
            VStack {
                MenuItem(
                    title: SharedR.strings().profile_app_settings_theming.desc().localized(),
                    icon: SharedR.images().ic_theming.toUIImage()!
                ) {
                    
                }
                
                MenuItem(
                    title: SharedR.strings().profile_app_settings_notifications.desc().localized(),
                    icon: SharedR.images().ic_notifications.toUIImage()!
                ) {
                    
                }
            }
        }
    }
}

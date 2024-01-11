//
//  HelpAndSupport.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct HelpAndSupport: View {
    
    let onContactUsClick: () -> Void
    let onTelegramClick: () -> Void
    
    init(onContactUsClick: @escaping () -> Void, onTelegramClick: @escaping () -> Void) {
        self.onContactUsClick = onContactUsClick
        self.onTelegramClick = onTelegramClick
    }
    
    var body: some View {
        SectionTitle(text: SharedR.strings().profile_help_title.desc().localized())
        
        PrimaryBox {
            VStack {
                MenuItem(
                    title: SharedR.strings().profile_help_contact_support.desc().localized(),
                    icon: SharedR.images().ic_chat_cloud.toUIImage()!,
                    onClick: onContactUsClick
                )
                
                MenuItem(
                    title: SharedR.strings().tg_group_title.desc().localized(),
                    icon: SharedR.images().ic_telegram.toUIImage()!,
                    onClick: onTelegramClick
                )
            }
        }
    }
}

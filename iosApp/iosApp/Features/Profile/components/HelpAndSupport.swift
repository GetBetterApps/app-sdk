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
    
    init(onContactUsClick: @escaping () -> Void) {
        self.onContactUsClick = onContactUsClick
    }
    
    var body: some View {
        SectionTitle(text: SharedR.strings().profile_help_title.desc().localized())
        
        PrimaryBox {
            VStack {
                MenuItem(
                    title: SharedR.strings().profile_help_rate.desc().localized(),
                    icon: SharedR.images().ic_empty_star.toUIImage()!
                ) {
                        
                }
                
                MenuItem(
                    title: SharedR.strings().profile_help_contact_support.desc().localized(),
                    icon: SharedR.images().ic_chat_cloud.toUIImage()!,
                    onClick: onContactUsClick
                )
            }
        }
    }
}

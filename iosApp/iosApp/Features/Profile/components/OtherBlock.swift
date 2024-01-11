//
//  OtherBlock.swift
//  iosApp
//
//  Created by velkonost on 11.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct OtherBlock: View {
    
    let onPrivacyClick: () -> Void
    let onTermsClick: () -> Void
    
    init(onPrivacyClick: @escaping () -> Void, onTermsClick: @escaping () -> Void) {
        self.onPrivacyClick = onPrivacyClick
        self.onTermsClick = onTermsClick
    }
    
    var body: some View {
        SectionTitle(text: SharedR.strings().profile_other_title.desc().localized())
        
        PrimaryBox {
            VStack {
                MenuItem(
                    title: SharedR.strings().profile_privacy_title.desc().localized(),
                    icon: SharedR.images().ic_privacy.toUIImage()!,
                    onClick: onPrivacyClick                )
                
                MenuItem(
                    title: SharedR.strings().profile_terms_title.desc().localized(),
                    icon: SharedR.images().ic_terms.toUIImage()!,
                    onClick: onTermsClick
                )
            }
        }
    }
    
}

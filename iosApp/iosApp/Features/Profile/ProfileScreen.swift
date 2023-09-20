//
//  ProfileScreen.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct ProfileScreen: View {
    
    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack {
                ProfileHeader(userName: "velkonost") {
                    
                } onSettingsClick: {
                    
                }
                
                SubscriptionBox(subscriptionPlan: SharedR.strings().profile_sub_basic.desc().localized()) {
                    
                }

                AppSettings()
                HelpAndSupport()
            }
            .padding(.init(top: 16, leading: 16, bottom: 200, trailing: 16))
            
        }
        
    }
    
}

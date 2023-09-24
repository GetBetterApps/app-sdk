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
import KMMViewModelSwiftUI

struct ProfileScreen: View {
    
    @StateViewModel var viewModel: ProfileViewModel
    
    let appVersion = Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! ProfileViewState
        
        ScrollView(showsIndicators: false) {
            VStack {
                ProfileHeader(userName: "velkonost") {
                    
                } onSettingsClick: {
                    
                }
                
                SubscriptionBox(subscriptionPlan: SharedR.strings().profile_sub_basic.desc().localized()) {
                    
                }
                
                AppSettings()
                HelpAndSupport()
                
                AppButton(
                    labelText: SharedR.strings().profile_logout.desc().localized(),
                    isLoading: state.isLogoutLoading) {
                        viewModel.dispatch(action: LogoutClick())
                    }
                    .padding(.top, 48)
                
                HStack {
                    Spacer()
                    Text(appVersion?.uppercased() ?? "")
                        .style(.labelMedium)
                        .foregroundColor(.textUnimportantColor)
                    Spacer()
                }.padding(.top, 40)
                
            }
            .padding(.init(top: 16, leading: 16, bottom: 200, trailing: 16))
        }
    }
    
}

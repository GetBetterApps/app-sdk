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
    
    @State var showImagePicker: Bool = false
    @State private var uploadAvatarState: UploadState = UploadState.Idle
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! ProfileViewState
        
        ScrollView(showsIndicators: false) {
            let isAvatarLoading = switch(uploadAvatarState) {
            case .Loading:
                true
            default:
                false
            }
            
            VStack {
                ProfileHeader(
                    userName: state.userName,
                    avatarUrl: state.avatarUrl,
                    isLoading: isAvatarLoading
                ) {
                    self.showImagePicker.toggle()
                } onSettingsClick: {
                    
                }
                
                SubscriptionBox(subscriptionPlan: SharedR.strings().profile_sub_basic.desc().localized()) {
                    
                }
                
                AppSettings()
                HelpAndSupport()
                
                AppButton(
                    labelText: SharedR.strings().profile_logout.desc().localized(),
                    isLoading: state.isLogoutLoading
                ) {
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
        }.sheet(isPresented: $showImagePicker) {
            ImagePicker(sourceType: .photoLibrary) { image in
                if image.pngData() != nil {
                    StorageDelegate(uploadState: $uploadAvatarState).uploadAvatar(file: image.pngData()!) { url in
                        viewModel.dispatch(action: AvatarUploaded(fileUrl: url))
                    }
                }
            }.edgesIgnoringSafeArea(.all)
        }
    }
    
}

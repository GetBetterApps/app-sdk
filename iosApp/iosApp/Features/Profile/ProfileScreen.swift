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
import PhotosUI
import KMMViewModelSwiftUI

struct ProfileScreen: View {
    
    @StateViewModel var viewModel: ProfileViewModel
    
    let appVersion = Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String
    
    @State var showImagePicker: Bool = false
    @State private var uploadAvatarState: UploadState = UploadState.Idle
    
    @State private var selectedItem: PhotosPickerItem? = nil
    @State private var selectedImageData: Data? = nil
    
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
                    avatarBytes: state.avatarBytes,
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
//            PhotosPicker(
//                selection: $selectedItem,
//                matching: .images,
//                photoLibrary: .shared()
//            ) {
//                
//            }
//            .onChange(of: selectedItem) { newItem in
//                Task {
//                    if let data = try? await newItem?.loadTransferable(type: Data.self) {
//                        selectedImageData = data
//                    }
//                }
//            }
//            .onChange(of: selectedImageData) { newImageData in
//                if newImageData != nil {
//                    viewModel.dispatch(action: AvatarSelected(avatarContent: KotlinByteArray.from(data: newImageData!)))
//                }
//            }
            
            ImagePicker(sourceType: .photoLibrary) { image in
                showImagePicker = false
                
//                async {
//                    await uploadAvatar(selectedImage: image)
//                }
//                async {
                    if image.pngData() != nil {
                        viewModel.dispatch(action: AvatarSelected(avatarContent: KotlinByteArray.from(data: image.pngData()!)))
                    }
//                }
                
            }.edgesIgnoringSafeArea(.all)
        }
    }
    
}

extension ProfileScreen {
    func uploadAvatar(selectedImage: UIImage?) async {
        let task = Task.detached {
            if selectedImage != nil {
                let data = await KotlinByteArray.from(data: selectedImage!.pngData()!)
                await viewModel.dispatch(action: AvatarSelected(avatarContent: data))
            }
        }
        
    }
}

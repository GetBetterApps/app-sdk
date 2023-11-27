//
//  SettingsScreen.swift
//  iosApp
//
//  Created by velkonost on 27.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct SettingsScreen: View {
    @StateViewModel var viewModel: SettingsViewModel
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! SettingsViewState
        
        ZStack {
            if state.isLoading {
                Loader().frame(alignment: .center)
            } else {
                VStack {
                    SettingsHeader()
                }
            }
        }
    }
}

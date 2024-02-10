//
//  SubscriptionScreen.swift
//  iosApp
//
//  Created by velkonost on 10.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct SubscriptionScreen: View {
    
    @StateViewModel var viewModel: SubscriptionViewModel
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! SubscriptionViewState
    }
}

//
//  OnboardingScreen.swift
//  iosApp
//
//  Created by velkonost on 17.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct OnboardingScreen: View {
    
    @StateViewModel var viewModel: OnboardingViewModel
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! OnboardingViewState
        
        VStack {
            Spacer().frame(height: 140)
            
            ZStack {
                OnboardingFirstStep()
            }
        }
    }
}

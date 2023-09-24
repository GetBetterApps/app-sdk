//
//  SplashScreen.swift
//  iosApp
//
//  Created by velkonost on 24.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI
import KMMViewModelSwiftUI

struct SplashScreen: View {
    @StateViewModel var viewModel: SplashViewModel
    
    var body: some View {
        ZStack(alignment: .topLeading) {
            
            LinearGradient(
                colors: [.onboardingBackgroundGradientStart, .onboardingBackgroundGradientEnd],
                startPoint: .top, endPoint: .bottom
            )
            
            VStack(alignment: .leading, spacing: .zero) {
                Spacer()
                Image(uiImage: SharedR.images().ic_getbetter_light_.toUIImage()!)
                    .resizable()
                    .scaledToFit()
                    .opacity(0.4)
                Spacer()
            }
        }.edgesIgnoringSafeArea(.all)
    }
}

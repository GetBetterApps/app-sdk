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
import KMPNativeCoroutinesAsync
import CoreTelephony

struct SplashScreen: View {
    @StateViewModel var viewModel: SplashViewModel
    @State private var eventsObserver: Task<(), Error>? = nil
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! SplashViewState
        
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
        }
        .onAppear {
            observeEvents()
            #if targetEnvironment(simulator)
                viewModel.dispatch(action: SplashActionAllowSubscription(value: false))
            #else
                viewModel.dispatch(action: SplashActionAllowSubscription(value: true))
            #endif
        }
        .onDisappear {
            eventsObserver?.cancel()
            eventsObserver = nil
        }
        .edgesIgnoringSafeArea(.all)
    }
        
}

extension SplashScreen {
    func observeEvents() {
        if eventsObserver == nil {
            eventsObserver = Task {
                for try await event in asyncSequence(for: viewModel.events) {
                    switch(event) {
                    case _ as SplashEventChangeTheme: do {
                        (UIApplication.shared.connectedScenes.first as? UIWindowScene)?.windows.first!.overrideUserInterfaceStyle = switch((event as! SplashEventChangeTheme).value) {
                        case UIThemeMode.lighttheme: .light
                        case UIThemeMode.darktheme: .dark
                        default : .unspecified
                        }
                    }
                   
                    default:
                        break
                    }
                }
            }
        }
    }
}

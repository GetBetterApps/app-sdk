//
//  OnboardingRoute.swift
//  iosApp
//
//  Created by velkonost on 17.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct OnboardingRoute: NavRoute {
    typealias T = OnboardingViewModel
    
    var route: String {
        return NavigationScreen.OnboardingNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return nil
    }
    
    var content: some View {
        return OnboardingScreen(viewModel: viewModel)
    }
    
    var viewModel: OnboardingViewModel {
        @LazyKoin var delegate: OnboardingViewModel
        delegate.doInit()
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return []
    }
    
}



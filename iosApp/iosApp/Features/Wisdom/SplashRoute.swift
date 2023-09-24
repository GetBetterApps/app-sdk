//
//  SplashRoute.swift
//  iosApp
//
//  Created by velkonost on 24.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct SplashRoute: NavRoute {
    
    typealias T = SplashViewModel
    
    var route: String {
        return NavigationScreen.SplashNavScreen.shared.route
    }
    
    var menuIcon: UIImage? = nil
    
    var content: some View {
        return SplashScreen(viewModel: viewModel)
    }
    
    var viewModel: SplashViewModel {
        @LazyKoin var delegate: SplashViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
    
}

//
//  AbilityDetailsRoute.swift
//  iosApp
//
//  Created by velkonost on 12.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AbilityDetailsRoute: NavRoute {
    typealias T = AbilityDetailsViewModel
    
    var route: String {
        return NavigationScreen.AbilityDetailsNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return nil
    }
    
    var content: some View {
        return AbilityDetailsScreen(viewModel: viewModel)
    }
    
    var viewModel: AbilityDetailsViewModel {
        @LazyKoin var delegate: AbilityDetailsViewModel
        delegate.doInit()
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [NavigationScreenKt.ARG_ABILITY]
    }
    
}


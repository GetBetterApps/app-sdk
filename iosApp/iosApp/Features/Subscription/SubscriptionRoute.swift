//
//  SubscriptionRoute.swift
//  iosApp
//
//  Created by velkonost on 10.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct SubscriptionRoute: NavRoute {
    typealias T = SubscriptionViewModel
    
    var route: String {
        return NavigationScreen.SubscriptionNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return nil
    }
    
    var content: some View {
        return SubscriptionScreen(viewModel: viewModel)
    }
    
    var viewModel: SubscriptionViewModel {
        @LazyKoin var delegate: SubscriptionViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return []
    }
    
}



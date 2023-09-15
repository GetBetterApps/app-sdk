//
//  HomeRoute.swift
//  iosApp
//
//  Created by velkonost on 06.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct HomeRoute: NavRoute {
    
    typealias T = HomeViewModel
    
    var route: String {
        return NavigationScreen.HomeNavScreen.shared.route
    }
    
    var content: some View {
        return HomeScreen(viewModel: viewModel)
    }
    
    var viewModel: HomeViewModel {
        @LazyKoin var delegate: HomeViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
}

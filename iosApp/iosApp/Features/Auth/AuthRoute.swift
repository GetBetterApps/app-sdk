//
//  AuthRoute.swift
//  iosApp
//
//  Created by velkonost on 10.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct AuthRoute: NavRoute {
    
    typealias T = AuthViewModel
    
    var route: String {
        return NavigationScreen.AuthNavScreen.shared.route
    }
    
    var content: some View {
        return AuthScreen(viewModel: viewModel)
    }
    
    var viewModel: AuthViewModel {
        @LazyKoin var delegate: AuthViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
}

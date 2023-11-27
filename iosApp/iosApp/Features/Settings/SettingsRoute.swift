//
//  SettingsRoute.swift
//  iosApp
//
//  Created by velkonost on 27.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct SettingsRoute: NavRoute {
    
    typealias T = SettingsViewModel
    
    var route: String {
        return NavigationScreen.SettingsNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return nil
    }
    
    var content: some View {
        return SettingsScreen(viewModel: viewModel)
    }
    
    var viewModel: SettingsViewModel {
        @LazyKoin var delegate: SettingsViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
    
}

//
//  AbilitiesRoute.swift
//  iosApp
//
//  Created by velkonost on 09.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct AbilitiesRoute: NavRoute {
    
    typealias T = AbilitiesViewModel
    
    var route: String {
        return NavigationScreen.AbilitiesNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return SharedR.images.shared.ic_menu_wisdom.toUIImage()
    }
    
    var content: some View {
        return AbilitiesScreen(viewModel: viewModel)
    }
    
    var viewModel: AbilitiesViewModel {
        @LazyKoin var delegate: AbilitiesViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
    
}

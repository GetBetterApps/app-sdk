//
//  ProfileRoute.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct ProfileRoute: NavRoute {
    
    typealias T = ProfileViewModel
    
    var route: String {
        return NavigationScreen.ProfileNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return SharedR.images.shared.ic_menu_profile.toUIImage()
    }
    
    var content: some View {
        return ProfileScreen(viewModel: viewModel)
    }
    
    var viewModel: ProfileViewModel {
        @LazyKoin var delegate: ProfileViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
    
}

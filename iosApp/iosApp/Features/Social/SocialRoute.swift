//
//  SocialRoute.swift
//  iosApp
//
//  Created by velkonost on 21.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct SocialRoute: NavRoute {
    
    typealias T = SocialViewModel
    
    var route: String {
        return NavigationScreen.SocialNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return SharedR.images.shared.ic_menu_profile.toUIImage()
    }
    
    var content: some View {
        return SocialScreen(viewModel: viewModel)
    }
    
    var viewModel: SocialViewModel {
        @LazyKoin var delegate: SocialViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
    
}

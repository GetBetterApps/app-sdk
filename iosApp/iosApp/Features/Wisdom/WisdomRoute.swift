//
//  WisdomRoute.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct WisdomRoute: NavRoute {
    
    typealias T = WisdomViewModel
    
    var route: String {
        return NavigationScreen.WisdomNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return SharedR.images.shared.ic_menu_profile.toUIImage()
    }
    
    var content: some View {
        return WisdomScreen() 
    }
    
    var viewModel: WisdomViewModel {
        @LazyKoin var delegate: WisdomViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
    
}

//
//  DiaryRoute.swift
//  iosApp
//
//  Created by velkonost on 21.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct DiaryRoute: NavRoute {
    
    typealias T = DiaryViewModel
    
    var route: String {
        return NavigationScreen.DiaryNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return SharedR.images.shared.ic_menu_profile.toUIImage()
    }
    
    var content: some View {
        return DiaryScreen(viewModel: viewModel) 
    }
    
    var viewModel: DiaryViewModel {
        @LazyKoin var delegate: DiaryViewModel
        delegate.doInit()
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
    
}

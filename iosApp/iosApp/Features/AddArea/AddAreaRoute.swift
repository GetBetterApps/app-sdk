//
//  AddAreaRoute.swift
//  iosApp
//
//  Created by velkonost on 06.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AddAreaRoute: NavRoute {
    
    typealias T = AddAreaViewModel
    
    var route: String {
        return NavigationScreen.AddAreaNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return nil
    }
    
    var content: some View {
        return AddAreaScreen()
    }
    
    var viewModel: AddAreaViewModel {
        @LazyKoin var delegate: AddAreaViewModel
        delegate.doInit()
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
    
}

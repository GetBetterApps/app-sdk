//
//  DetailRoute.swift
//  iosApp
//
//  Created by velkonost on 06.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct DetailRoute: NavRoute {
    
    typealias T = DetailViewModel
    
    var route: String {
        return NavigationScreen.DetailNavScreen.shared.route
    }
    
    var content: some View {
        return DetailScreen(viewModel: viewModel)
    }
    
    var viewModel: DetailViewModel {
        @LazyKoin var delegate: DetailViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
}

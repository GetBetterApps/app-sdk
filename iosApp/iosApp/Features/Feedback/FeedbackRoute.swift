//
//  FeedbackRoute.swift
//  iosApp
//
//  Created by velkonost on 25.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct FeedbackRoute: NavRoute {
    
    typealias T = FeedbackViewModel
    
    var route: String {
        return NavigationScreen.FeedbackNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return SharedR.images.shared.ic_menu_social.toUIImage()
    }
    
    var content: some View {
        return FeedbackScreen(viewModel: viewModel)
    }
    
    var viewModel: FeedbackViewModel {
        @LazyKoin var delegate: FeedbackViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
    
}

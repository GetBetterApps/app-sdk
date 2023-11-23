//
//  CalendarsRoute.swift
//  iosApp
//
//  Created by velkonost on 21.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

struct CalendarsRoute: NavRoute {
    
    typealias T = CalendarsViewModel
    
    var route: String {
        return NavigationScreen.CalendarsNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return SharedR.images.shared.ic_menu_calendars.toUIImage()
    }
    
    var content: some View {
        return CalendarsScreen(viewModel: viewModel)
    }
    
    var viewModel: CalendarsViewModel {
        @LazyKoin var delegate: CalendarsViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [String]()
    }
    
}

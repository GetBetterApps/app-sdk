//
//  TaskDetailRoute.swift
//  iosApp
//
//  Created by velkonost on 03.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TaskDetailRoute: NavRoute {
    typealias T = TaskDetailViewModel
    
    var route: String {
        return NavigationScreen.TaskDetailNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return nil
    }
    
    var content: some View {
        return TaskDetailScreen(viewModel: viewModel)
    }
    
    var viewModel: TaskDetailViewModel {
        @LazyKoin var delegate: TaskDetailViewModel
        delegate.doInit()
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [NavigationScreenKt.ARG_TASK]
    }
    
}

//
//  NoteDetailRoute.swift
//  iosApp
//
//  Created by velkonost on 03.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct NoteDetailRoute: NavRoute {
    typealias T = NoteDetailViewModel
    
    var route: String {
        return NavigationScreen.NoteDetailNavScreen.shared.route
    }
    
    var menuIcon: UIImage? {
        return nil
    }
    
    var content: some View {
        return NoteDetailScreen(viewModel: viewModel)
    }
    
    var viewModel: NoteDetailViewModel {
        @LazyKoin var delegate: NoteDetailViewModel
        return delegate
    }
    
    func getArguments() -> Array<String> {
        return [NavigationScreenKt.ARG_NOTE]
    }
    
}

//
//  TasksView.swift
//  iosApp
//
//  Created by velkonost on 27.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct TasksView: View {
    
    let isLoading: Bool
    
    init(isLoading: Bool) {
        self.isLoading = isLoading
    }
    
    var body: some View {
        ZStack {
            if isLoading {
                Loader()
            } else {
                Text("tasks")
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
        
    }
}

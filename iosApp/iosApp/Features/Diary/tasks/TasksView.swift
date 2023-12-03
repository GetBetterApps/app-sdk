//
//  TasksView.swift
//  iosApp
//
//  Created by velkonost on 27.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TasksView: View {
    
    private let isLoading: Bool
    private let favoriteItems: [TaskUI]
    private let currentItems: [TaskUI]
    private let completedItems: [TaskUI]
    private let onTaskFavoriteClick: (TaskUI) -> Void
    
    init(isLoading: Bool, favoriteItems: [TaskUI], currentItems: [TaskUI], completedItems: [TaskUI], onTaskFavoriteClick: @escaping (TaskUI) -> Void) {
        self.isLoading = isLoading
        self.favoriteItems = favoriteItems
        self.currentItems = currentItems
        self.completedItems = completedItems
        self.onTaskFavoriteClick = onTaskFavoriteClick
    }
    
    var body: some View {
        ZStack {
            if isLoading && currentItems.isEmpty {
                Loader()
            } else {
                ScrollView(showsIndicators: false) {
                    LazyVStack(spacing: 0) {
                        TasksSection(
                            section: TasksUISection.favorite,
                            items: favoriteItems,
                            onFavoriteClick: onTaskFavoriteClick
                        )
                        
                        TasksSection(
                            section: TasksUISection.current,
                            items: currentItems,
                            onFavoriteClick: onTaskFavoriteClick
                        )
                        
                        TasksSection(
                            section: TasksUISection.completed,
                            items: completedItems,
                            onFavoriteClick: onTaskFavoriteClick
                        )
                    }
                    .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                }.fadingEdge()
                
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
        
    }
}

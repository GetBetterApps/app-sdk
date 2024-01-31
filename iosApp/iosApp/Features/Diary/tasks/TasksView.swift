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
    
    private let onTaskClick: (TaskUI) -> Void
    private let onTaskListUpdateClick: () -> Void
    private let onTaskFavoriteClick: (TaskUI) -> Void
    
    init(
        isLoading: Bool, favoriteItems: [TaskUI], currentItems: [TaskUI], completedItems: [TaskUI],
        onTaskClick: @escaping (TaskUI) -> Void,
        onTaskFavoriteClick: @escaping (TaskUI) -> Void,
        onTaskListUpdateClick: @escaping () -> Void
    ) {
        self.isLoading = isLoading
        self.favoriteItems = favoriteItems
        self.currentItems = currentItems
        self.completedItems = completedItems
        self.onTaskClick = onTaskClick
        self.onTaskFavoriteClick = onTaskFavoriteClick
        self.onTaskListUpdateClick = onTaskListUpdateClick
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
                            onItemClick: onTaskClick,
                            onFavoriteClick: onTaskFavoriteClick
                        )
                        
                        if !favoriteItems.isEmpty {
                            AdView()
                                .padding(.vertical, 2)
                        }
                        
                        TasksSection(
                            section: TasksUISection.current,
                            items: currentItems,
                            onItemClick: onTaskClick,
                            onFavoriteClick: onTaskFavoriteClick,
                            onUpdateClick: onTaskListUpdateClick
                        )
                        
                        AdView()
                            .padding(.vertical, 2)
                        
                        TasksSection(
                            section: TasksUISection.completed,
                            items: completedItems,
                            onItemClick: onTaskClick,
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

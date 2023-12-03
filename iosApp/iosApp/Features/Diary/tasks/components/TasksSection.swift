//
//  TasksSection.swift
//  iosApp
//
//  Created by velkonost on 03.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TasksSection : View {
    
    private let section: TasksUISection
    private let items: [TaskUI]
    private let onItemClick: (TaskUI) -> Void
    private let onFavoriteClick: (TaskUI) -> Void
    
    init(section: TasksUISection, items: [TaskUI], onItemClick: @escaping (TaskUI) -> Void, onFavoriteClick: @escaping (TaskUI) -> Void) {
        self.section = section
        self.items = items
        self.onItemClick = onItemClick
        self.onFavoriteClick = onFavoriteClick
    }
    
    var body: some View {
        if !items.isEmpty {
            
            let title = switch(section) {
            case TasksUISection.favorite: SharedR.strings().tasks_favorite_title.desc().localized()
            case TasksUISection.current: SharedR.strings().tasks_current_list_title.desc().localized()
            default: SharedR.strings().tasks_completed_title.desc().localized()
            }
            
            HStack {
                Text(title)
                    .style(.headlineSmall)
                    .foregroundColor(.textPrimary)
                    .padding(.top, 24)
                Spacer()
            }
            
            ForEach(items, id: \.self) { item in
                TaskItem(
                    item: item,
                    onClick: {
                        onItemClick(item)
                    },
                    onFavoriteClick: {
                        onFavoriteClick(item)
                    }
                )
            }
        }
    }
}

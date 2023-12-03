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
    
    init(section: TasksUISection, items: [TaskUI]) {
        self.section = section
        self.items = items
    }
    
    var body: some View {
        if !items.isEmpty {
            
            let title = switch(section) {
            case TasksUISection.favorite: SharedR.strings().tasks_favorite_title.desc().localized()
            case TasksUISection.current: SharedR.strings().tasks_current_list_title.desc().localized()
            default: SharedR.strings().tasks_completed_title.desc().localized()
            }
            
            Text(title)
                .style(.headlineSmall)
                .foregroundColor(.textPrimary)
                .padding(.top, 24)
                .padding(.horizontal, 20)
            
            ForEach(items, id: \.self.id) { item in
                TaskItem(item: item)
            }
        }
    }
}

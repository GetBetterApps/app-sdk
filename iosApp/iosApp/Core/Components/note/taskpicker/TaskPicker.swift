//
//  TaskPicker.swift
//  iosApp
//
//  Created by velkonost on 04.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import SwiftUIPager


struct TaskWrapper : Identifiable, Equatable, Hashable {
    var id: String = UUID().uuidString
    var task: TaskUI?
}

struct TaskPicker: View {
    
    let tasks: [TaskUI?]
    private var selectedTask: TaskUI?
    
    let onTaskSelect: (TaskUI?) -> Void
    @Binding private var isTaskPickerVisible: Bool
    
    @StateObject var taskPage: Page = .first()
    
    init(tasks: [TaskUI?], selectedTask: TaskUI?, onTaskSelect: @escaping (TaskUI?) -> Void, isTaskPickerVisible: Binding<Bool>) {
        self.tasks = tasks
        self.selectedTask = selectedTask
        self.onTaskSelect = onTaskSelect
        self._isTaskPickerVisible = isTaskPickerVisible
    }
    
    var body: some View {
        PrimaryBox(padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero)) {
            VStack {
                TaskPickerHeader(
                    selectedTask: selectedTask,
                    availableTasksAmount: tasks.count,
                    isTaskPickerVisible: isTaskPickerVisible
                ) {
                    withAnimation {
                        isTaskPickerVisible.toggle()
                    }
                }
                
                if isTaskPickerVisible {
                    Pager(
                        page: taskPage,
                        data: tasks.map({ task in TaskWrapper(task: task) }),
                        id: \.self.task?.id
                    ) { taskWrapper in
                        if taskWrapper.task == nil {
                            TaskPickerEmptyItem()
                        } else {
                            TaskPickerItem(task: taskWrapper.task!)
                        }
                    }
                    .interactive(rotation: true)
                    .interactive(scale: 0.8)
                    .alignment(.center)
                    .onPageChanged({ page in
                        onTaskSelect(tasks[page])
                    })
                    .preferredItemSize(CGSize(width: 300, height: 150))
                    .frame(height: 150)
                    .padding(.bottom, 16)
                    .onChange(of: tasks) { value in
                        taskPage.update(.moveToFirst)
                    }
                }
            }
        }
        .onChange(of: tasks) { value in
            if tasks.count <= 1 {
                withAnimation {
                    isTaskPickerVisible = false
                }
            }
        }
    }
}

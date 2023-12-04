//
//  TaskPickerHeader.swift
//  iosApp
//
//  Created by velkonost on 04.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TaskPickerHeader : View {
    
    let selectedTask: TaskUI?
    let availableTasksAmount: Int
    
    let onClick: () -> Void
    let isTaskPickerVisible: Bool
    
    init(selectedTask: TaskUI?, availableTasksAmount: Int, isTaskPickerVisible: Bool, onClick: @escaping () -> Void) {
        self.selectedTask = selectedTask
        self.onClick = onClick
        self.availableTasksAmount = availableTasksAmount
        self.isTaskPickerVisible = isTaskPickerVisible
    }
    
    var body: some View {
        @State var arrowRotationAngle: Double = isTaskPickerVisible ? -90 : 90
        
        HStack {
            if selectedTask == nil && availableTasksAmount <= 1 {
                Text(SharedR.strings().create_note_no_tasks_title.desc().localized())
                .style(.titleMedium)
                .foregroundColor(.textPrimary)
                .lineLimit(1)
            } else {
                Text(selectedTask != nil ? selectedTask!.name : SharedR.strings().create_note_without_task.desc().localized())
                    .style(.titleMedium)
                    .lineLimit(1)
                    .foregroundColor(.textPrimary)
                    .padding(.leading, 6)
                    .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                
                Spacer()
                
                Image(uiImage: SharedR.images().ic_arrow_right.toUIImage()!)
                    .resizable()
                    .renderingMode(.template)
                    .scaledToFit()
                    .foregroundColor(.iconInactive)
                    .frame(width: 24, height: 24)
                    .rotationEffect(Angle(degrees: arrowRotationAngle))
            }
           
        }
        .animation(.easeInOut, value: selectedTask)
        .padding(16)
        .frame(minWidth: 0, maxWidth: .infinity)
        .contentShape(Rectangle())
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            
            if availableTasksAmount > 1 {
                onClick()
            }
        }
    }
}

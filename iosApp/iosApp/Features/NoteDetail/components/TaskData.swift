//
//  TaskData.swift
//  iosApp
//
//  Created by velkonost on 05.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TaskData : View {
    
    let task: TaskUI
    let onClick: () -> Void
    
    init(task: TaskUI, onClick: @escaping () -> Void) {
        self.task = task
        self.onClick = onClick
    }
    
    var body: some View {
        PrimaryBox(
            padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero)
        ) {
            HStack {
                Text(task.name)
                    .style(.titleMedium)
                    .lineLimit(1)
                    .foregroundColor(.textPrimary)
                    .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                
            }
            .padding(16)
            .frame(minWidth: 0, maxWidth: .infinity)
            .contentShape(Rectangle())
            .onTapGesture {
                let impactMed = UIImpactFeedbackGenerator(style: .medium)
                impactMed.impactOccurred()
                onClick()
            }
        }
    }
}

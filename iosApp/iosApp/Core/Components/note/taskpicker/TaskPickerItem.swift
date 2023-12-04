//
//  TaskPickerItem.swift
//  iosApp
//
//  Created by velkonost on 04.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TaskPickerItem: View {
    
    let task: TaskUI
    
    init(task: TaskUI) {
        self.task = task
    }
    
    var body: some View {
        ZStack {
            VStack {
                Text(task.name)
                    .style(.titleMedium)
                    .foregroundColor(.textLight)
                    .lineLimit(1)
                Text(task.why)
                    .style(.bodyMedium)
                    .foregroundColor(.textPrimary)
                    .padding(.top, 6)
            }
        }
        .frame(height: 120)
        .frame(minWidth: 0, maxWidth: .infinity)
        .padding(16)
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color.textFieldBackground)
        )
    }
}

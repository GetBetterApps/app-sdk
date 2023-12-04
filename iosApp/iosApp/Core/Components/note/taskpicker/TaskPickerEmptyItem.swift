//
//  TaskPickerEmptyItem.swift
//  iosApp
//
//  Created by velkonost on 04.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TaskPickerEmptyItem : View {
    var body: some View {
        ZStack {
            VStack {
                Text(SharedR.strings().create_note_without_task.desc().localized())
                    .style(.titleLarge)
                    .foregroundColor(.textPrimary)
                    .padding(.top, 12)
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity)
        .padding(16)
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color.textFieldBackground)
        )
    }
}

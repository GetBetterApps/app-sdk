//
//  TaskItem.swift
//  iosApp
//
//  Created by velkonost on 03.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TaskItem: View {
    
    private let item: TaskUI
    
    init(item: TaskUI) {
        self.item = item
    }
    
    var body: some View {
        PrimaryBox {
            VStack(spacing: 0) {
            }
        }
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
        }
    }
}

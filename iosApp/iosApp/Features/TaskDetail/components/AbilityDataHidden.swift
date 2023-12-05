//
//  AbilityDataHidden.swift
//  iosApp
//
//  Created by velkonost on 05.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AbilityDataHidden: View {
    var body: some View {
        PrimaryBox {
            VStack(spacing: 0) {
                HStack {
                    Text(SharedR.strings().task_ability_hidden.desc().localized())
                        .style(.labelMedium)
                        .foregroundColor(.textPrimary)
                }
            }
        }
    }
}

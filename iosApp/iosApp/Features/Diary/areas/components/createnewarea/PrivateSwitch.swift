//
//  PrivateSwitch.swift
//  iosApp
//
//  Created by velkonost on 04.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct PrivateSwitch: View {
    
    @State private var isPrivate: Bool
    let onCheckedChange: (Bool) -> Void
    
    init(isPrivate: Bool, onCheckedChange: @escaping (Bool) -> Void) {
        self.isPrivate = isPrivate
        self.onCheckedChange = onCheckedChange
    }
    
    var body: some View {
        Toggle(isOn: $isPrivate) {
            Text(isPrivate ? SharedR.strings().private_state.desc().localized() : SharedR.strings().public_state.desc().localized())
                .style(.titleMedium)
                .foregroundColor(.textSecondaryTitle)
        }
        .tint(.iconActive)
        .padding(.top, 12)
    }
}

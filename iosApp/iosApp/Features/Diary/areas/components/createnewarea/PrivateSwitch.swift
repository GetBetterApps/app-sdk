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
    
    @Binding var isPrivate: Bool
    let onCheckedChange: () -> Void
    
    let isEnabled: Bool
    
    init(onCheckedChange: @escaping () -> Void, isEnabled: Bool = true, isPrivate: Binding<Bool>) {
        self.onCheckedChange = onCheckedChange
        self.isEnabled = isEnabled
        self._isPrivate = isPrivate
    }
    
    var body: some View {
        PrimaryBox(
            padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero)
        ) {
            Toggle(
                isOn: Binding(
                    get: { isPrivate },
                    set: { newValue in
                        onCheckedChange()
                    }
                )
            ) {
                Text(isPrivate ? SharedR.strings().private_state.desc().localized() : SharedR.strings().public_state.desc().localized())
                    .style(.titleMedium)
                    .foregroundColor(.textPrimary)
            }
            .disabled(!isEnabled)
            .tint(.iconActive)
            .padding(.trailing, 16)
            .padding(.leading, 16)
            .frame(height: 60)
//            .padding(.top, 12)
        }
        
    }
}

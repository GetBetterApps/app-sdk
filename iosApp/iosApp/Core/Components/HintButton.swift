//
//  HintButton.swift
//  iosApp
//
//  Created by velkonost on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct HintButton: View {
    
    private let onClick: () -> Void
    init(onClick: @escaping () -> Void) {
        self.onClick = onClick
    }
    
    var body: some View {
        Image(uiImage: SharedR.images().ic_info.toUIImage()!)
            .resizable()
            .renderingMode(.template)
            .scaledToFit()
            .foregroundColor(.iconInactive)
            .frame(width: 18, height: 18)
            .onTapGesture {
                onClick()
            }
    }
}

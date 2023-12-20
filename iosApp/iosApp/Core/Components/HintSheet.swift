//
//  HintSheet.swift
//  iosApp
//
//  Created by velkonost on 20.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct HintSheet: View {
    
    private let state: MessageType.Sheet?
    
    init(state: MessageType.Sheet?) {
        self.state = state
    }
    
    var body: some View {
        VStack(spacing: 0) {
            if state?.title != nil {
                Text((state?.title!.localized())!)
                    .style(.titleMedium)
                    .foregroundColor(.textTitle)
            }
            
            if state?.text != nil {
                Text((state?.text.localized())!)
                    .style(.bodyMedium)
                    .foregroundColor(.textTitle)
            }
            
        }
    }
}

extension View {
    func hintSheet(
        isShowing: Binding<Bool>,
        sheet: MessageType.Sheet?
    ) -> some View {
        self.sheet(isPresented: isShowing) {
            HintSheet(state: sheet)
        }
    }
}

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
    
    @Binding var sheetHeight: CGFloat
    private let state: MessageType.Sheet?
    
    init(sheetHeight: Binding<CGFloat>, state: MessageType.Sheet?) {
        self._sheetHeight = sheetHeight
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
                Text((state?.text!.localized())!)
                    .style(.bodyMedium)
                    .foregroundColor(.textTitle)
                    .fixedSize(horizontal: false, vertical: true)
            }
            
        }
        .overlay {
            GeometryReader { geometry in
                Color.clear.preference(key: InnerHeightPreferenceKey.self, value: geometry.size.height)
            }
        }
        .onPreferenceChange(InnerHeightPreferenceKey.self) { newHeight in
            sheetHeight = newHeight
        }
        .presentationDetents([.height(sheetHeight)])
    }
}

extension View {
    func hintSheet(
        isShowing: Binding<Bool>,
        sheet: MessageType.Sheet?,
        sheetHeight: Binding<CGFloat>
    ) -> some View {
        return self.sheet(isPresented: isShowing) {
            HintSheet(sheetHeight: sheetHeight, state: sheet)
               
        }
    }
}

struct InnerHeightPreferenceKey: PreferenceKey {
    static var defaultValue: CGFloat = .zero
    static func reduce(value: inout CGFloat, nextValue: () -> CGFloat) {
        value = nextValue()
    }
}

//
//  HintSubscriptionSheet.swift
//  iosApp
//
//  Created by velkonost on 02.03.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct HintSubscriptionSheet: View {
    
    @Binding var sheetHeight: CGFloat
    private let text: String
    private let onClick: () -> Void
    
    init(
        sheetHeight: Binding<CGFloat>,
        text: String,
        onClick: @escaping () -> Void
    ) {
        self._sheetHeight = sheetHeight
        self.text = text
        self.onClick = onClick
    }
    
    var body: some View {
        ZStack {
            Color.mainBackground.edgesIgnoringSafeArea(.all)
            
            VStack(spacing: 0) {
                HStack {
                    Spacer()
                    Text(text)
                        .style(.bodyLarge)
                        .foregroundColor(.textTitle)
                        .fixedSize(horizontal: false, vertical: true)
                        .multilineTextAlignment(.center)
                    Spacer()
                }
            
                HStack {
                    Spacer()
                    AppButton(
                        labelText: SharedR.strings().profile_sub_upgrade.desc().localized(),
                        isLoading: false,
                        onClick: onClick
                    )
                    Spacer()
                }
                .padding(.top, 24)
                
            }
            .padding(.horizontal, 16)
            .padding(.bottom, 16)
            .padding(.top, 16)
            .overlay {
                GeometryReader { geometry in
                    Color.clear.preference(key: InnerHeightPreferenceKey.self, value: geometry.size.height)
                }
            }
            .onPreferenceChange(InnerHeightPreferenceKey.self) { newHeight in
                if newHeight < UIScreen.screenHeight - 10 {
                    sheetHeight = newHeight
                }
            }
            .presentationDetents([.height(sheetHeight)])
        }
    }
}

extension View {
    func hintSubscriptionSheet(
        isShowing: Binding<Bool>,
        sheetHeight: Binding<CGFloat>,
        text: String,
        onClick: @escaping () -> Void
    ) -> some View {
        return self.sheet(isPresented: isShowing) {
            HintSubscriptionSheet(
                sheetHeight: sheetHeight,
                text: text,
                onClick: onClick
            )
        }
    }
}

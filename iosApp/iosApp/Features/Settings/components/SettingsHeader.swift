//
//  SettingsHeader.swift
//  iosApp
//
//  Created by velkonost on 27.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct SettingsHeader: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    var body: some View {
        HStack {
            Image(uiImage: SharedR.images().ic_arrow_back.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .foregroundColor(.iconActive)
                .frame(width: 32, height: 32)
                .scaledToFill()
                .padding(4)
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .fill(Color.backgroundIcon)
                )
                .padding(.leading, 20)
                .onTapGesture {
                    let impactMed = UIImpactFeedbackGenerator(style: .medium)
                    impactMed.impactOccurred()
                    presentationMode.wrappedValue.dismiss()
                }
            Text(SharedR.strings().settings_title.desc().localized())
                .style(.headlineSmall)
                .foregroundColor(.textTitle)
                .padding(.leading, 12)
            Spacer()
        }
    }
}

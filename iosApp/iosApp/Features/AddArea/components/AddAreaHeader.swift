//
//  AddAreaHeader.swift
//  iosApp
//
//  Created by velkonost on 06.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AddAreaHeader: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    private let onHintClick: () -> Void
    init( onHintClick: @escaping () -> Void) {
        self.onHintClick = onHintClick
    }
    
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
            Text(SharedR.strings().add_area_title.desc().localized())
                .style(.headlineSmall)
                .foregroundColor(.textTitle)
                .padding(.leading, 12)
            HintButton(onClick: onHintClick)
                .padding(.top, 4)
            Spacer()
           
        }
    }
}

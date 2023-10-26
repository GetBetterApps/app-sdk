//
//  SubNotesHeader.swift
//  iosApp
//
//  Created by velkonost on 26.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct SubNotesHeader: View {
    
    let onClick: () -> Void
    let isSubNotesBlockVisible: Bool
    
    init(isSubNotesBlockVisible: Bool, onClick: @escaping () -> Void) {
        self.onClick = onClick
        self.isSubNotesBlockVisible = isSubNotesBlockVisible
    }
    
    var body: some View {
        @State var arrowRotationAngle: Double = isSubNotesBlockVisible ? -90 : 90
        
        HStack {
            Text(SharedR.strings().create_note_subnote_title.desc().localized())
                .style(.titleMedium)
                .lineLimit(1)
                .foregroundColor(.textPrimary)
                .padding(.leading, 6)
                .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
            
            Spacer()
            
            Image(uiImage: SharedR.images().ic_arrow_right.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .scaledToFit()
                .foregroundColor(.iconInactive)
                .frame(width: 24, height: 24)
                .rotationEffect(Angle(degrees: arrowRotationAngle))
        }
        .padding(16)
        .frame(minWidth: 0, maxWidth: .infinity)
        .contentShape(Rectangle())
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onClick()
        }
    }
}

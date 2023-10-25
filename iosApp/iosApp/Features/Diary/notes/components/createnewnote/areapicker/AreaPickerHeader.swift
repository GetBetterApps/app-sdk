//
//  AreaPickerHeader.swift
//  iosApp
//
//  Created by velkonost on 25.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AreaPickerHeader: View {
    
    let selectedArea: Area?
    let noteType: NoteType
    let onClick: () -> Void
    let isAreaPickerVisible: Bool
    
    init(selectedArea: Area?, noteType: NoteType, isAreaPickerVisible: Bool, onClick: @escaping () -> Void) {
        self.selectedArea = selectedArea
        self.onClick = onClick
        self.noteType = noteType
        self.isAreaPickerVisible = isAreaPickerVisible
    }
    
    var body: some View {
        @State var arrowRotationAngle: Double = isAreaPickerVisible ? -90 : 90
        
        HStack {
            if (selectedArea == nil) {
                Text(
                    noteType == NoteType.default_ ? SharedR.strings().select_area_for_note.desc().localized() : SharedR.strings().select_area_for_goal.desc().localized()
                )
                .style(.titleMedium)
                .foregroundColor(.textPrimary)
                .lineLimit(1)
            } else {
                Image(uiImage: Emoji.companion.getIconById(id: Int32(truncating: selectedArea!.emojiId!)).toUIImage()!)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 32, height: 32)
                
                Text(selectedArea!.name)
                    .style(.titleMedium)
                    .lineLimit(1)
                    .foregroundColor(.textPrimary)
                    .padding(.leading, 6)
                    .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
            }
            
            Spacer()
            
            Image(uiImage: SharedR.images().ic_arrow_right.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .scaledToFit()
                .foregroundColor(.iconInactive)
                .frame(width: 24, height: 24)
                .rotationEffect(Angle(degrees: arrowRotationAngle))
        }
        .animation(.easeInOut, value: selectedArea)
        .padding(16)
        .frame(minWidth: 0, maxWidth: .infinity)
        .contentShape(Rectangle())
        .onTapGesture {
            onClick()
        }
        
    }
}

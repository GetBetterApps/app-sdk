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
    
    init(selectedArea: Area?, noteType: NoteType, onClick: @escaping () -> Void) {
        self.selectedArea = selectedArea
        self.onClick = onClick
        self.noteType = noteType
    }
    
    var body: some View {
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
                    .padding(.leading, 12)
            }
        }
        .animation(.easeInOut, value: selectedArea)
        .padding(16)
        .frame(minWidth: 0, maxWidth: .infinity)
        .onTapGesture {
            onClick()
        }
        
    }
}

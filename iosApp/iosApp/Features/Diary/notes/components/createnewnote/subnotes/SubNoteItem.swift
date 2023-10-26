//
//  SubNoteItem.swift
//  iosApp
//
//  Created by velkonost on 26.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct SubNoteItem: View {
    
    let item: SubNoteUI
    let onDeleteSubNote: (SubNoteUI) -> Void
    
    init(item: SubNoteUI, onDeleteSubNote: @escaping (SubNoteUI) -> Void) {
        self.item = item
        self.onDeleteSubNote = onDeleteSubNote
    }
    
    var body: some View {
        HStack {
            Text(item.text)
                .style(.titleSmall)
                .foregroundColor(.textSecondaryTitle)
                .padding(.leading, 12)
            Spacer()
            
            ZStack {
                Image(uiImage: SharedR.images().ic_close.toUIImage()!)
                    .resizable()
                    .renderingMode(.template)
                    .foregroundColor(.iconInactive)
                    .frame(width: 24, height: 24, alignment: .leading)
                    .onTapGesture {
                        onDeleteSubNote(item)
                    }
            }
            .padding(.trailing, 12)
            .frame(width: 36, height: 36, alignment: .center)
            .background(
                RoundedRectangle(cornerRadius: 12)
                    .fill(Color.backgroundItem)
            )
        }
        .frame(height: 56)
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color.textFieldBackground)
        )
    }
}

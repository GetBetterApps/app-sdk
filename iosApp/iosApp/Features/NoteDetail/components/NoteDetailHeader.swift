//
//  NoteDetailHeader.swift
//  iosApp
//
//  Created by velkonost on 03.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct NoteDetailHeader : View {
    
    let noteType: NoteType
    let isNotePrivate: Bool
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    init(noteType: NoteType, isNotePrivate: Bool) {
        self.noteType = noteType
        self.isNotePrivate = isNotePrivate
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
            
            Text(
                noteType == NoteType.default_ ? SharedR.strings().note_detail_title.desc().localized() : SharedR.strings().goal_detail_title.desc().localized()
            )
                .style(.headlineSmall)
                .foregroundColor(.textTitle)
                .padding(.leading, 12)
            Spacer()
            
            if isNotePrivate {
                Image(uiImage: SharedR.images().ic_lock.toUIImage()!)
                    .resizable()
                    .renderingMode(.template)
                    .foregroundColor(.iconInactive)
                    .frame(width: 32, height: 32)
                    .scaledToFill()
                    .padding(4)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .fill(Color.backgroundIcon)
                    )
                    .padding(.trailing, 20)
                    
            }
        }
    }
}

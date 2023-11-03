//
//  SubNotesBlock.swift
//  iosApp
//
//  Created by velkonost on 26.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct SubNotesBlock: View {
    
    let items: [SubNoteUI]
    @Binding var newSubNote: SubNoteUI
    
    let onlyView: Bool
    let isCompleteVisible: Bool
    
    let onNewSubNoteChanged: (String) -> Void
    let onAddNewSubNote: () -> Void
    let onSubNoteDelete: (SubNoteUI) -> Void
    let onCompleteClick: ((SubNoteUI) -> Void)?
    
    @Binding private var isSubNotesBlockPickerVisible: Bool
    
    init(items: [SubNoteUI], newSubNote: Binding<SubNoteUI>, onlyView: Bool = false, isCompleteVisible: Bool = false, onNewSubNoteChanged: @escaping (String) -> Void, onAddNewSubNote: @escaping () -> Void, onSubNoteDelete: @escaping (SubNoteUI) -> Void, isSubNotesBlockPickerVisible: Binding<Bool>, onCompleteClick: ((SubNoteUI) -> Void)? = nil) {
        self.items = items
        self._newSubNote = newSubNote
        self.onlyView = onlyView
        self.isCompleteVisible = isCompleteVisible
        self.onNewSubNoteChanged = onNewSubNoteChanged
        self.onAddNewSubNote = onAddNewSubNote
        self.onSubNoteDelete = onSubNoteDelete
        self.onCompleteClick = onCompleteClick
        self._isSubNotesBlockPickerVisible = isSubNotesBlockPickerVisible
    }
    
    var body: some View {
        PrimaryBox(
            padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero)
        ) {
            VStack {
                SubNotesHeader(
                    isSubNotesBlockVisible: isSubNotesBlockPickerVisible,
                    onClick: {
                        withAnimation {
                            isSubNotesBlockPickerVisible.toggle()
                        }
                    }
                )
                
                if isSubNotesBlockPickerVisible {
                    LazyVStack {
                        ForEach(items, id: \.self.id) { item in
                            SubNoteItem(
                                item: item,
                                onlyView: onlyView,
                                isCompleteVisible: isCompleteVisible,
                                onDeleteSubNote: onSubNoteDelete,
                                onCompleteClick: onCompleteClick
                            )
                        }
                        
                        if !onlyView {
                            AddSubNoteItem(
                                value: $newSubNote,
                                placeholderText: SharedR.strings().create_note_subnote_hint.desc().localized(),
                                onValueChanged: onNewSubNoteChanged,
                                onAddSubNote: onAddNewSubNote
                            )
                        }
                    }
                    .padding(.leading, 16)
                    .padding(.trailing, 16)
                    
                }
            }
            .animation(.easeInOut, value: onlyView)
            .animation(.easeInOut, value: isCompleteVisible)
            .padding(.bottom, 16)
            
        }
        .animation(.easeInOut, value: items)
    }
}

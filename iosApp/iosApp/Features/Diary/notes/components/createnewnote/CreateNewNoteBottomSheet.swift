//
//  CreateNewNoteBottomSheet.swift
//  iosApp
//
//  Created by velkonost on 12.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import SwiftUIPager

struct AreaWrapper : Identifiable, Equatable, Hashable {
    var id: String = UUID().uuidString
    
    var area: Area
}

struct CreateNewNoteBottomSheet: View {
    
    @Binding private var state: CreateNewNoteViewState
    
    @State private var isAreaPickerVisible = false
    @State var currentAreaIndex: Int = 0
    
    let onAreaSelect: (Area) -> Void
    let onTextChanged: (String) -> Void
    let onPrivateChanged: () -> Void
    
    let onNewTagChanged: (String) -> Void
    let onAddNewTag: () -> Void
    let onTagDelete: (String) -> Void
    
    init(
        state: Binding<CreateNewNoteViewState>,
        onAreaSelect: @escaping (Area) -> Void,
        onTextChanged: @escaping (String) -> Void,
        onPrivateChanged: @escaping () -> Void,
        onNewTagChanged: @escaping (String) -> Void,
        onAddNewTag: @escaping () -> Void,
        onTagDelete: @escaping (String) -> Void
    ) {
        self._state = state
        self.onAreaSelect = onAreaSelect
        self.onTextChanged = onTextChanged
        self.onPrivateChanged = onPrivateChanged
        self.onNewTagChanged = onNewTagChanged
        self.onAddNewTag = onAddNewTag
        self.onTagDelete = onTagDelete
    }
    
    var body: some View {
        @State var isNotePrivate = state.isPrivate
        @State var newTag = state.newTag
        
        ZStack {
            Color.mainBackground
            
            VStack {
                if state.isLoading {
                    Loader()
                } else {
                    Text(
                        state.type == NoteType.default_ ? SharedR.strings().create_note_title.desc().localized() : SharedR.strings().create_goal_title.desc().localized()
                    )
                    .style(.headlineSmall)
                    .foregroundColor(.textTitle)
                    .frame(alignment: .center)
                    
                    AreaPicker(
                        areas: state.availableAreas,
                        selectedArea: state.selectedArea,
                        noteType: state.type,
                        onAreaSelect: onAreaSelect,
                        isAreaPickerVisible: $isAreaPickerVisible
                    )
                    
                    MultilineTextField(
                        value: state.text,
                        placeholderText:
                            state.type == NoteType.default_ ? SharedR.strings().create_note_text_hint.desc().localized() : SharedR.strings().create_goal_text_hint.desc().localized()
                    ) { value in
                        onTextChanged(value)
                    }
                    
                    PrivateSwitch(
                        onCheckedChange: onPrivateChanged,
                        isEnabled: state.selectedArea != nil && state.selectedArea?.isPrivate == false,
                        isPrivate: $isNotePrivate
                    )
                    
                    TagsBlock(
                        tags: state.tags,
                        newTag: $newTag,
                        onNewTagChanged: onNewTagChanged,
                        onAddNewTag: onAddNewTag,
                        onTagDelete: onTagDelete
                    )
                    
                    Spacer()
                }
                
            }
            .padding(20)
        }
        .ignoresSafeArea(.all)
        .onTapGesture {
            endTextEditing()
        }
    }
}

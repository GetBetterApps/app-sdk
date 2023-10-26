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
    @State private var isSubNoteBlockVisible = false
    @State var currentAreaIndex: Int = 0
    
    let onAreaSelect: (Area) -> Void
    let onTextChanged: (String) -> Void
    let onPrivateChanged: () -> Void
    
    let onNewTagChanged: (String) -> Void
    let onAddNewTag: () -> Void
    let onTagDelete: (String) -> Void
    
    let onNewSubNoteChanged: (String) -> Void
    let onAddNewSubNote: () -> Void
    let onSubNoteDelete: (SubNoteUI) -> Void
    
    init(
        state: Binding<CreateNewNoteViewState>,
        onAreaSelect: @escaping (Area) -> Void,
        onTextChanged: @escaping (String) -> Void,
        onPrivateChanged: @escaping () -> Void,
        onNewTagChanged: @escaping (String) -> Void,
        onAddNewTag: @escaping () -> Void,
        onTagDelete: @escaping (String) -> Void,
        onNewSubNoteChanged: @escaping (String) -> Void,
        onAddNewSubNote: @escaping () -> Void,
        onSubNoteDelete: @escaping (SubNoteUI) -> Void
    ) {
        self._state = state
        self.onAreaSelect = onAreaSelect
        self.onTextChanged = onTextChanged
        self.onPrivateChanged = onPrivateChanged
        
        self.onNewTagChanged = onNewTagChanged
        self.onAddNewTag = onAddNewTag
        self.onTagDelete = onTagDelete
        
        self.onNewSubNoteChanged = onNewSubNoteChanged
        self.onAddNewSubNote = onAddNewSubNote
        self.onSubNoteDelete = onSubNoteDelete
    }
    
    var body: some View {
        @State var isNotePrivate = state.isPrivate
        @State var newTag = state.newTag
        @State var newSubNote = state.newSubNote
        
        ZStack {
            Color.mainBackground
        
            ScrollView(.vertical, showsIndicators: false) {
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
                        
                        SubNotesBlock(
                            items: state.subNotes,
                            newSubNote: $newSubNote,
                            onNewSubNoteChanged: onNewSubNoteChanged,
                            onAddNewSubNote: onAddNewSubNote,
                            onSubNoteDelete: onSubNoteDelete,
                            isSubNotesBlockPickerVisible: $isSubNoteBlockVisible
                        )
                        
                        Spacer()
                    }
                    
                }
                .padding(20)
                .padding(.bottom, 400)
            }
            .ignoresSafeArea(.keyboard)
        }
        .frame(minHeight: 0, maxHeight: .infinity)
        .ignoresSafeArea(.all)
        .onTapGesture {
            endTextEditing()
        }
    }
}

struct KeyboardResponsiveModifier: ViewModifier {
  @State private var offset: CGFloat = 0

  func body(content: Content) -> some View {
    content
      .padding(.bottom, offset)
      .onAppear {
        NotificationCenter.default.addObserver(forName: UIResponder.keyboardWillShowNotification, object: nil, queue: .main) { notif in
          let value = notif.userInfo![UIResponder.keyboardFrameEndUserInfoKey] as! CGRect
          let height = value.height
          let bottomInset = UIApplication.shared.windows.first?.safeAreaInsets.bottom
          self.offset = height - (bottomInset ?? 0)
        }

        NotificationCenter.default.addObserver(forName: UIResponder.keyboardWillHideNotification, object: nil, queue: .main) { notif in
          self.offset = 0
        }
    }
  }
}

extension View {
  func keyboardResponsive() -> ModifiedContent<Self, KeyboardResponsiveModifier> {
    return modifier(KeyboardResponsiveModifier())
  }
}

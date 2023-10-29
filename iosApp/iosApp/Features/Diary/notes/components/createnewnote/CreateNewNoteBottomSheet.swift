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
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

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
    
    let onSetCompletionDate: (Int64?) -> Void
    
    let onCreateClick: () -> Void
    
    @State private var resourceMessageText: String?
    @State private var snackBar: MessageType.SnackBar?
    @State private var showSnackBar: Bool = false
    @State private var messageDequeObserver: Task<(), Error>? = nil
    
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
        onSubNoteDelete: @escaping (SubNoteUI) -> Void,
        onSetCompletionDate: @escaping (Int64?) -> Void,
        onCreateClick: @escaping () -> Void
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
        
        self.onSetCompletionDate = onSetCompletionDate
        self.onCreateClick = onCreateClick
    }
    
    var body: some View {
        @State var isNotePrivate = state.isPrivate
        @State var newTag = state.newTag
        @State var newSubNote = state.newSubNote
        
        ZStack {
            Color.mainBackground
            
            if state.isLoading {
                VStack {
                    Loader()
                }
            } else {
                
                ScrollView(.vertical, showsIndicators: false) {
                    ScrollViewReader { value in
                        VStack {
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
                            .onTapGesture {
                                withAnimation {
                                    value.scrollTo(1, anchor: .bottom)
                                }
                            }
                            .id(1)
                            
                            if state.type == NoteType.goal {
                                CompletionDateBlock(
                                    onSetCompletionDate: onSetCompletionDate
                                )
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
                            .onTapGesture {
                                withAnimation {
                                    value.scrollTo(2, anchor: .bottom)
                                }
                            }
                            .id(2)
                            
                            if state.type == NoteType.goal {
                                SubNotesBlock(
                                    items: state.subNotes,
                                    newSubNote: $newSubNote,
                                    onNewSubNoteChanged: onNewSubNoteChanged,
                                    onAddNewSubNote: onAddNewSubNote,
                                    onSubNoteDelete: onSubNoteDelete,
                                    isSubNotesBlockPickerVisible: $isSubNoteBlockVisible
                                )
                                .onTapGesture {
                                    withAnimation {
                                        value.scrollTo(3, anchor: .bottom)
                                    }
                                }
                                .id(3)
                            }
                            
                            Spacer()
                            
                            
                        }
                        .padding(20)
                        .padding(.bottom, 140)
                    }
                    Spacer()
                }
            
            
            VStack {
                Spacer()
                
                VStack {
                    Spacer()
                        .frame(height: 20)
                    
                    AppButton(
                        labelText: SharedR.strings().diary_areas_create_button.desc().localized(),
                        isLoading: false
                    ) {
                        onCreateClick()
                    }
                    .padding(.bottom, 70)
                }
                .frame(minWidth: 0, maxWidth: .infinity)
                .background(
                    Rectangle()
                        .fill(LinearGradient(gradient: Gradient(colors: [
                            .mainBackground,
                            .mainBackground,
                            .mainBackground,
                            .clear
                        ]), startPoint: .bottom, endPoint: .top)
                        )
                    
                )
                
                
            }.ignoresSafeArea(.keyboard)
            
            Spacer()
        }
    }
        .ignoresSafeArea(.container)
        .onTapGesture {
            endTextEditing()
        }
        .snackBar(
            isShowing: $showSnackBar,
            text: resourceMessageText ?? "",
            snackBar: snackBar
        )
        .onAppear {
            if messageDequeObserver == nil {
                messageDequeObserver = Task {
                    for try await message in asyncSequence(for: MessageDeque.shared.invoke()) {
                        handle(resource: message)
                    }
                }
            }
        }
        .onDisappear {
            messageDequeObserver?.cancel()
            messageDequeObserver = nil
        }
}
}

extension CreateNewNoteBottomSheet {
    private func handle(resource message: Message) {
        switch message.messageType {
            
        case let snackBar as MessageType.SnackBar : do {
            if showSnackBar == false {
                resourceMessageText = message.text != nil ? message.text : message.textResource?.localized()
                self.snackBar = snackBar
                withAnimation {
                    showSnackBar.toggle()
                }
                DispatchQueue.main.asyncAfter(deadline: .now() + 5) {
                    Task { try await MessageDeque.shared.dequeue() }
                }
            }
        }
            
        default: break
        }
    }
}

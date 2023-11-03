//
//  NoteDetailScreen.swift
//  iosApp
//
//  Created by velkonost on 03.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct NoteDetailScreen : View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @StateViewModel var viewModel: NoteDetailViewModel
    @State private var eventsObserver: Task<(), Error>? = nil
    
    @State private var confirmDeleteNoteDialog = false
    @State private var isSubNotesBlockVisible = true
    
    @State private var selectedAreaId: Int32? = nil
    @State private var showingAreaDetailSheet = false
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! NoteDetailViewState
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
                        NoteDetailHeader(
                            noteType: state.noteType,
                            isNotePrivate: state.isNotePrivate
                        )
                        
                        if state.area != nil {
                            AreaData(
                                area: state.area!,
                                onClick: {
                                    selectedAreaId = state.area!.id
                                    showingAreaDetailSheet = true
                                }
                            )
                        }
                        
                        MultilineTextField(
                            value: state.noteText,
                            placeholderText:
                                state.noteType == NoteType.default_ ? SharedR.strings().create_note_text_hint.desc().localized() : SharedR.strings().create_goal_text_hint.desc().localized(),
                            isEnabled: state.noteState == NoteState.editing
                        ) { value in
                            viewModel.dispatch(action: NoteDetailActionTextChanged(value: value))
                        }
                        .onTapGesture {
                            withAnimation {
                                value.scrollTo(1, anchor: .bottom)
                            }
                        }
                        .id(1)
                        
                        if state.noteType == NoteType.goal {
                            CompletionDateBlock(
                                enabled: state.noteState == NoteState.editing,
                                isLoading: state.isCompleteGoalLoading,
                                initialValue: state.expectedCompletionDate as? Int64,
                                initialValueStr: state.expectedCompletionDateStr,
                                isCompleteVisible: true,
                                completionDateStr: state.completionDateStr,
                                onSetCompletionDate: { value in
                                    viewModel.dispatch(action: NoteDetailActionSetCompletionDate(value: KotlinLong(value: value!)))
                                },
                                onCompleteClick: {
                                    if state.completionDate == nil {
                                        viewModel.dispatch(action: NoteDetailActionCompleteClick())
                                    } else {
                                        viewModel.dispatch(action: NoteDetailActionUnCompleteClick())
                                    }
                                }
                            )
                        }
                        
                        if !state.tags.isEmpty || state.noteState == NoteState.editing {
                            TagsBlock(
                                tags: state.tags,
                                newTag: $newTag,
                                onlyView: state.noteState == NoteState.view,
                                onNewTagChanged: { value in
                                    viewModel.dispatch(action: NoteDetailActionNewTagTextChanged(value: value))
                                },
                                onAddNewTag: {
                                    viewModel.dispatch(action: NoteDetailActionAddNewTag())
                                },
                                onTagDelete: { value in
                                    viewModel.dispatch(action: NoteDetailActionRemoveTag(value: value))
                                }
                            )
                            .onTapGesture {
                                withAnimation {
                                    value.scrollTo(2, anchor: .bottom)
                                }
                            }
                            .id(2)
                        }
                        
                        if state.noteType == NoteType.goal && (!state.subNotes.isEmpty || state.noteState == NoteState.editing) {
                            SubNotesBlock(
                                items: state.subNotes,
                                newSubNote: $newSubNote,
                                onlyView: state.noteState == NoteState.view,
                                isCompleteVisible: state.allowEdit,
                                onNewSubNoteChanged: { value in
                                    viewModel.dispatch(action: NoteDetailActionNewSubNoteTextChanged(value: value))
                                },
                                onAddNewSubNote: {
                                    viewModel.dispatch(action: NoteDetailActionAddSubNote())
                                },
                                onSubNoteDelete: { value in
                                    viewModel.dispatch(action: NoteDetailActionRemoveSubNote(value: value))
                                },
                                isSubNotesBlockPickerVisible: $isSubNotesBlockVisible,
                                onCompleteClick: { value in
                                    if value.completionDate == nil {
                                        viewModel.dispatch(action: NoteDetailActionCompleteSubNoteClick(value: value))
                                    } else {
                                        viewModel.dispatch(action: NoteDetailActionUnCompleteSubNoteClick(value: value))
                                    }
                                }
                            )
                            .onTapGesture {
                                withAnimation {
                                    value.scrollTo(3, anchor: .bottom)
                                }
                            }
                            .id(3)
                        }
                            
                            if state.allowEdit {
                                ActionButtons(
                                    noteState: state.noteState,
                                    onEditClick: {
                                        viewModel.dispatch(action: NoteDetailActionStartEditClick())
                                    },
                                    onDeleteClick: {
                                        confirmDeleteNoteDialog = true
                                    },
                                    onSaveClick: {
                                        viewModel.dispatch(action: NoteDetailActionEndEditClick())
                                    },
                                    onCancelSaveClick: {
                                        viewModel.dispatch(action: NoteDetailActionCancelEditClick())
                                    }
                                )
                            }
                        
                        
                    }
                }
                //                .ignoresSafeArea(.all)
                .padding(.bottom, 40)
                .padding(.horizontal, 20)
                .onTapGesture {
                    endTextEditing()
                }
            }
        }
        .alert(
            state.noteType == NoteType.default_ ? SharedR.strings().note_detail_confirm_delete_title.desc().localized() : SharedR.strings().goal_detail_confirm_delete_title.desc().localized(), isPresented: $confirmDeleteNoteDialog) {
            Button(SharedR.strings().confirm.desc().localized()) {
                viewModel.dispatch(action: NoteDetailActionDeleteClick())
            }
            Button(SharedR.strings().cancel.desc().localized(), role: .cancel) {}
        } message: {
            Text(SharedR.strings().note_detail_confirm_delete_text.desc().localized())
        }
        .sheet(isPresented: $showingAreaDetailSheet) {
            AreaDetailScreen(
                areaId: $selectedAreaId,
                onClose: {
                    showingAreaDetailSheet = false
                },
                onAreaChanged: { areaId in
                    viewModel.dispatch(action: NoteDetailActionAreaChanged())
                }
            )
        }
        .onAppear {
            observeEvents()
        }
    }
}

extension NoteDetailScreen {
    func observeEvents() {
        if eventsObserver == nil {
            eventsObserver = Task {
                for try await event in asyncSequence(for: viewModel.events) {
                    switch(event) {
                    case _ as NoteDetailEventDeleteSuccess: do {
                        presentationMode.wrappedValue.dismiss()
                    }
                        
                    case _ as NoteDetailEventEditSuccess: do {
                        // TODO
                    }
                   
                    
                    default:
                        break
                    }
                }
            }
        }
    }
    
}

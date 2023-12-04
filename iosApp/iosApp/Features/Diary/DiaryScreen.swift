//
//  DiaryScreen.swift
//  iosApp
//
//  Created by velkonost on 21.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct DiaryScreen: View {
    
    @StateViewModel var viewModel: DiaryViewModel
    @State private var eventsObserver: Task<(), Error>? = nil
    
    @State private var showingCreateNewAreaSheet = false
    @State private var showingAreaDetailSheet = false
    @State private var showingCreateNewNoteSheet = false
    
    @State private var selectedPage: Int = 0
    
    @State private var selectedAreaId: Int32? = nil
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! DiaryViewState
        @State var createNewAreaState = state.createNewAreaViewState
        @State var createNewNoteState = state.createNewNoteViewState
        
        @State var notesViewState = state.notesViewState
        @State var notesLoading = state.notesViewState.isLoading
        
        VStack {
            PrimaryTabs(
                selectedPage: $selectedPage,
                tabs: state.tabs.map { tab in tab.title.localized() }
            )
            
            switch(selectedPage) {
            case 0: NotesView(
                state: $notesViewState,
                isLoading: notesViewState.isLoading,
                items: state.notesViewState.items,
                createGoalClick: {
                    if state.createNewNoteViewState.availableAreas.isEmpty {
                        viewModel.dispatch(action: CreateNewNoteActionCloseBecauseZeroAreas())
                    } else {
                        viewModel.dispatch(action: CreateNewNoteActionOpenGoal())
                        showingCreateNewNoteSheet = true
                    }
                    
                },
                createNoteClick: {
                    if state.createNewNoteViewState.availableAreas.isEmpty {
                        viewModel.dispatch(action: CreateNewNoteActionCloseBecauseZeroAreas())
                    } else {
                        viewModel.dispatch(action: CreateNewNoteActionOpenDefault())
                        showingCreateNewNoteSheet = true
                    }
                },
                itemClick: { value in
                    viewModel.dispatch(action: NoteClick(value: value))
                },
                itemLikeClick: { value in
                    viewModel.dispatch(action: NoteLikeClick(value: value))
                },
                onBottomReach: {
                    viewModel.dispatch(action: DiaryActionNotesLoadNextPage())
                }
            )
            case 1: AreasView(
                items: state.areasViewState.items, 
                isLoading: state.areasViewState.isLoading,
                areaClick: { areaId in
                    selectedAreaId = areaId
                    showingAreaDetailSheet = true
                },
                areaLikeClick: { value in
                    viewModel.dispatch(action: AreaLikeClick(value: value))
                },
                createNewAreaClick: {
                    viewModel.dispatch(action: CreateNewAreaActionOpen())
                    showingCreateNewAreaSheet = true
                },
                addExistingAreaClick: {
                    viewModel.dispatch(action: AddAreaClick())
                }
            )
                
            default:
                TasksView(
                    isLoading: state.tasksViewState.isLoading,
                    favoriteItems: state.tasksViewState.favoriteItems,
                    currentItems: state.tasksViewState.currentItems,
                    completedItems: state.tasksViewState.completedItems,
                    onTaskClick: { value in
                        viewModel.dispatch(action: TaskClick(value: value))
                    },
                    onTaskFavoriteClick: { value in
                        viewModel.dispatch(action: DiaryActionTaskFavoriteClick(value: value))
                    },
                    onTaskListUpdateClick: {
                        viewModel.dispatch(action: DiaryActionTasksListUpdateClick())
                    }
                )
            }
            Spacer()
            
        }.sheet(isPresented: $showingCreateNewAreaSheet) {
            CreateNewAreaBottomSheet(state: $createNewAreaState, emojiItems: state.emojiList) { value in
                withAnimation {
                    viewModel.dispatch(action: CreateNewAreaActionEmojiSelect(value: value))
                }
            } onNameChanged: { value in
                viewModel.dispatch(action: CreateNewAreaActionNameChanged(value: value))
            } onDescriptionChanged: { value in
                viewModel.dispatch(action: CreateNewAreaActionDescriptionChanged(value: value))
            } onRequiredLevelChanged: { value in
                viewModel.dispatch(action: CreateNewAreaActionRequiredLevelChanged(value: Int32(value)))
            } onPrivateChanged: {
                viewModel.dispatch(action: CreateNewAreaActionPrivateChanged())
            } onCreateClick: {
                viewModel.dispatch(action: CreateNewAreaActionCreateClick())
            }
        }
        .sheet(isPresented: $showingAreaDetailSheet) {
            AreaDetailScreen(
                areaId: $selectedAreaId,
                onClose: {
                    showingAreaDetailSheet = false
                },
                onAreaChanged: { areaId in
                    viewModel.refreshData()
                }
            )
        }
        .sheet(isPresented: $showingCreateNewNoteSheet) {
            CreateNewNoteBottomSheet(
                state: $createNewNoteState,
                onAreaSelect: { area in
                    viewModel.dispatch(action: CreateNewNoteActionAreaSelect(value: area))
                },
                onTextChanged: { value in
                    viewModel.dispatch(action: CreateNewNoteActionTextChanged(value: value))
                },
                onPrivateChanged: {
                    viewModel.dispatch(action: CreateNewNoteActionPrivateChanged())
                },
                onNewTagChanged: { value in
                    viewModel.dispatch(action: CreateNewNoteActionNewTagTextChanged(value: value))
                },
                onAddNewTag: {
                    viewModel.dispatch(action: CreateNewNoteActionAddNewTag())
                },
                onTagDelete: { value in
                    viewModel.dispatch(action: CreateNewNoteActionRemoveTag(value: value))
                },
                onNewSubNoteChanged: { value in
                    viewModel.dispatch(action: CreateNewNoteActionNewSubNoteTextChanged(value: value))
                },
                onAddNewSubNote: {
                    viewModel.dispatch(action: CreateNewNoteActionAddSubNote())
                },
                onSubNoteDelete: { value in
                    viewModel.dispatch(action: CreateNewNoteActionRemoveSubNote(value: value))
                },
                onSetCompletionDate: { value in
                    viewModel.dispatch(action: CreateNewNoteActionSetCompletionDate(value: value != nil ? KotlinLong(value: value!) : nil))
                },
                onCreateClick: {
                    viewModel.dispatch(action: CreateNewNoteActionCreateClick())
                }
            )
        }
        .onAppear {
            viewModel.refreshData()
            observeEvents()
        }
        .onDisappear {
            eventsObserver?.cancel()
            eventsObserver = nil
        }
    }
}

extension DiaryScreen {
    func observeEvents() {
        if eventsObserver == nil {
            eventsObserver = Task {
                for try await event in asyncSequence(for: viewModel.events) {
                    switch(event) {
                    case _ as CreateNewAreaEventCreatedSuccess: do {
                        showingCreateNewAreaSheet = false
                        viewModel.refreshData()
                    }
                    case _ as CreateNewNoteEventCreatedSuccess: do {
                        showingCreateNewNoteSheet = false
                        viewModel.refreshData()
                    }
                    default:
                        break
                    }
                }
            }
        }
    }
}

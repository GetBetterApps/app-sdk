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
            HStack {
                PrimaryTabs(
                    selectedPage: $selectedPage,
                    tabs: state.tabs.map { tab in tab.title.localized() }
                )
                HintButton {
                    viewModel.dispatch(action: DiaryActionHintClick(firstTime: false, index: Int32(selectedPage)))
                }
                .padding(.leading, -10)
                .padding(.trailing, 16)
            }
            
            switch(selectedPage) {
            case 0: NotesView(
                showAd: state.showAds,
                adPosition: Int(state.adPosition),
                state: $notesViewState,
                isLoading: notesViewState.isLoading,
                items: state.notesViewState.items,
                createGoalClick: {
                    if state.createNewNoteViewState.availableAreas.isEmpty {
                        viewModel.dispatch(action_: CreateNewNoteActionCloseBecauseZeroAreas())
                    } else {
                        viewModel.dispatch(action_: CreateNewNoteActionOpenGoal())
                        showingCreateNewNoteSheet = true
                    }
                    
                },
                createNoteClick: {
                    if state.createNewNoteViewState.availableAreas.isEmpty {
                        viewModel.dispatch(action_: CreateNewNoteActionCloseBecauseZeroAreas())
                    } else {
                        viewModel.dispatch(action_: CreateNewNoteActionOpenDefault())
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
                showAd: state.showAds,
                adPosition: Int(state.adPosition),
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
                    showAd: state.showAds,
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
            
        }
        .sheet(isPresented: $showingCreateNewAreaSheet) {
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
            } onHintClick: {
                viewModel.dispatch(action: CreateNewAreaActionHintClick())
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
                    viewModel.dispatch(action_: CreateNewNoteActionAreaSelect(value: area))
                },
                onTaskSelect: { task in
                    viewModel.dispatch(action_: CreateNewNoteActionTaskSelect(value: task))
                },
                onTextChanged: { value in
                    viewModel.dispatch(action_: CreateNewNoteActionTextChanged(value: value))
                },
                onPrivateChanged: {
                    viewModel.dispatch(action_: CreateNewNoteActionPrivateChanged())
                },
                onNewTagChanged: { value in
                    viewModel.dispatch(action_: CreateNewNoteActionNewTagTextChanged(value: value))
                },
                onAddNewTag: {
                    viewModel.dispatch(action_: CreateNewNoteActionAddNewTag())
                },
                onTagDelete: { value in
                    viewModel.dispatch(action_: CreateNewNoteActionRemoveTag(value: value))
                },
                onNewSubNoteChanged: { value in
                    viewModel.dispatch(action_: CreateNewNoteActionNewSubNoteTextChanged(value: value))
                },
                onAddNewSubNote: {
                    viewModel.dispatch(action_: CreateNewNoteActionAddSubNote())
                },
                onSubNoteDelete: { value in
                    viewModel.dispatch(action_: CreateNewNoteActionRemoveSubNote(value: value))
                },
                onSetCompletionDate: { value in
                    viewModel.dispatch(action_: CreateNewNoteActionSetCompletionDate(value: value != nil ? KotlinLong(value: value!) : nil))
                },
                onCreateClick: {
                    viewModel.dispatch(action_: CreateNewNoteActionCreateClick())
                },
                onHintClick: {
                    viewModel.dispatch(action_: CreateNewNoteActionHintClick())
                }
            )
        }
        .onAppear {
            viewModel.refreshData()
            observeEvents()
            
            viewModel.dispatch(action: DiaryActionHintClick(firstTime: true, index: Int32(0)))
        }
        .onDisappear {
            eventsObserver?.cancel()
            eventsObserver = nil
        }
        .onChange(of: selectedPage) { newValue in
            viewModel.dispatch(action: DiaryActionHintClick(firstTime: true, index: Int32(newValue)))
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
                    case _ as DiaryEventNewNoteCreatedSuccess: do {
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

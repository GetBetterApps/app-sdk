//
//  TaskDetailScreen.swift
//  iosApp
//
//  Created by velkonost on 03.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct TaskDetailScreen : View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @StateViewModel var viewModel: TaskDetailViewModel
    @State private var eventsObserver: Task<(), Error>? = nil
    
    @State private var selectedAreaId: Int32? = nil
    @State private var showingAreaDetailSheet = false
    @State private var showingCreateNewNoteSheet = false
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! TaskDetailViewState
        @State var createNewNoteState = state.createNewNoteViewState
        
        ZStack {
            if state.isLoading || state.task == nil {
                VStack {
                    Loader()
                }
            } else {
                ScrollView(.vertical, showsIndicators: false) {
                    ScrollViewReader { value in
                        LazyVStack(spacing: 0) {
                            TaskDetailHeader(
                                isShortInfo: state.task!.isShortInfo,
                                isFavorite: state.task!.isFavorite,
                                isFavoriteLoading: state.task!.isFavoriteLoading,
                                onFavoriteClick: {
                                    viewModel.dispatch(action: TaskDetailActionFavoriteClick())
                                }
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
                            
                            HStack {
                                Text(state.task!.name)
                                    .style(.labelLarge, withSize: 18)
                                    .foregroundColor(.textLight)
                                    .padding(.top, 24)
                                Spacer()
                            }
                            
                            HStack {
                                Text(SharedR.strings().task_what_to_do_title.desc().localized())
                                    .style(.labelMedium)
                                    .foregroundColor(.textTitle)
                                    .padding(.top, 16)
                                Spacer()
                            }
                            
                            HStack {
                                Text(state.task!.whatToDo)
                                    .style(.labelMedium)
                                    .foregroundColor(.textPrimary)
                                    .padding(.top, 6)
                                Spacer()
                            }
                            
                            HStack {
                                Text(SharedR.strings().task_why_title.desc().localized())
                                    .style(.labelMedium)
                                    .foregroundColor(.textTitle)
                                    .padding(.top, 16)
                                Spacer()
                            }
                            
                            HStack {
                                Text(state.task!.why)
                                    .style(.labelMedium)
                                    .foregroundColor(.textPrimary)
                                    .padding(.top, 6)
                                Spacer()
                            }
                            
                            HStack {
                                Text(SharedR.strings().task_abilities_title.desc().localized())
                                    .style(.labelMedium)
                                    .foregroundColor(.textTitle)
                                    .padding(.top, 16)
                                Spacer()
                            }
                            
                            if !state.task!.isShortInfo {
                                ForEach(state.task!.abilities, id: \.self.id ) { ability in
                                    AbilityData(
                                        item: ability,
                                        onClick: { value in
                                            
                                        }
                                    )
                                }
                                
                                Text(SharedR.strings().task_mask_as.desc().localized().uppercased())
                                    .style(.labelMedium)
                                    .foregroundColor(.textPrimary)
                                    .padding(.top, 24)
                                
                                HStack(spacing: 0) {
                                    Text(SharedR.strings().task_not_interesting_title.desc().localized())
                                        .style(.labelLarge)
                                        .foregroundColor(state.task!.isNotInteresting ? .textLight : .textPrimary)
                                        .padding(.horizontal, 16)
                                        .padding(.vertical, 8)
                                        .background(
                                            RoundedRectangle(cornerRadius: 12)
                                                .fill(state.task!.isNotInteresting ? Color.buttonGradientStart : Color.backgroundItem)
                                                .shadow(radius: state.task!.isNotInteresting ? 8 : 0)
                                        )
                                        .padding(.trailing, 6)
                                        .onTapGesture {
                                            viewModel.dispatch(action: TaskDetailActionNotInterestingClick())
                                        }
                                    
                                    Text(SharedR.strings().task_completed_title.desc().localized())
                                        .style(.labelLarge)
                                        .foregroundColor(state.task!.isCompleted ? .textLight : .textPrimary)
                                        .padding(.horizontal, 16)
                                        .padding(.vertical, 8)
                                        .background(
                                            RoundedRectangle(cornerRadius: 12)
                                                .fill(state.task!.isCompleted ? Color.buttonGradientStart : Color.backgroundItem)
                                                .shadow(radius: state.task!.isCompleted ? 8 : 0)
                                        )
                                        .padding(.leading, 6)
                                        .onTapGesture {
                                            viewModel.dispatch(action: TaskDetailActionCompletedClick())
                                        }
                                }.padding(.top, 12)
                            } else {
                                AbilityDataHidden()
                            }
                            
                            if !state.userNotesViewState.items.isEmpty {
                                HStack {
                                    Text(SharedR.strings().task_user_notes_title.desc().localized())
                                        .style(.headlineSmall)
                                        .foregroundColor(.textPrimary)
                                    Spacer()
                                }
                                .padding(.top, 24)
                            }
                            
                            ForEach(state.userNotesViewState.items, id: \.self.id) { item in
                                NoteItem(
                                    item: item,
                                    onClick: { value in
                                        
                                    },
                                    onLikeClick: { value in
                                        
                                    }
                                )
                                .onAppear {
                                    checkPaginationThreshold(
                                        currentItemId: item.id,
                                        loadMorePrefetch: Int(state.userNotesViewState.loadMorePrefetch),
                                        isLoading: state.userNotesViewState.isLoading,
                                        onBottomReach: {
                                            viewModel.dispatch(action: TaskDetailActionUserNotesLoadNextPage())
                                        }
                                    )
                                }
                            }
                            
                            if state.userNotesViewState.items.isEmpty && state.userNotesViewState.isLoading {
                                Loader().frame(alignment: .center)
                            }
                            
                            
                            Spacer().frame(height: 140)
                        }
                        .frame(alignment: .leading)
                        .padding(.horizontal, 20)
                    }
                }
                .animation(.easeInOut, value: state.userNotesViewState)
                .onAppear {
                    if state.userNotesViewState.items.isEmpty {
                        viewModel.dispatch(action: TaskDetailActionUserNotesLoadNextPage())
                    }
                }
                
                if !state.task!.isShortInfo {
                    VStack(alignment: .trailing) {
                        Spacer()
                        AddNoteItem(
                            paddingBottom: 45,
                            createGoalClick: {
                                if state.createNewNoteViewState.availableAreas.isEmpty {
                                    viewModel.dispatch(action_: CreateNewNoteActionCloseBecauseZeroAreas())
                                } else {
                                    viewModel.dispatch(action: TaskDetailActionCreateGoalClick())
                                    showingCreateNewNoteSheet = true
                                }
                            },
                            createNoteClick: {
                                if state.createNewNoteViewState.availableAreas.isEmpty {
                                    viewModel.dispatch(action_: CreateNewNoteActionCloseBecauseZeroAreas())
                                } else {
                                    viewModel.dispatch(action: TaskDetailActionCreateNoteClick())
                                    showingCreateNewNoteSheet = true
                                }
                            }
                        )
                    }
                }
            }
        }
        .sheet(isPresented: $showingAreaDetailSheet) {
            AreaDetailScreen(
                areaId: $selectedAreaId,
                onClose: {
                    showingAreaDetailSheet = false
                },
                onAreaChanged: { areaId in
                    viewModel.dispatch(action: TaskDetailActionAreaChanged())
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
                }
            )
        }
        .onAppear {
            observeEvents()
        }
        .onDisappear {
            eventsObserver?.cancel()
            eventsObserver = nil
        }
        
    }
}

extension TaskDetailScreen {
    func observeEvents() {
        if eventsObserver == nil {
            eventsObserver = Task {
                for try await event in asyncSequence(for: viewModel.events) {
                    switch(event) {
                    case _ as TaskDetailEventNewNoteCreatedSuccess: do {
                        showingCreateNewNoteSheet = false
                    }
                    default:
                        break
                    }
                }
            }
        }
    }
    
    func checkPaginationThreshold(currentItemId: Int32, loadMorePrefetch: Int, isLoading: Bool, onBottomReach: () -> Void) {
        let data = (viewModel.viewStateValue as! TaskDetailViewState).userNotesViewState.items
        let thresholdIndex = data.index(data.endIndex, offsetBy: -loadMorePrefetch)
        
        if data.firstIndex(where: { $0.id == currentItemId })! >= thresholdIndex && !isLoading {
            onBottomReach()
        }
    }
}

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
    
    @State private var selectedPage: Int = 0
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! DiaryViewState
        @State var createNewAreaState = state.createNewAreaViewState
        
        VStack {
            PrimaryTabs(
                selectedPage: $selectedPage,
                tabs: state.tabs.map { tab in tab.title.localized() }
            )
            
            switch(selectedPage) {
            case 0: NotesView(
                isLoading: state.notesViewState.isLoading,
                createGoalClick: {
                    
                },
                createNoteClick: {
                    
                }
            )
            case 1: AreasView(
                items: state.areasViewState.items,
                isLoading: state.areasViewState.isLoading,
                areaClick: {
                    showingAreaDetailSheet = true
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
                    isLoading: state.tasksViewState.isLoading
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
            AreaDetailScreen()
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

extension DiaryScreen {
    func observeEvents() {
        if eventsObserver == nil {
            eventsObserver = Task {
                for try await event in asyncSequence(for: viewModel.events) {
                    switch(event) {
                    case _ as CreateNewAreaEventCreatedSuccess: do {
                        showingCreateNewAreaSheet = false
                    }
                    default:
                        break
                    }
                }
            }
        }
    }
}

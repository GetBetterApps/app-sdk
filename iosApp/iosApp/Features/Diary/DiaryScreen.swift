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

struct DiaryScreen: View {
    
    
    @StateViewModel var viewModel: DiaryViewModel
    @State private var showingCreateNewAreaSheet = false
    
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
            case 0: NotesView()
            case 1: AreasView() {
                viewModel.dispatch(action: CreateNewAreaActionOpen())
                showingCreateNewAreaSheet = true
            }
            default: TasksView()
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
            } onCreateClick: {
                viewModel.dispatch(action: CreateNewAreaActionCreateClick())
            }

            
                           
        }
    }
}

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
    
    init(state: Binding<CreateNewNoteViewState>, onAreaSelect: @escaping (Area) -> Void) {
        self._state = state
        self.onAreaSelect = onAreaSelect
    }
    
    var body: some View {
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
                    
                    Spacer()
                }
                
            }
            .padding(20)
        }
        .ignoresSafeArea(.all)
    }
}

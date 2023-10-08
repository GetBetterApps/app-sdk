//
//  AreaDetailScreen.swift
//  iosApp
//
//  Created by velkonost on 07.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct AreaDetailScreen: View {
    
    @StateObject var viewModel = AreaDetailVIewModelDelegate()
    @State private var eventsObserver: Task<(), Error>? = nil
    
    @Binding var areaId: String?
    
    @State private var isEmojiPickerVisible = false
    
    @State private var confirmDeleteAreaDialog = false
    @State private var confirmLeaveAreaDialog = false
    
    private let onClose: () -> Void
    
    init(
        areaId: Binding<String?>,
        onClose: @escaping () -> Void
    ) {
        self._areaId = areaId
        self.onClose = onClose
    }
    
    var body: some View {
        @State var state = viewModel.state
        
        ZStack {
            Color.mainBackground
            
            if state.isLoading {
                VStack {
                    Loader()
                }
            } else {
                if state.item != nil {
                    AreaDetailContent(
                        areaData: state.item!,
                        isEditing: state.isEditing,
                        isEmojiPickerVisible: $isEmojiPickerVisible,
                        onEmojiClick: { value in
                            viewModel.dispatch(action: AreaDetailActionEmojiChanged(value: value))
                        },
                        onNameChanged: { value in
                            viewModel.dispatch(action: AreaDetailActionNameChanged(value: value))
                        },
                        onDescriptionChanged: { value in
                            viewModel.dispatch(action: AreaDetailActionDescriptionChanged(value: value))
                        }
                    )
                    
                    AreaDetailBottomButtons(
                        isEditing: state.isEditing,
                        onEditClick: {
                            viewModel.dispatch(action: AreaDetailActionStartEdit())
                        },
                        onLeaveClick: {
                            confirmLeaveAreaDialog = true
                        },
                        onDeleteClick: {
                            confirmDeleteAreaDialog = true
                        },
                        onSaveClick: {
                            isEmojiPickerVisible = false
                            viewModel.dispatch(action: AreaDetailActionEndEdit())
                        }
                    )
                }
            }
        }
        .onAppear {
            viewModel.onAppear(areaId: areaId!)
            observeEvents()
        }
        .onDisappear(perform: viewModel.onDisappear)
        .edgesIgnoringSafeArea(.all)
        .onTapGesture {
            self.endTextEditing()
        }
        .alert(SharedR.strings().add_area_confirm_delete_title.desc().localized(), isPresented: $confirmDeleteAreaDialog) {
            Button(SharedR.strings().confirm.desc().localized()) {
                viewModel.dispatch(action: AreaDetailActionDeleteClick())
            }
            Button(SharedR.strings().cancel.desc().localized(), role: .cancel) {}
        } message: {
            Text(SharedR.strings().add_area_confirm_delete_text.desc().localized())
        }
        .alert(SharedR.strings().add_area_confirm_leave_title.desc().localized(), isPresented: $confirmLeaveAreaDialog) {
            Button(SharedR.strings().confirm.desc().localized()) {
                viewModel.dispatch(action: AreaDetailActionLeaveClick())
            }
            Button(SharedR.strings().cancel.desc().localized(), role: .cancel) {}
        } message: {
            Text(SharedR.strings().add_area_confirm_leave_text.desc().localized())
        }
        
    }
}

extension AreaDetailScreen {
    func observeEvents() {
        if eventsObserver == nil {
            eventsObserver = Task {
                for try await event in asyncSequence(for: viewModel.delegate.events) {
                    switch(event) {
                    case _ as AreaDetailEventLeaveSuccess: do {
                        onClose()
                    }
                    case _ as AreaDetailEventDeleteSuccess: do {
                        onClose()
                    }
                    
                    default:
                        break
                    }
                }
            }
        }
    }
}

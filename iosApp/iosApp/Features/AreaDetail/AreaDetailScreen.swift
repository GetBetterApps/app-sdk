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
    
    @Binding var areaId: Int32?
    
    @State private var isEmojiPickerVisible = false
    
    @State private var confirmDeleteAreaDialog = false
    @State private var confirmLeaveAreaDialog = false
    
    private let onClose: () -> Void
    private let onAreaChanged: (Int32) -> Void
    
    @State private var resourceMessageText: String?
    @State private var snackBar: MessageType.SnackBar?
    @State private var showSnackBar: Bool = false
    @State private var messageDequeObserver: Task<(), Error>? = nil
    
    init(
        areaId: Binding<Int32?>,
        onClose: @escaping () -> Void,
        onAreaChanged: @escaping (Int32) -> Void
    ) {
        self._areaId = areaId
        self.onClose = onClose
        self.onAreaChanged = onAreaChanged
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
                if state.modifiedItem != nil {
                    AreaDetailContent(
                        areaData: state.modifiedItem!,
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
                        },
                        onLikeClick: {
                            viewModel.dispatch(action: AreaDetailActionLikeClick())
                        }
                    )
                    
                    AreaDetailBottomButtons(
                        isJoinButtonVisible: state.isAllowJoin,
                        isEditButtonVisible: state.isAllowEdit,
                        isDeleteButtonVisible: state.isAllowDelete,
                        isLeaveButtonVisible: state.isAllowLeave,
                        isEditing: state.isEditing,
                        onJoinClick: {
                            viewModel.dispatch(action: AreaDetailActionJoinClick())
                        },
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
                        },
                        onCancelSaveClick: {
                            isEmojiPickerVisible = false
                            viewModel.dispatch(action: AreaDetailActionCancelEdit())
                            viewModel.onAppear(areaId: areaId!) // TODO: fix this with restore name & desc values locally
                        }
                    )
                }
            }
        }
        .snackBar(
            isShowing: $showSnackBar,
            text: resourceMessageText ?? "",
            snackBar: snackBar
        )
        .onAppear {
            viewModel.onAppear(areaId: areaId!)
            observeEvents()
            
            if messageDequeObserver == nil {
                messageDequeObserver = Task {
                    for try await message in asyncSequence(for: MessageDeque.shared.invoke()) {
                        handle(resource: message)
                    }
                }
            }
        }
        .onDisappear {
            viewModel.onDisappear()
            messageDequeObserver?.cancel()
            messageDequeObserver = nil
        }
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
                        let areaId = (event as! AreaDetailEventLeaveSuccess).areaId
                        onAreaChanged(areaId)
                    }
                    case _ as AreaDetailEventDeleteSuccess: do {
                        let areaId = (event as! AreaDetailEventDeleteSuccess).areaId
                        onAreaChanged(areaId)
                        onClose()
                    }
                        
                    case _ as AreaDetailEventEditSuccess: do {
                        let areaId = (event as! AreaDetailEventEditSuccess).areaId
                        onAreaChanged(areaId)
                    }
                        
                    case _ as AreaDetailEventJoinSuccess: do {
                        let areaId = (event as! AreaDetailEventJoinSuccess).areaId
                        onAreaChanged(areaId)
                    }
                    
                    default:
                        break
                    }
                }
            }
        }
    }
    
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

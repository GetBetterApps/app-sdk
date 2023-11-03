//
//  AreaDetailBottomButtons.swift
//  iosApp
//
//  Created by velkonost on 08.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AreaDetailBottomButtons: View {
    
    let isJoinButtonVisible: Bool
    let isEditButtonVisible: Bool
    let isDeleteButtonVisible: Bool
    let isLeaveButtonVisible: Bool
    
    let isEditing: Bool
    
    let onJoinClick: () -> Void
    let onEditClick: () -> Void
    let onLeaveClick: () -> Void
    let onDeleteClick: () -> Void
    let onSaveClick: () -> Void
    let onCancelSaveClick: () -> Void
    
    init(isJoinButtonVisible: Bool, isEditButtonVisible: Bool, isDeleteButtonVisible: Bool, isLeaveButtonVisible: Bool, isEditing: Bool, onJoinClick: @escaping () -> Void, onEditClick: @escaping () -> Void, onLeaveClick: @escaping () -> Void, onDeleteClick: @escaping () -> Void, onSaveClick: @escaping () -> Void, onCancelSaveClick: @escaping () -> Void) {
        self.isJoinButtonVisible = isJoinButtonVisible
        self.isEditButtonVisible = isEditButtonVisible
        self.isDeleteButtonVisible = isDeleteButtonVisible
        self.isLeaveButtonVisible = isLeaveButtonVisible
        self.isEditing = isEditing
        self.onJoinClick = onJoinClick
        self.onEditClick = onEditClick
        self.onLeaveClick = onLeaveClick
        self.onDeleteClick = onDeleteClick
        self.onSaveClick = onSaveClick
        self.onCancelSaveClick = onCancelSaveClick
    }
    
    var body: some View {
        ZStack {
            VStack(spacing: 0) {
                Spacer()
                
                HStack {
                    if isEditing {
                        CancelSaveButton
                        SaveButton
                    } else {
                        
                        if isJoinButtonVisible {
                            JoinButton
                        }
                        
                        if isLeaveButtonVisible {
                            LeaveButton
                        }
                        
                        if isEditButtonVisible {
                            EditButton
                        }
                        
                        if isDeleteButtonVisible {
                            DeleteButton
                        }
                    }
                }
            }
            .padding(.bottom, 80)
        }
        .animation(.easeInOut(duration: 0.5), value: isEditing)
    }
}

extension AreaDetailBottomButtons {
    
    private var LeaveButton: some View {
        BottomActionButton(
            icon: SharedR.images().ic_exit.toUIImage()!,
            onClick: onLeaveClick
        )
    }
    
    private var EditButton: some View {
        BottomActionButton(
            icon: SharedR.images().ic_edit.toUIImage()!,
            onClick: onEditClick
        )
    }
    
    private var SaveButton: some View {
        BottomActionButton(
            icon: SharedR.images().ic_save.toUIImage()!,
            onClick: onSaveClick
        )
    }
    
    private var CancelSaveButton: some View {
        BottomActionButton(
            icon: SharedR.images().ic_cancel.toUIImage()!,
            onClick: onCancelSaveClick
        )
    }
    
    private var DeleteButton: some View {
        BottomActionButton(
            icon: SharedR.images().ic_trash.toUIImage()!,
            onClick: onDeleteClick
        )
    }
    
    private var JoinButton: some View {
        BottomActionButton(
            icon: SharedR.images().ic_enter.toUIImage()!,
            onClick: onJoinClick
        )
    }
    
}

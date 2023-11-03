//
//  ActionButtons.swift
//  iosApp
//
//  Created by velkonost on 03.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct ActionButtons: View {

    let noteState: NoteState
    let onEditClick: () -> Void
    let onDeleteClick: () -> Void
    let onSaveClick: () -> Void
    let onCancelSaveClick: () -> Void
    
    init(noteState: NoteState, onEditClick: @escaping () -> Void, onDeleteClick: @escaping () -> Void, onSaveClick: @escaping () -> Void, onCancelSaveClick: @escaping () -> Void) {
        self.noteState = noteState
        self.onEditClick = onEditClick
        self.onDeleteClick = onDeleteClick
        self.onSaveClick = onSaveClick
        self.onCancelSaveClick = onCancelSaveClick
    }

    var body: some View {
        ZStack {
            VStack(spacing: 0) {
                Spacer()
                
                HStack {
                    if noteState == NoteState.editing {
                        CancelSaveButton
                        SaveButton
                    } else {
                        DeleteButton
                        EditButton
                    }
                }
            }
            .padding(.bottom, 80)
        }
        .animation(.easeInOut(duration: 0.5), value: noteState)
    }
}

extension ActionButtons {
    private var EditButton: some View {
        BottomActionButton(
            icon: SharedR.images().ic_edit.toUIImage()!,
            onClick: onEditClick
        )
    }
    
    private var DeleteButton: some View {
        BottomActionButton(
            icon: SharedR.images().ic_trash.toUIImage()!,
            onClick: onDeleteClick
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
}

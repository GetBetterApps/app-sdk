//
//  AddNoteItem.swift
//  iosApp
//
//  Created by velkonost on 03.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AddNoteItem: View {
    
    let paddingBottom: Int
    let createGoalClick: () -> Void
    let createNoteClick: () -> Void
    
    init(paddingBottom: Int = 90, createGoalClick: @escaping () -> Void, createNoteClick: @escaping () -> Void) {
        self.paddingBottom = paddingBottom
        self.createGoalClick = createGoalClick
        self.createNoteClick = createNoteClick
    }
    
    
    var body: some View {
        VStack {
            Spacer()
            HStack {
                Spacer()
                ExpandableButtonPanel(
                    paddingBottom: paddingBottom,
                    primaryItem: ExpandableButtonItem(
                        icon: SharedR.images().ic_plus.toUIImage()!,
                        iconExpanded: SharedR.images().ic_arrow_back.toUIImage()!,
                        label: SharedR.strings().cancel.desc().localized()
                    ),
                    secondaryItems: [
                        ExpandableButtonItem(
                            icon: SharedR.images().ic_goal.toUIImage()!,
                            iconExpanded: nil,
                            label: SharedR.strings().create_goal_button.desc().localized()
                        ) {
                            createGoalClick()
                        },
                        ExpandableButtonItem(
                            icon: SharedR.images().ic_note.toUIImage()!,
                            iconExpanded: nil,
                            label: SharedR.strings().create_note_button.desc().localized()
                        ) {
                            createNoteClick()
                        }
                    ]
                )
            }
        }
    }
}

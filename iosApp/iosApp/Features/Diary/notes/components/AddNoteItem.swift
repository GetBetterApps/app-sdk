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
    
    let createGoalClick: () -> Void
    let createNoteClick: () -> Void
    
    init(createGoalClick: @escaping () -> Void, createNoteClick: @escaping () -> Void) {
        self.createGoalClick = createGoalClick
        self.createNoteClick = createNoteClick
    }
    
    
    var body: some View {
        VStack {
            Spacer()
            HStack {
                Spacer()
                ExpandableButtonPanel(
                    primaryItem: ExpandableButtonItem(
                        icon: SharedR.images().ic_plus.toUIImage()!,
                        iconExpanded: SharedR.images().ic_arrow_back.toUIImage()!,
                        label: SharedR.strings().cancel.desc().localized()
                    ),
                    secondaryItems: [
                        ExpandableButtonItem(
                            icon: SharedR.images().ic_grid.toUIImage()!,
                            iconExpanded: nil,
                            label: SharedR.strings().diary_areas_add_existing_title.desc().localized()
                        ) {
                            createGoalClick()
                        },
                        ExpandableButtonItem(
                            icon: SharedR.images().ic_edit.toUIImage()!,
                            iconExpanded: nil,
                            label: SharedR.strings().diary_areas_create_new_title.desc().localized()
                        ) {
                            createNoteClick()
                        }
                    ]
                )
            }
        }
    }
}

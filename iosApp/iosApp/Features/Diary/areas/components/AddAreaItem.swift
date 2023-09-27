//
//  AddAreaItem.swift
//  iosApp
//
//  Created by velkonost on 27.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AddAreaItem: View {
    
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
                        },
                        ExpandableButtonItem(
                            icon: SharedR.images().ic_edit.toUIImage()!,
                            iconExpanded: nil,
                            label: SharedR.strings().diary_areas_create_new_title.desc().localized()
                        ) {
                            
                        }
                    ]
                )
            }
        }
    }
}

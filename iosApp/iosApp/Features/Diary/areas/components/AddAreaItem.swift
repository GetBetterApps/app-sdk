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
                        icon: SharedR.images().ic_menu_profile.toUIImage()!,
                        iconExpanded: SharedR.images().ic_settings.toUIImage()!,
                        label: "Cancel"
                    ),
                    secondaryItems: [
                        ExpandableButtonItem(
                            icon: SharedR.images().ic_menu_profile.toUIImage()!,
                            iconExpanded: nil,
                            label: "Add existing"
                        ) {
                        },
                        ExpandableButtonItem(
                            icon: SharedR.images().ic_menu_profile.toUIImage()!,
                            iconExpanded: nil,
                            label: "Create new"
                        ) {
                            
                        }
                    ]
                )
            }
        }
    }
}

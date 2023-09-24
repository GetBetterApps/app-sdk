//
//  PrimaryTabs.swift
//  iosApp
//
//  Created by velkonost on 24.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct PrimaryTabs: View {
    
    @Binding private var selectedPage: Int
    let tabs: Array<String>
    
    init(selectedPage: Binding<Int>, tabs: Array<String>) {
        self._selectedPage = selectedPage
        self.tabs = tabs
            
        UISegmentedControl.appearance().selectedSegmentTintColor = SharedR.colors.shared.main_background.getUIColor()
        UISegmentedControl.appearance().setTitleTextAttributes([.foregroundColor: SharedR.colors.shared.icon_active.getUIColor()], for: .selected)
        UISegmentedControl.appearance().setTitleTextAttributes([.foregroundColor: SharedR.colors.shared.icon_inactive.getUIColor()], for: .normal)
        UISegmentedControl.appearance().backgroundColor = SharedR.colors.shared.background_item.getUIColor()
    }
    
    var body: some View {
        Picker("", selection: $selectedPage) {
            ForEach(Array(tabs.enumerated()), id: \.offset) { index, tabTitle in
                Text(tabTitle).tag(index)
            }
        }
        .pickerStyle(.segmented)
        .padding(.init(top: .zero, leading: 20, bottom: .zero, trailing: 20))
    }
}

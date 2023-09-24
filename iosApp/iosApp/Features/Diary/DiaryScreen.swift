//
//  DiaryScreen.swift
//  iosApp
//
//  Created by velkonost on 21.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct DiaryScreen: View {
    
    
    @State private var selectedPage: Int = 0
    
    var body: some View {
        VStack {
           PrimaryTabs(selectedPage: $selectedPage, tabs: ["diary", "oblasti", "tasks"])
            
            Text("\(selectedPage)")
            Spacer()   
        }
    }
}

//
//  NotesView.swift
//  iosApp
//
//  Created by velkonost on 27.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct NotesView: View {
    
    let isLoading: Bool
    let createGoalClick: () -> Void
    let createNoteClick: () -> Void
    
    init(isLoading: Bool, createGoalClick: @escaping () -> Void, createNoteClick: @escaping () -> Void) {
        self.isLoading = isLoading
        self.createGoalClick = createGoalClick
        self.createNoteClick = createNoteClick
    }
    
    var body: some View {
        ZStack {
            if isLoading {
                Loader()
            } else {
                ScrollView(showsIndicators: false) {
                    Text("notes")
                }
                
                VStack(alignment: .trailing) {
                    Spacer()
                    AddNoteItem {
                        createGoalClick()
                    } createNoteClick: {
                        createNoteClick()
                    }
                }
                
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
        
    }
}

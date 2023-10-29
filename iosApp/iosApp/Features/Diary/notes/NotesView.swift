//
//  NotesView.swift
//  iosApp
//
//  Created by velkonost on 27.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct NotesView: View {
    
    let isLoading: Bool
    let createGoalClick: () -> Void
    let createNoteClick: () -> Void
    
    let items: [Area]
    
    init(isLoading: Bool, items: [Area], createGoalClick: @escaping () -> Void, createNoteClick: @escaping () -> Void) {
        self.isLoading = isLoading
        self.items = items
        self.createGoalClick = createGoalClick
        self.createNoteClick = createNoteClick
    }
    
    var body: some View {
        ZStack {
            if isLoading {
                Loader()
            } else {
                ScrollView(showsIndicators: false) {
                    LazyVStack(spacing: 0) {
                        ForEach(items, id: \.self) { item in
                            NoteItem(item: item)
                        }
                    }
                    .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                    
                }
                .fadingEdge()
                
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

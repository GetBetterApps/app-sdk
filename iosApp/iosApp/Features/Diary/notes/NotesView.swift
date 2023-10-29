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
    
    let items: [Note]
    let itemClick: (Note) -> Void
    
    init(isLoading: Bool, items: [Note], createGoalClick: @escaping () -> Void, createNoteClick: @escaping () -> Void, itemClick: @escaping (Note) -> Void) {
        self.isLoading = isLoading
        self.items = items
        self.createGoalClick = createGoalClick
        self.createNoteClick = createNoteClick
        self.itemClick = itemClick
    }
    
    var body: some View {
        ZStack {
            if isLoading {
                Loader()
            } else {
                ScrollView(showsIndicators: false) {
                    LazyVStack(spacing: 0) {
                        ForEach(items, id: \.self) { item in
                            NoteItem(
                                item: item,
                                onClick: itemClick
                            )
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

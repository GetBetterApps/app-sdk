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
    
    @Binding var state: NotesViewState
    
    private let showAd: Bool
    private let adPosition: Int
    
    private var isLoading: Bool
    private let createGoalClick: () -> Void
    private let createNoteClick: () -> Void
    
    private let items: [Note]
    private let itemClick: (Note) -> Void
    private let itemLikeClick: (Note) -> Void
    private let onBottomReach: () -> Void
    
    init(
        showAd: Bool,
        adPosition: Int, state: Binding<NotesViewState>,
        isLoading: Bool, items: [Note],
        createGoalClick: @escaping () -> Void, createNoteClick: @escaping () -> Void,
        itemClick: @escaping (Note) -> Void, 
        itemLikeClick: @escaping (Note) -> Void,
        onBottomReach: @escaping () -> Void
    ) {
        self.showAd = showAd
        self.adPosition = adPosition
        self._state = state
        self.isLoading = isLoading
        self.items = items
        
        self.createGoalClick = createGoalClick
        self.createNoteClick = createNoteClick
        
        self.itemClick = itemClick
        self.itemLikeClick = itemLikeClick
        
        self.onBottomReach = onBottomReach
    }
    
    var body: some View {
        ZStack {
            if isLoading && items.isEmpty {
                Loader()
            } else {
                if items.isEmpty {
                    PlaceholderView(text: SharedR.strings().placeholder_diary_notes.desc().localized())
                } else {
                    ScrollView(showsIndicators: false) {
                        LazyVStack(spacing: 0) {
                            ForEach(0..<items.count, id: \.self) { index in
                                NoteItem(
                                    item: items[index],
                                    onClick: itemClick,
                                    onLikeClick: itemLikeClick
                                )
                                .onAppear {
                                    checkPaginationThreshold(currentItemId: items[index].id)
                                }
                                
                                if index != 0 && index % adPosition == 0 {
                                    AdView(showAd: showAd)
                                        .padding(.vertical, 2)
                                }
                            }
                            
                            if items.count < adPosition {
                                AdView(showAd: showAd)
                                    .padding(.vertical, 2)
                            }
                        }
                        .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                        
                        Loader().opacity(state.isLoading ? 1 : 0)
                    }
                    .fadingEdge()
                }
                
                VStack(alignment: .trailing) {
                    Spacer()
                    AddNoteItem {
                        createGoalClick()
                    } createNoteClick: {
                        createNoteClick()
                    }
                }
                .frame(width: UIScreen.screenWidth)
                
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
        
    }
}

extension NotesView {
    func checkPaginationThreshold(currentItemId: Int32) {
        let data = items
        let thresholdIndex = data.index(data.endIndex, offsetBy: -5)
        
        if data.firstIndex(where: { $0.id == currentItemId })! >= thresholdIndex && !isLoading {
            onBottomReach()
        }
    }
}

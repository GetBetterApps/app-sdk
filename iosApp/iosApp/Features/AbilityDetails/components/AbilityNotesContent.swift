//
//  AbilityNotesContent.swift
//  iosApp
//
//  Created by velkonost on 14.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AbilityNotesContent: View {
    
    private let isLoading: Bool
    private let items: [Note]
    private let loadMorePrefetch: Int
    private let itemClick: (Note) -> Void
    private let itemLikeClick: (Note) -> Void
    private let onBottomReach: () -> Void
    
    init(isLoading: Bool, items: [Note], loadMorePrefetch: Int, itemClick: @escaping (Note) -> Void, itemLikeClick: @escaping (Note) -> Void, onBottomReach: @escaping () -> Void) {
        self.isLoading = isLoading
        self.items = items
        self.loadMorePrefetch = loadMorePrefetch
        self.itemClick = itemClick
        self.itemLikeClick = itemLikeClick
        self.onBottomReach = onBottomReach
    }
    
    var body: some View {
        ZStack {
            Color.mainBackground.edgesIgnoringSafeArea(.all)
            
            if items.isEmpty {
                PlaceholderView(text: SharedR.strings().placeholder_ability_details_notes.desc().localized())
            } else {
                ScrollView(.vertical, showsIndicators: false) {
                    ScrollViewReader { value in
                        LazyVStack(spacing: 0) {
                            ForEach(items, id: \.self.id) { item in
                                NoteItem(
                                    item: item,
                                    onClick: itemClick,
                                    onLikeClick: itemLikeClick
                                )
                                .padding(.horizontal, 16)
                                .onAppear {
                                    checkPaginationThreshold(currentItemId: item.id)
                                }
                            }
                            
                            if isLoading {
                                Loader().frame(alignment: .center)
                            }
                        }
                    }
                }.fadingEdge()
            }
        }
        .padding(.top, 80)
    }
}

extension AbilityNotesContent {
    func checkPaginationThreshold(currentItemId: Int32) {
        let data = items
        let thresholdIndex = data.index(data.endIndex, offsetBy: -loadMorePrefetch)
        
        if data.firstIndex(where: { $0.id == currentItemId })! >= thresholdIndex && !isLoading {
            onBottomReach()
        }
    }
}

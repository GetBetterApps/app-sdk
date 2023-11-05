//
//  SocialFeedView.swift
//  iosApp
//
//  Created by velkonost on 05.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct SocialFeedView: View {
    
    @Binding var isLoading: Bool
    let items: [Note]
    let loadMorePrefetch: Int
    let itemClick: (Note) -> Void
    let onBottomReach: () -> Void
    let onRefresh: () -> Void
    
    init(isLoading: Binding<Bool>,
         loadMorePrefetch: Int,
         items: [Note], itemClick: @escaping (Note) -> Void, onBottomReach: @escaping () -> Void, onRefresh: @escaping () -> Void) {
        self._isLoading = isLoading
        self.loadMorePrefetch = loadMorePrefetch
        self.items = items
        self.itemClick = itemClick
        self.onBottomReach = onBottomReach
        self.onRefresh = onRefresh
    }
    
    var body: some View {
        ZStack {
            if isLoading && items.isEmpty {
                Loader()
            } else {
                ScrollView(showsIndicators: false) {
                    LazyVStack(spacing: 0) {
                        ForEach(items, id: \.self.id) { item in
                            FeedNoteItem(
                                item: item,
                                onClick: itemClick
                            )
                            .onAppear {
                                checkPaginationThreshold(currentItemId: item.id)
                            }
                        }
                    }
                    .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                    
                    Loader().opacity(isLoading ? 1 : 0)
                }
                .fadingEdge()
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
        .refreshable {
            onRefresh()
        }
    }
}

extension SocialFeedView {
    func checkPaginationThreshold(currentItemId: Int32) {
        let data = items
        let thresholdIndex = data.index(data.endIndex, offsetBy: -loadMorePrefetch)
        
        if data.firstIndex(where: { $0.id == currentItemId })! >= thresholdIndex && !isLoading {
            onBottomReach()
        }
    }
}

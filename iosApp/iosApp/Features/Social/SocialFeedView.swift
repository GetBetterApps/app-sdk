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
    private let emptyText: String
    private let items: [Note]
    private let loadMorePrefetch: Int
    
    private let showAd: Bool
    private let adPosition: Int
    
    private let itemClick: (Note) -> Void
    private let itemLikeClick: (Note) -> Void
    
    private let onBottomReach: () -> Void
    private let onRefresh: () -> Void
    
    init(isLoading: Binding<Bool>,
         emptyText: String, loadMorePrefetch: Int,
         items: [Note],
         showAd: Bool, adPosition: Int,
         itemClick: @escaping (Note) -> Void, itemLikeClick: @escaping (Note) -> Void, onBottomReach: @escaping () -> Void, onRefresh: @escaping () -> Void) {
        self._isLoading = isLoading
        self.emptyText = emptyText
        self.loadMorePrefetch = loadMorePrefetch
        
        self.items = items
        self.showAd = showAd
        self.adPosition = adPosition
        
        self.itemClick = itemClick
        self.itemLikeClick = itemLikeClick
        
        self.onBottomReach = onBottomReach
        self.onRefresh = onRefresh
    }
    
    var body: some View {
        ZStack {
            if isLoading && items.isEmpty {
                Loader()
            } else {
                if items.isEmpty {
                    PlaceholderView(text: emptyText)
                } else {
                    ScrollView(showsIndicators: false) {
                        LazyVStack(spacing: 0) {
                            ForEach(0..<items.count, id: \.self) { index in
                                FeedNoteItem(
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
                        }
                        .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                        
                        Loader().opacity(isLoading ? 1 : 0)
                    }
                    .fadingEdge()
                }
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

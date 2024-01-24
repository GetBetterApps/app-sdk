//
//  SocialScreen.swift
//  iosApp
//
//  Created by velkonost on 21.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct SocialScreen: View {
    
    @StateViewModel var viewModel: SocialViewModel
    @State private var selectedPage: Int = 0

    var body: some View {
        @State var state = viewModel.viewStateValue as! SocialViewState
        @State var generalFeedState = state.generalFeed
        @State var areasFeedState = state.areasFeed
        
        VStack {
            HStack {
                PrimaryTabs(
                    selectedPage: $selectedPage,
                    tabs: state.tabs.map { tab in tab.title.localized() }
                )
                HintButton {
                    viewModel.dispatch(action: SocialActionHintClick())
                }
                .padding(.leading, -10)
                .padding(.trailing, 16)
            }
            
            switch(selectedPage) {
            case 0: SocialFeedView(
                isLoading: Binding(get: { generalFeedState.isLoading }, set: {_ in }),
                emptyText: SharedR.strings().placeholder_social_all.desc().localized(),
                loadMorePrefetch: Int(generalFeedState.loadMorePrefetch),
                items: generalFeedState.items,
                adPosition: Int(state.adPosition),
                itemClick: { value in
                    viewModel.dispatch(action: SocialActionNoteClick(value: value))
                },
                itemLikeClick: { value in
                    viewModel.dispatch(action: SocialActionNoteLikeClick(value: value))
                },
                onBottomReach: {
                    viewModel.dispatch(action: SocialActionGeneralFeedLoadNextPage())
                },
                onRefresh: {
                    viewModel.dispatch(action: SocialActionRefreshGeneralFeed())
                }
            )
            default: SocialFeedView(
                isLoading: Binding(get: { areasFeedState.isLoading }, set: {_ in }),
                emptyText: SharedR.strings().placeholder_social_areas.desc().localized(),
                loadMorePrefetch: Int(areasFeedState.loadMorePrefetch),
                items: areasFeedState.items,
                adPosition: Int(state.adPosition),
                itemClick: { value in
                    viewModel.dispatch(action: SocialActionNoteClick(value: value))
                },
                itemLikeClick: { value in
                    viewModel.dispatch(action: SocialActionNoteLikeClick(value: value))
                },
                onBottomReach: {
                    viewModel.dispatch(action: SocialActionAreasFeedLoadNextPage())
                },
                onRefresh: {
                    viewModel.dispatch(action: SocialActionRefreshAreasFeed())
                }
            )
            
            }
        }
        .onAppear {            
            viewModel.onAppear()
            
            if state.generalFeed.items.isEmpty {
                viewModel.dispatch(action: SocialActionGeneralFeedLoadNextPage())
            }
            
            if state.areasFeed.items.isEmpty {
                viewModel.dispatch(action: SocialActionAreasFeedLoadNextPage())
            }
        }
    }
}

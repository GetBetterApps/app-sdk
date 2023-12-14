//
//  AbilityDetailsScreen.swift
//  iosApp
//
//  Created by velkonost on 12.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct AbilityDetailsScreen: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @StateViewModel var viewModel: AbilityDetailsViewModel
    @State private var eventsObserver: Task<(), Error>? = nil
    @State var currentIndex: Int = 0
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! AbilityDetailsViewState
        
        ZStack {
            TabView(selection: $currentIndex) {
                AbilityNotesContent(
                    isLoading: state.userNotesViewState.isLoading,
                    items: state.userNotesViewState.items,
                    loadMorePrefetch: Int(state.userNotesViewState.loadMorePrefetch),
                    itemClick: { value in
                        
                    },
                    itemLikeClick: { value in
                        
                    },
                    onBottomReach: {
                        viewModel.dispatch(action: AbilityDetailsActionUserNotesLoadNextPage())
                    }
                ).tag(0)
                
                AbilityMotivationContent(
                    items: state.affirmations,
                    isActive: currentIndex == 1,
                    itemFavoriteClick: { value in
                        viewModel.dispatch(action: AbilityDetailsActionFavoriteClick(value: value))
                    }
                ).tag(1)
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
            .edgesIgnoringSafeArea(.all)
            
            VStack {
                if currentIndex == 0 {
                    AbilityDetailsHeader(title: state.ability?.name == nil ? "" : state.ability!.name)
                        .padding(.horizontal, 16)
                }
            
                PrimaryTabs(
                    selectedPage: $currentIndex,
                    tabs: state.tabs.map({ tab in tab.title.localized()})
                )
                .opacity(currentIndex == 0 ? 1 : 0.6)
                Spacer()
            }
            .padding(.top, 40)
        }
        .animation(.easeInOut, value: currentIndex)
        .edgesIgnoringSafeArea(.all)
    }
}

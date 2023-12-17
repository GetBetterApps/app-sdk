//
//  AbilitiesScreen.swift
//  iosApp
//
//  Created by velkonost on 09.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct AbilitiesScreen: View {
    
    @StateViewModel var viewModel: AbilitiesViewModel
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! AbilitiesViewState
        
        ZStack {
            if state.isLoading && state.items.count <= 1 {
                Loader().frame(alignment: .center)
            } else {
                ScrollView(showsIndicators: false) {
                    LazyVStack(spacing: 0) {
                        ForEach(state.items, id: \.self.id) { item in
                            AbilityItem(
                                item: item,
                                onClick: { value in
                                    viewModel.dispatch(action: AbilitiesActionAbilityClick(value: value))
                                }
                            )
                            .onAppear {
                                checkPaginationThreshold(
                                    items: state.items,
                                    currentItemId: item.id!,
                                    loadMorePrefetch: Int(state.loadMorePrefetch),
                                    isLoading: state.isLoading,
                                    onBottomReach: {
                                        viewModel.dispatch(action: AbilitiesActionLoadNextPage())
                                    }
                                )
                            }
                        }
                    }.padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                }.fadingEdge()
            }
        }
        .onAppear {
            if state.items.isEmpty {
                viewModel.dispatch(action: AbilitiesActionLoadNextPage())
            } else {
                viewModel.onAppear()
            }
        }
    }
}

extension AbilitiesScreen {
    func checkPaginationThreshold(items: [Ability], currentItemId: KotlinInt, loadMorePrefetch: Int, isLoading: Bool, onBottomReach: () -> Void) {
        let data = (viewModel.viewStateValue as! AbilitiesViewState).items
        let thresholdIndex = data.index(data.endIndex, offsetBy: -5)
        
        if data.firstIndex(where: { $0.id == currentItemId })! >= thresholdIndex && !isLoading {
            onBottomReach()
        }
    }
}

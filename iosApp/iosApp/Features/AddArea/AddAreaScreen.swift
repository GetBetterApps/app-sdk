//
//  AddAreaScreen.swift
//  iosApp
//
//  Created by velkonost on 06.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AddAreaScreen: View {
    
    @StateObject var viewModel = AddAreaViewModelDelegate()
    
    var body: some View {
        @State var isLoading = viewModel.state.isLoading
        ZStack {
            if isLoading {
                Loader()
                    .frame(alignment: .center)
            } else {
                
                ScrollView(showsIndicators: false) {
                    LazyVStack(spacing: 0) {
                        ForEach(viewModel.pagingData, id: \.self) { item in
                            AddAreaItem(item: item)
                                .onAppear {
                                    checkPaginationThreshold(currentItemId: item.id)
                                }
                        }
                    }
                    .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                    
                    Loader()
                        .opacity(viewModel.paginationState == .loading ? 1 : 0)
                }
                .fadingEdge()
              
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
        .onAppear(perform: viewModel.onAppear)
        .onDisappear(perform: viewModel.onDisappear)
    }
}


extension AddAreaScreen {
    func checkPaginationThreshold(currentItemId: String) {
        let data = viewModel.pagingData
        
        let thresholdIndex = data.index(data.endIndex, offsetBy: -5)
        
        if data.firstIndex(where: { $0.id == currentItemId })! >= thresholdIndex && viewModel.paginationState != .loading {
            viewModel.paginationState = .loading
            viewModel.dispatch(action: LoadNextPage())
        }
    }
}

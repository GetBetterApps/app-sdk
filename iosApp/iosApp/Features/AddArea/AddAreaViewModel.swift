//
//  AddAreaViewModel.swift
//  iosApp
//
//  Created by velkonost on 06.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import KMPNativeCoroutinesAsync
import SharedSDK

@MainActor
class AddAreaViewModelDelegate: ObservableObject {
    
    @LazyKoin private var delegate: SharedSDK.AddAreaViewModel
    
    @Published var state = AddAreaViewState(isLoading: false, items: [])
    @Published var pagingData = [Area]()
    @Published var paginationState = PaginationState.loading
    
    private var viewStateStream: Task<(), Error>? = nil
    private var pagingDataStream: Task<(), Error>? = nil
    
    
    func dispatch(action: AddAreaAction) {
        delegate.dispatch(action: action)
    }
    
    func onAppear() {
        resumeViewStateStream()
        resumePagingDataStream()
    }
    
    func onDisappear() {
        viewStateStream?.cancel()
        pagingDataStream?.cancel()
    }
    
    private func resumePagingDataStream() {
        pagingDataStream = Task {
            for try await data in asyncSequence(for: delegate.pagingData) {
                paginationState = .idle
                pagingData = data.uniqued()
            }
        }
    }
    
    private func resumeViewStateStream() {
        viewStateStream = Task {
            for try await data in asyncSequence(for: delegate.viewState) {
                state = data as! AddAreaViewState
            }
        }
    }
    
}

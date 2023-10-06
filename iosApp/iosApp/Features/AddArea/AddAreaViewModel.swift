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
    
    @Published var state = AddAreaViewState(isLoading: false, items: [], loadMorePrefetch: 0)
    
    private var stateStream: Task<(), Error>? = nil
    
    
    func dispatch(action: AddAreaAction) {
        delegate.dispatch(action: action)
    }
    
    func onAppear() {
        resumeViewStateStream()
    }
    
    func onDisappear() {
        stateStream?.cancel()
    }
    
    
    private func resumeViewStateStream() {
        stateStream = Task {
            for try await data in asyncSequence(for: delegate.viewState) {
                state = data as! AddAreaViewState
            }
        }
    }
    
}

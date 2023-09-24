//
//  WisdomViewModel.swift
//  iosApp
//
//  Created by velkonost on 24.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import KMPNativeCoroutinesAsync

@MainActor
class WisdomViewModelDelegate: ObservableObject {
    @LazyKoin private var delegate: SharedSDK.WisdomViewModel
    
    @Published var state = WisdomViewState(isLoading: false, items: [])
    private var stateStream: Task<(), Error>? = nil
    
    func dispatch(action: WisdomAction) {
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
                state = data as! WisdomViewState
            }
        }
    }
}

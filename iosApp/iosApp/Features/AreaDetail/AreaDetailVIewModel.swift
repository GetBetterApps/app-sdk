//
//  AreaDetailVIewModel.swift
//  iosApp
//
//  Created by velkonost on 07.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import KMPNativeCoroutinesAsync

@MainActor
class AreaDetailVIewModelDelegate: ObservableObject {
    
    @LazyKoin var delegate: SharedSDK.AreaDetailViewModel
    
    @Published var state = AreaDetailViewState(
        isLoading: false,
        isEditing: false,
        isAllowJoin: false,
        isAllowDelete: false,
        isAllowLeave: false,
        isAllowEdit: false,
        initialItem: nil,
        modifiedItem: nil
    )
    
    private var stateStream: Task<(), Error>? = nil
    
    func dispatch(action: AreaDetailAction) {
        delegate.dispatch(action: action)
    }
    
    func onAppear(areaId: Int32) {
        dispatch(action: AreaDetailActionLoad(areaId: areaId))
        resumeViewStateStream()
    }
    
    func onDisappear() {
        stateStream?.cancel()
    }
    
    private func resumeViewStateStream() {
        stateStream = Task {
            for try await data in asyncSequence(for: delegate.viewState) {
                state = data as! AreaDetailViewState
            }
        }
    }
}

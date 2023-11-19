//
//  ProfileDetailViewModel.swift
//  iosApp
//
//  Created by velkonost on 19.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import KMPNativeCoroutinesAsync
import SharedSDK

@MainActor
class ProfileDetailVIewModelDelegate: ObservableObject {
    
    @LazyKoin var delegate: SharedSDK.ProfileDetailViewModel
    
    @Published var state = ProfileDetailViewState(
        profileData: ProfileUI(isLoading: false, userId: "", userName: "", avatarBytes: nil, experienceData: nil),
        notesData: NotesUI(isLoading: true, loadMorePrefetch: 0, items: []),
        followData: FollowUI(isLoading: true, state: FollowState.followed)
    )
    
    private var stateStream: Task<(), Error>? = nil
    
    func dispatch(action: ProfileDetailAction) {
        delegate.dispatch(action: action)
    }
    
    func onAppear(userId: String) {
        dispatch(action: ProfileDetailActionLoad(userId: userId))
        dispatch(action: ProfileDetailActionNotesLoadNextPage())
        resumeViewStateStream()
    }
    
    func onDisappear() {
        stateStream?.cancel()
    }
    
    private func resumeViewStateStream() {
        stateStream = Task {
            for try await data in asyncSequence(for: delegate.viewState) {
                state = data as! ProfileDetailViewState
            }
        }
    }
}

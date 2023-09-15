//
//  HomeViewModelDelegate.swift
//  iosApp
//
//  Created by velkonost on 06.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import KMPNativeCoroutinesAsync
import SharedSDK

@MainActor
class HomeViewModelDelegate : ObservableObject {
    
    @LazyKoin private var delegate: SharedSDK.HomeViewModel
    
    func dispatch(action: HomeAction) {
        delegate.dispatch(action: action)
    }
    
    func onAppear() {
        
    }
    
    func onDisappear() {
        
    }
}

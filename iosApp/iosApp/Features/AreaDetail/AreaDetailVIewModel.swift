//
//  AreaDetailVIewModel.swift
//  iosApp
//
//  Created by velkonost on 07.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK

@MainActor
class AreaDetailVIewModelDelegate: ObservableObject {
    
    @LazyKoin private var delegate: SharedSDK.AreaDetailViewModel
    
    func dispatch(action: AreaDetailAction) {
        delegate.dispatch(action: action)
    }
    
    func onAppear() {
        
    }
    
    func onDisappear() {
        
    }
}

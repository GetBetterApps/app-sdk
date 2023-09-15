//
//  LifecycleAwareObservableObject.swift
//  iosApp
//
//  Created by velkonost on 06.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

@MainActor
protocol LifecycleAwareObservableObject : ObservableObject {
    func onAppear()
    func onDisappear()
}

//
//  Koin.swift
//  iosApp
//
//  Created by velkonost on 06.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK

extension KoinApplication {
    static let shared = PlatformSDK().doInit(modules: nil, appDeclaration: {_ in })
    
    @discardableResult
    static func start() -> KoinApplication { shared }
}

extension KoinApplication {
    private static let keyPaths: [PartialKeyPath<Koin>] = [
        \.AuthViewModel,
         
         \.SplashViewModel,
         \.SocialViewModel,
         \.DiaryViewModel,
         \.AddAreaViewModel,
         \.AreaDetailViewModel,
         \.NoteDetailViewModel,
         \.NoteDetailViewModel,
         \.CalendarsViewModel,
         \.WisdomViewModel,
         \.ProfileViewModel,
         \.ProfileDetailViewModel,
         \.FeedbackViewModel,
         \.SettingsViewModel,
         \.TaskDetailViewModel,
         \.AbilitiesViewModel,
         \.AbilityDetailsViewModel,
         \.OnboardingViewModel
    ]
    
    static func inject<T>() -> T { shared.inject() }
    
    func inject<T>() -> T {
        for partialKeyPath in Self.keyPaths {
            guard let keyPath = partialKeyPath as? KeyPath<Koin, T> else { continue }
            return koin[keyPath: keyPath]
        }
        
        fatalError("\(T.self) is not registered with KoinApplication")
    }
}

@propertyWrapper
struct LazyKoin<T> {
    lazy var wrappedValue: T = { KoinApplication.shared.inject() } ()
    
    init() {}
}



//
//  NavRoute.swift
//  iosApp
//
//  Created by velkonost on 06.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import KMPNativeCoroutinesAsync
import SharedSDK
import SwiftUI

protocol NavRoute {
    associatedtype T: RouteNavigator
    associatedtype V: View
    
    var route: String { get }
    
    var menuIcon: UIImage? { get }
    
    var content: V { get }
    
    var viewModel: T { get }
    
    func getArguments() -> Array<String>
}

@MainActor
extension NavRoute {
    
    func view(pilot: UIPilot<String>, route: String) -> some View {
        var navEventStream: Task<(), Error>? = nil
        
        return content
            .onAppear {
                if let viewModel = self.viewModel as? BaseViewModel<AnyObject, AnyObject, AnyObject, AnyObject> {
                    
                    if navEventStream == nil {
                        if !getArguments().isEmpty {
                            var args: [String: String] = [:]
                            
                            getArguments().forEach { argName in
                                if let arg = route.valueOf(param: argName) {
                                    args[argName] = arg
                                }
                            }
                            
                            viewModel.put(args: args)
                        }
                        
                        navEventStream = Task {
                            let eventStream = asyncSequence(for: viewModel.navigationEvent)
                            for try await event in eventStream {
                                onNavEvent(pilot: pilot, event: event)
                            }
                        }
                    }
                    
                }
            }
            .onDisappear {
                navEventStream?.cancel()
                navEventStream = nil
            }
    }
    
    func onNavEvent(pilot: UIPilot<String>, event: NavigationEvent) {
        switch event {
            
            case let navEvent as NavigationEvent.NavigateToRoute : do {
                
                if route == navEvent.route {
                    break
                }
                
                var currentRoute: String = navEvent.route
                let args: KotlinMutableDictionary<NSString, NSString>? = navEvent.args
                
                if args != nil {
                    args!.forEach { entry in
                        let key: String = entry.key as? String ?? ""
                        let value: String = entry.value as? String ?? ""
                        
                        currentRoute.replace("{\(key)}", with: value.isEmpty ? "null" : value)
                    }
                }
                
                pilot.push(currentRoute)
            }
            
            case let navEvent as NavigationEvent.NavigateAndPopUpToRoute : do {
                
                if route == navEvent.route {
                    break
                }
                
                var currentRoute: String = navEvent.route
                let args: KotlinMutableDictionary<NSString, NSString>? = navEvent.args
                
                if args != nil {
                    args!.forEach { entry in
                        let key: String = entry.key as? String ?? ""
                        let value: String = entry.value as? String ?? ""
                        
                        currentRoute.replace("{\(key)}", with: value.isEmpty ? "null" : value)
                    }
                }
                
                pilot.popTo(navEvent.popUpTo, inclusive: true)
                pilot.push(currentRoute)
            }
            
            case let navEvent as NavigationEvent.PopToRoute : do {
                
                if route == navEvent.staticRoute {
                    break
                }
                
                var currentRoute: String = navEvent.staticRoute
                let args: KotlinMutableDictionary<NSString, NSString>? = navEvent.args
                
                if args != nil {
                    args!.forEach { entry in
                        let key: String = entry.key as? String ?? ""
                        let value: String = entry.value as? String ?? ""
                        
                        currentRoute.replace("{\(key)}", with: value.isEmpty ? "null" : value)
                    }
                }
            
                pilot.popTo(currentRoute, inclusive: false)
                
            }
            
            case _ as NavigationEvent.NavigateUp : do {
                pilot.pop()
            }
            
            default : break
        }
    }
}


//
//  BottomBar.swift
//  iosApp
//
//  Created by velkonost on 17.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI


struct BottomBar: View {

    let screens: [any NavRoute]
    let pilot: UIPilot<String>
    let currentRoute: String
    
    var body: some View {
        
        HStack(alignment: .top, spacing: 0) {
            BottomBarItem(pilot: pilot, screen: SocialRoute(), currentRoute: currentRoute)
            BottomBarItem(pilot: pilot, screen: DiaryRoute(), currentRoute: currentRoute)
            BottomBarItem(pilot: pilot, screen: CalendarsRoute(), currentRoute: currentRoute)
            BottomBarItem(pilot: pilot, screen: WisdomRoute(), currentRoute: currentRoute)
            BottomBarItem(pilot: pilot, screen: ProfileRoute(), currentRoute: currentRoute)
        }
        .frame(maxWidth: .infinity)
        .frame(height: 95, alignment: .top)
        .background(
            RoundedRectangle(cornerRadius: 40)
                .fill(Color.backgroundItem)
                .shadow(radius: 8, y: -5)
        )
     
    }
}


struct BottomBarItem: View {
    let pilot: UIPilot<String>
    let screen: any NavRoute
    let currentRoute: String
    
    
    var body: some View {
        let isSelected = currentRoute == screen.route
        
        Image(uiImage: screen.menuIcon!)
            .resizable()
            .renderingMode(.template)
            .frame(width: 48, height: 48, alignment: .top)
            .foregroundColor(isSelected ? .iconActive : .iconInactive)
            .padding()
            .onTapGesture {
                if screen.route != currentRoute {
                    let impactMed = UIImpactFeedbackGenerator(style: .medium)
                    impactMed.impactOccurred()
                    
//                    if !pilot.popTo(screen.route, inclusive: false, animated: false) {
                        pilot.setRoot(screen.route)
//                    }
                }
//                pilot.push(currentRoute)
                
//                if pilot.routes.contains(screen.route) {
//                    pilot.popTo(screen.route, inclusive: true)
//                } else {
//                    pilot.setRoot(screen.route)
//                }
            }
    }
}

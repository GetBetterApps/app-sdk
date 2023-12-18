//
//  OnboardingSecondStep.swift
//  iosApp
//
//  Created by velkonost on 18.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct OnboardingSecondStep: View {
    
    @State var imageIndex: Int = 0
    
    
    var body: some View {
        @State var rotation: Angle = Angle(degrees: Double(imageIndex) * Double(180))
        /*@START_MENU_TOKEN@*//*@PLACEHOLDER=Hello, world!@*/Text("Hello, world!")/*@END_MENU_TOKEN@*/
    }
}

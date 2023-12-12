//
//  AbilityDetailsScreen.swift
//  iosApp
//
//  Created by velkonost on 12.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct AbilityDetailsScreen: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @StateViewModel var viewModel: AbilityDetailsViewModel
    @State private var eventsObserver: Task<(), Error>? = nil
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! AbilityDetailsViewState
        
        ZStack {
            
        }
    }
}

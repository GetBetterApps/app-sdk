//
//  CalendarsScreen.swift
//  iosApp
//
//  Created by velkonost on 21.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct CalendarsScreen: View {
    
    @StateViewModel var viewModel: CalendarsViewModel
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! CalendarsViewState
        
        VStack {
            if state.datesState.selectedDate != nil {
                VStack {
                    Text(state.datesState.selectedDate!.monthDay.localized().capitalized)
                        
                }
            }
            
        }
    }
}

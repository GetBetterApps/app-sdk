//
//  AbilitiesScreen.swift
//  iosApp
//
//  Created by velkonost on 09.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct AbilitiesScreen: View {
    
    @StateViewModel var viewModel: AbilitiesViewModel
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! AbilitiesViewState
        
        ZStack {
            if state.isLoading && state.items.isEmpty {
                Loader().frame(alignment: .center)
            } else {
                ScrollView(showsIndicators: false) {
                    LazyVStack(spacing: 0) {
                        ForEach(state.items, id: \.self) { item in
                            AbilityData(
                                item: item,
                                onClick: { value in
                                    
                                }
                            )
                        }
                    }
                }
            }
            
        }
    }
}

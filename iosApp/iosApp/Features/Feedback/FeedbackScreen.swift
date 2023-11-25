//
//  FeedbackScreen.swift
//  iosApp
//
//  Created by velkonost on 25.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct FeedbackScreen: View {
    
    @StateViewModel var viewModel: FeedbackViewModel
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! FeedbackViewState
        
        ZStack {
            if state.isLoading && state.items.isEmpty {
                Loader().frame(alignment: .center)
            } else {
                VStack {
                    FeedbackListHeader()
                    
                    ScrollView(showsIndicators: false) {
                        LazyVStack(spacing: 0) {
                            ForEach(state.items, id: \.self) { item in
                                FeedbackItem(
                                    item: item,
                                    onClick: {
                                        
                                    }
                                )
                            }
                        }
                        .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                    }
                }
            }
        }
    }
}

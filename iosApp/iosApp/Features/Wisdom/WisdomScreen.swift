//
//  WisdomScreen.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI

struct WisdomScreen: View {
    
    @StateObject var viewModel = WisdomViewModelDelegate()
    
    var body: some View {
        @State var state = viewModel.state
        
        ScrollView(showsIndicators: false) {
            LazyVStack(spacing: 0) {
                ForEach(state.items, id: \.self) { item in
                    WisdomItem(
                        title: item.title.localized(),
                        description: item.description_.localized(),
                        backgroundImage: item.backgroundImage.toUIImage()!) {
                            viewModel.dispatch(action: WisdomItemClick(item: item))
                        }
                        .id(item.title.localized())
                }
            }
            .padding(16)
            .padding(.bottom, 100)
            
        }
        .onAppear(perform: viewModel.onAppear)
        .onDisappear(perform: viewModel.onDisappear)
    }
}

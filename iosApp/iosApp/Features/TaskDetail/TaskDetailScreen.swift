//
//  TaskDetailScreen.swift
//  iosApp
//
//  Created by velkonost on 03.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct TaskDetailScreen : View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @StateViewModel var viewModel: TaskDetailViewModel
    
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! TaskDetailViewState
        
        ZStack {
            if state.isLoading || state.task == nil {
                VStack {
                    Loader()
                }
            } else {
                ScrollView(.vertical, showsIndicators: false) {
                    ScrollViewReader { value in
                        LazyVStack {
                            TaskDetailHeader(
                                isFavorite: state.task!.isFavorite,
                                isFavoriteLoading: state.task!.isFavoriteLoading,
                                onFavoriteClick: {
                                    
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

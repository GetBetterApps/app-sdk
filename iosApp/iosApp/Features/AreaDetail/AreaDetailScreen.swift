//
//  AreaDetailScreen.swift
//  iosApp
//
//  Created by velkonost on 07.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct AreaDetailScreen: View {
    
    @StateObject var viewModel = AreaDetailVIewModelDelegate()
    @Binding var areaId: String?
    
    @State private var isEmojiPickerVisible = false
    
    init(areaId: Binding<String?>) {
        self._areaId = areaId
    }
    
    var body: some View {
        @State var state = viewModel.state
        
        ZStack {
            Color.mainBackground
            
            if state.isLoading {
                VStack {
                    Loader()
                }
            } else {
                if state.item != nil {
                    AreaDetailContent(
                        areaData: state.item!,
                        isEditing: state.isEditing,
                        isEmojiPickerVisible: $isEmojiPickerVisible,
                        onEmojiClick: { value in
                            viewModel.dispatch(action: AreaDetailActionEmojiChanged(value: value))
                        },
                        onNameChanged: { value in
                            viewModel.dispatch(action: AreaDetailActionNameChanged(value: value))
                        },
                        onDescriptionChanged: { value in
                            viewModel.dispatch(action: AreaDetailActionDescriptionChanged(value: value))
                        }
                    )
                }
            }
        }
        .onAppear {
            viewModel.onAppear(areaId: areaId!)
        }
        .onDisappear(perform: viewModel.onDisappear)
        .edgesIgnoringSafeArea(.all)
        .onTapGesture {
            self.endTextEditing()
        }
        
    }
}

//
//  AddAreaScreen.swift
//  iosApp
//
//  Created by velkonost on 06.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI

struct AddAreaScreen: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    @StateObject var viewModel = AddAreaViewModelDelegate()
    
    var body: some View {
        @State var state = viewModel.state
        
        ZStack {
            if state.isLoading && state.items.isEmpty {
                Loader()
                    .frame(alignment: .center)
            } else {
                VStack {
                    HStack {
                        Image(uiImage: SharedR.images().ic_arrow_back.toUIImage()!)
                            .resizable()
                            .renderingMode(.template)
                            .foregroundColor(.iconActive)
                            .frame(width: 32, height: 32)
                            .scaledToFill()
                            .padding(4)
                            .background(
                                RoundedRectangle(cornerRadius: 8)
                                    .fill(Color.backgroundIcon)
                            )
                            .padding(.leading, 20)
                            .onTapGesture {
                                let impactMed = UIImpactFeedbackGenerator(style: .medium)
                                impactMed.impactOccurred()
                                presentationMode.wrappedValue.dismiss()
                            }
                        
                        Text("Public Areas")
                            .style(.headlineSmall)
                            .foregroundColor(.textTitle)
                            .padding(.leading, 12)
                        Spacer()
                    }
                    
                    ScrollView(showsIndicators: false) {
                       
                        
                        
                        LazyVStack(spacing: 0) {
                            ForEach(state.items, id: \.self) { item in
                                AddAreaItem(item: item) { areaId in
                                    viewModel.dispatch(action: AddAreaClick_(areaId: areaId))
                                }
                                .onAppear {
                                    checkPaginationThreshold(currentItemId: item.id, isLoading: state.isLoading)
                                }
                            }
                        }
                        .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                        
                        Loader()
                            .opacity(state.isLoading ? 1 : 0)
                    }
                    .fadingEdge()
                }
                
                
                
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
        .onAppear(perform: viewModel.onAppear)
        .onDisappear(perform: viewModel.onDisappear)
    }
}


extension AddAreaScreen {
    func checkPaginationThreshold(currentItemId: String, isLoading: Bool) {
        
        let data = viewModel.state.items
        
        let thresholdIndex = data.index(data.endIndex, offsetBy: -5)
        
        if data.firstIndex(where: { $0.id == currentItemId })! >= thresholdIndex && !isLoading {
            viewModel.dispatch(action: LoadNextPage())
            
        }
    }
}

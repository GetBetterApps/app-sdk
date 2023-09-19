//
//  DetailScreen.swift
//  iosApp
//
//  Created by velkonost on 06.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import KMMViewModelSwiftUI
import SwiftUI

struct DetailScreen: View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    @StateViewModel var viewModel: DetailViewModel
    
    var body: some View {
        ZStack(alignment: .topLeading) {
            VStack() {
                @State var state = viewModel.viewStateValue as! DetailViewState
                
                Text("count = \(state.count)")
                
                Button {
                    viewModel.dispatch(action: Increment())
                } label: {
                    Text("increment")
                        .padding(10)
                }
                .background(Color.purple)
                .padding(10)
                
                Button {
                    viewModel.dispatch(action: Decrement())
                } label: {
                    Text("decrement")
                        .foregroundColor(.white)
                        .padding(10)
                }
                .background(Color.blue)
                
                Button {
                    presentationMode.wrappedValue.dismiss()
                } label: {
                    Text("back")
                        .padding(10)
                }
                .background(Color.red)
                
                            
            }
        }
    }
    
}

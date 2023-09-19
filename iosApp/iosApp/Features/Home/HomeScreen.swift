//
//  HomeScreen.swift
//  iosApp
//
//  Created by velkonost on 06.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI
import KMMViewModelSwiftUI

struct HomeScreen: View {
    
    @ObservedViewModel var viewModel: HomeViewModel
    
    private let safeAreaInsetTop = UIApplication.shared.windows.first?.safeAreaInsets.top ?? 0
    
    var body: some View {
        ZStack(alignment: .topLeading) {
            
            Color.red
            
            VStack {
                Text("123")
                    .foregroundColor(Color.red)
                    .font(.largeTitle)
                
                Button(action: {
                    viewModel.dispatch(action: NavigateToDetails())
                }) {
                    Text("click me mthfcr")
                        .foregroundColor(Color.green)
                        .padding(10)
                        .font(.title2)
                }
                .background(Color.blue)
                .cornerRadius(30)
                .padding(.init(top: 20, leading: .zero, bottom: .zero, trailing: .zero))
                
//                Image("\(SharedR.shared.image.ic_test)")
                
//                Image(uiImage: SharedR.images().bg_paw_print_loaded.toUIImage()!)
//                Image(name: SharedR.shared.image.ic_test)
            }
            
        }
    }
}

//struct HomeScreenPreview: PreviewProvider {
//    static var previews: some View {
//        HomeScreen()
//    }
//}

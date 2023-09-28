//
//  AreasView.swift
//  iosApp
//
//  Created by velkonost on 27.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AreasView: View {
    
    let createNewAreaClick: () -> Void
    
    init(createNewAreaClick: @escaping () -> Void) {
        self.createNewAreaClick = createNewAreaClick
    }
    
    
    var body: some View {
        
        ZStack {
            ScrollView(showsIndicators: false) {
                LazyVStack(spacing: 0) {
                    ForEach(0...20, id: \.self) { index in
                        AreaItem {
                            
                        }
                    }
                }
                .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                
            }
            .mask(LinearGradient(gradient: Gradient(stops: [
                .init(color: .clear, location: 0),
                .init(color: .black, location: 0.1),
                .init(color: .black, location: 0.75),
                .init(color: .clear, location: 1)
            ]), startPoint: .top, endPoint: .bottom))
            
            
            VStack(alignment: .trailing) {
                Spacer()
                AddAreaItem {
                    
                } createNewClick: {
                    createNewAreaClick()
                }

            }
        }
    }
}

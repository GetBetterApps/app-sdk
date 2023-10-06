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
    
    let items: [Area]
    let isLoading: Bool
    let createNewAreaClick: () -> Void
    let addExistingAreaClick: () -> Void
    
    init(
        items: [Area], isLoading: Bool,
        createNewAreaClick: @escaping () -> Void,
        addExistingAreaClick: @escaping () -> Void
    ) {
        self.items = items
        self.isLoading = isLoading
        self.createNewAreaClick = createNewAreaClick
        self.addExistingAreaClick = addExistingAreaClick
    }
    
    
    var body: some View {
        ZStack {
            if isLoading {
                Loader()
                    .frame(alignment: .center)
            } else {
                
                ScrollView(showsIndicators: false) {
                    LazyVStack(spacing: 0) {
                        ForEach(items, id: \.self) { item in
                            AreaItem(item: item) {
                                
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
                    AddAreaButton {
                        addExistingAreaClick()
                    } createNewClick: {
                        createNewAreaClick()
                    }
                }
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
    }
    
}

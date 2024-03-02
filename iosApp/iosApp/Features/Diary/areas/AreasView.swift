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
    
    private let items: [Area]
    private let isLoading: Bool
    
    private let showAd: Bool
    private let adPosition: Int
    
    private let areaClick: (Int32) -> Void
    private let areaLikeClick: (Area) -> Void
    private let createNewAreaClick: () -> Void
    private let addExistingAreaClick: () -> Void
    
    init(
        items: [Area], isLoading: Bool,
        showAd: Bool,
        adPosition: Int,
        areaClick: @escaping (Int32) -> Void,
        areaLikeClick: @escaping (Area) -> Void,
        createNewAreaClick: @escaping () -> Void,
        addExistingAreaClick: @escaping () -> Void
    ) {
        self.items = items
        self.isLoading = isLoading
        self.showAd = showAd
        self.adPosition = adPosition
        self.areaClick = areaClick
        self.areaLikeClick = areaLikeClick
        self.createNewAreaClick = createNewAreaClick
        self.addExistingAreaClick = addExistingAreaClick
    }
    
    
    var body: some View {
        ZStack {
            if isLoading {
                Loader()
                    .frame(alignment: .center)
            } else {
                if items.isEmpty {
                    PlaceholderView(text: SharedR.strings().placeholder_diary_areas.desc().localized())
                } else {
                    ScrollView(showsIndicators: false) {
                        LazyVStack(spacing: 0) {
//                            ForEach(items, id: \.self) { item in
                            ForEach(0..<items.count, id: \.self) { index in
                                AreaItem(
                                    item: items[index],
                                    onClick: areaClick,
                                    onLikeClick: areaLikeClick
                                )
                                
                                if index != 0 && index % adPosition == 0 {
                                    AdView(showAd: showAd)
                                        .padding(.vertical, 2)
                                }
                            }
                            
                            if items.count < adPosition {
                                AdView(showAd: showAd)
                                    .padding(.vertical, 2)
                            }
                        }
                        .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                        
                    }
                    .fadingEdge()
                }
                
                VStack(alignment: .trailing) {
                    Spacer()
                    AddAreaButton {
                        addExistingAreaClick()
                    } createNewClick: {
                        createNewAreaClick()
                    }
                }
                .frame(width: UIScreen.screenWidth)
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
    }
    
}

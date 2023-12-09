//
//  TaskItemHeader.swift
//  iosApp
//
//  Created by velkonost on 03.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct TaskItemHeader : View {
    
    let areaName: String
    let taskName: String?
    let areaIcon: UIImage
    let isFavorite: Bool
    let isFavoriteLoading: Bool
    let onFavoriteClick: (() -> Void)?
    
    init(areaName: String, taskName: String? = nil, areaIcon: UIImage, isFavorite: Bool,  isFavoriteLoading: Bool, onFavoriteClick: (() -> Void)? = nil) {
        self.areaName = areaName
        self.taskName = taskName
        self.areaIcon = areaIcon
        self.isFavorite = isFavorite
        self.isFavoriteLoading = isFavoriteLoading
        self.onFavoriteClick = onFavoriteClick
    }
    
    var body: some View {
        HStack {
            Image(uiImage: areaIcon)
                .resizable()
                .scaledToFit()
                .frame(width: 32, height: 32)
            
            VStack {
                HStack {
                    Text(areaName)
                        .style(.titleSmall)
                        .foregroundColor(.textPrimary)
                        .lineLimit(1)
                    Spacer()
                }
                
                if taskName != nil {
                    HStack {
                        Text(taskName!)
                            .style(.titleMedium)
                            .foregroundColor(.textTitle)
                            .lineLimit(1)
                        Spacer()
                    }
                }
            }
            .frame(alignment: .center)
            .padding(.leading, 6)
            
            Spacer()
            
            if onFavoriteClick != nil {
                ZStack(alignment: .center) {
                    if !isFavoriteLoading {
                        VStack {
                            Image(
                                uiImage: isFavorite ? SharedR.images().ic_star.toUIImage()! : SharedR.images().ic_empty_star.toUIImage()!
                            )
                            .resizable()
                            .renderingMode(.template)
                            .foregroundColor(.buttonGradientStart)
                            .scaledToFill()
                            .frame(width: 24, height: 24)
                            
                        }
                        .onTapGesture {
                            onFavoriteClick!()
                        }
                    } else {
                        HStack {
                            Spacer()
                            Loader()
                                .scaleEffect(0.5)
                            Spacer()
                        }
                    }
                    
                }
                .frame(width: 32, height: 32)
                .animation(.easeInOut, value: isFavorite)
                .animation(.easeInOut, value: isFavoriteLoading)
            }
            
        }
    }
}

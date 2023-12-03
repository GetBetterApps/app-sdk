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
    let onFavoriteClick: () -> Void
    
    init(areaName: String, taskName: String? = nil, areaIcon: UIImage, isFavorite: Bool, onFavoriteClick: @escaping () -> Void) {
        self.areaName = areaName
        self.taskName = taskName
        self.areaIcon = areaIcon
        self.isFavorite = isFavorite
        self.onFavoriteClick = onFavoriteClick
    }

    var body: some View {
        HStack {
            Image(uiImage: areaIcon)
                .resizable()
                .scaledToFit()
                .frame(width: 32, height: 32)
            
            VStack {
                Spacer()
                
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
                
                Spacer()
            }
            .padding(.leading, 6)
            
            Spacer()
            
//            if showLikes {
//                ZStack(alignment: .center) {
//                    if (!likesData.isLikesLoading) {
//                        VStack {
//                            Image(
//                                uiImage: likesData.userLike == LikeType.positive ? SharedR.images().ic_heart.toUIImage()! : SharedR.images().ic_heart_empty.toUIImage()!
//                            )
//                            .resizable()
//                            .renderingMode(.template)
//                            .foregroundColor(.buttonGradientStart)
//                            .scaledToFill()
//                            .frame(width: 24, height: 24)
//                            
//                            Text(String(likesData.totalLikes))
//                                .style(.bodySmall)
//                                .foregroundColor(.textPrimary)
//                        }
//                        .onTapGesture {
//                            onLikeClick()
//                        }
//                    } else {
//                        HStack {
//                            Spacer()
//                            Loader()
//                                .scaleEffect(0.5)
//                            Spacer()
//                        }
//                    }
//                }
//                .frame(width: 32, height: 32)
//                .animation(.easeInOut, value: likesData.isLikesLoading)
//            }
        }
    }
}

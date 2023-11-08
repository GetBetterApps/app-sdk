//
//  NoteItemHeader.swift
//  iosApp
//
//  Created by velkonost on 29.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct NoteItemHeader: View {
    
    let areaName: String
    let taskName: String?
    let areaIcon: UIImage
    let likesData: LikesData
    let onLikeClick: () -> Void
    
    init(areaName: String, taskName: String? = nil, areaIcon: UIImage, likesData: LikesData, onLikeClick: @escaping () -> Void) {
        self.areaName = areaName
        self.taskName = taskName
        self.areaIcon = areaIcon
        self.likesData = likesData
        self.onLikeClick = onLikeClick
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
                        .style(.titleMedium)
                        .foregroundColor(.textPrimary)
                        .lineLimit(1)
                    Spacer()
                }
                
                if taskName != nil {
                    HStack {
                        Text(taskName!)
                            .style(.bodySmall)
                            .foregroundColor(.iconInactive)
                            .lineLimit(1)
                        Spacer()
                    }
                }
                
                Spacer()
            }
            .padding(.leading, 6)
            
            Spacer()
            
            ZStack(alignment: .center) {
                if (!likesData.isLikesLoading) {
                    VStack {
                        Image(
                            uiImage: likesData.userLike == LikeType.positive ? SharedR.images().ic_heart.toUIImage()! : SharedR.images().ic_heart_empty.toUIImage()!
                        )
                        .resizable()
                        .renderingMode(.template)
                        .foregroundColor(.buttonGradientStart)
                        .scaledToFill()
                        .frame(width: 24, height: 24)
                        
                        Text(String(likesData.totalLikes))
                            .style(.bodySmall)
                            .foregroundColor(.textPrimary)
                    }
                    .onTapGesture {
                        onLikeClick()
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
            .animation(.easeInOut, value: likesData.isLikesLoading)
        }
    }
}

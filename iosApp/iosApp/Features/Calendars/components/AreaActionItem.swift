//
//  AreaActionItem.swift
//  iosApp
//
//  Created by velkonost on 22.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AreaActionItem: View {
    
    let item: Area
    let onClick: (Int32) -> Void
    let onLikeClick: (Area) -> Void
    
    init(item: Area, onClick: @escaping (Int32) -> Void, onLikeClick: @escaping (Area) -> Void) {
        self.item = item
        self.onClick = onClick
        self.onLikeClick = onLikeClick
    }
    
    
    var body: some View {
        PrimaryBox(topPadding: 0) {
            HStack {
                Image(uiImage: Emoji.companion.getIconById(id: item.emojiId as! Int32).toUIImage()!)
                    .resizable()
                    .padding(8)
                    .frame(width: 64, height: 64)
                    .scaledToFill()
                
                
                VStack(alignment: .leading) {
                    Spacer()
                    Text(item.name)
                        .style(.labelLarge)
                        .lineLimit(1)
                        .fixedSize(horizontal: false, vertical: true)
                        .foregroundColor(.textTitle)
                        .multilineTextAlignment(.leading)
                    
                    Text(item.description_)
                        .style(.bodySmall)
                        .foregroundColor(.textSecondaryTitle)
                        .lineLimit(2)
                        .fixedSize(horizontal: false, vertical: true)
                        .padding(.top, 4)
                        .multilineTextAlignment(.leading)
                    
                    Spacer()
                }
                .padding(.leading, 12)
                
                Spacer()
                
                if item.isPrivate {
                    VStack {
                        Image(uiImage: SharedR.images().ic_lock.toUIImage()!)
                            .resizable()
                            .renderingMode(.template)
                            .scaledToFit()
                            .padding(4)
                            .frame(width: 24, height: 24)
                            .foregroundColor(.iconInactive)
                            .background(
                                RoundedRectangle(cornerRadius: 8)
                                    .fill(Color.mainBackground)
                            )
                        Spacer()
                    }
                } else {
                    ZStack(alignment: .center) {
                        if (!item.likesData.isLikesLoading) {
                            VStack {
                                Image(
                                    uiImage: item.likesData.userLike == LikeType.positive ? SharedR.images().ic_heart.toUIImage()! : SharedR.images().ic_heart_empty.toUIImage()!
                                )
                                .resizable()
                                .renderingMode(.template)
                                .foregroundColor(.buttonGradientStart)
                                .scaledToFill()
                                .frame(width: 24, height: 24)
                                
                                Text(String(item.likesData.totalLikes))
                                    .style(.bodySmall)
                                    .foregroundColor(.textPrimary)
                            }
                            .onTapGesture {
                                onLikeClick(item)
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
                    .animation(.easeInOut, value: item.likesData.isLikesLoading)
                }
                
            }.frame(minWidth: 0, maxWidth: .infinity)
        }
        .frame(minWidth: 0, maxWidth: .infinity)
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onClick(item.id)
        }
    }
}

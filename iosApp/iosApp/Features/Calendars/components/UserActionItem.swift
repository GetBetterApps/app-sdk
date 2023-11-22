//
//  UserActionItem.swift
//  iosApp
//
//  Created by velkonost on 22.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct UserActionItem: View {
    
    let isLoading: Bool
    let item: UserInfoShort?
    let onClick: () -> Void
    
    init(isLoading: Bool, item: UserInfoShort?, onClick: @escaping () -> Void) {
        self.isLoading = isLoading
        self.item = item
        self.onClick = onClick
    }
    
    var body: some View {
        PrimaryBox(
            padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero),
            topPadding: 0
        ) {
            HStack {
                
                if isLoading {
                    Spacer()
                    Loader(size: 32).scaleEffect(0.6)
                    Spacer()
                } else {
                    
                    if item?.avatarUrl != nil {
                        AsyncImage(url: URL(string: item!.avatarUrl!)) { image in
                            image
                                .resizable()
                                .scaledToFill()
                                .frame(width: 32, height: 32)
                                .clipped()
                                .cornerRadius(8)
                        } placeholder: {
                            Image(uiImage: SharedR.images().logo.toUIImage()!)
                                .resizable()
                                .frame(width: 32, height: 32)
                                .clipped()
                                .cornerRadius(8)
                        }
                        
                    } else {
                        Image(uiImage: SharedR.images().logo.toUIImage()!)
                            .resizable()
                            .frame(width: 32, height: 32)
                            .clipped()
                            .cornerRadius(8)
                    }
                    
                    Text(item?.displayName != nil ? item!.displayName! : "")
                        .style(.titleMedium)
                        .lineLimit(1)
                        .foregroundColor(.textPrimary)
                        .padding(.leading, 6)
                        .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
                }
            }
            .padding(16)
            .frame(minWidth: 0, maxWidth: .infinity)
            .contentShape(Rectangle())
            .onTapGesture {
                let impactMed = UIImpactFeedbackGenerator(style: .medium)
                impactMed.impactOccurred()
                onClick()
            }
        }
        .animation(.easeInOut, value: isLoading)
    }
}

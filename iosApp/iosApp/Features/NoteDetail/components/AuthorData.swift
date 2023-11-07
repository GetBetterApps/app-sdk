//
//  AuthorData.swift
//  iosApp
//
//  Created by velkonost on 07.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AuthorData: View {
    
    let isLoading: Bool
    let author: UserInfoShort?
    let onClick: () -> Void
    
    init(isLoading: Bool, author: UserInfoShort?, onClick: @escaping () -> Void) {
        self.isLoading = isLoading
        self.author = author
        self.onClick = onClick
    }
    
    var body: some View {
        PrimaryBox(
            padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero)
        ) {
            HStack {
                
                if isLoading {
                    Spacer()
                    Loader(size: 32)
                    Spacer()
                } else {
                    
                    if author?.avatar != nil {
                        Image(uiImage: UIImage(data: author!.avatar!.toData()!)!)
                            .resizable()
                            .scaledToFill()
                            .frame(width: 32, height: 32)
                            .clipped()
                            .cornerRadius(8)
                    } else {
                        Image(uiImage: SharedR.images().logo.toUIImage()!)
                            .resizable()
                            .frame(width: 32, height: 32)
                            .clipped()
                            .cornerRadius(8)
                    }
                    
                    Text(author?.displayName != nil ? author!.displayName! : "")
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

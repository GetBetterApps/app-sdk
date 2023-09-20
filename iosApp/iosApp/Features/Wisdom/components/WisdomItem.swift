//
//  WisdomItem.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct WisdomItem: View {
    
    let title: String
    let description: String
    let backgroundImage: UIImage
    let onClick: () -> Void
    
    init(title: String, description: String, backgroundImage: UIImage, onClick: @escaping () -> Void) {
        self.title = title
        self.description = description
        self.backgroundImage = backgroundImage
        self.onClick = onClick
    }
    
    var body: some View {
        ZStack {
            VStack(alignment: .center) {
                Spacer()
                HStack {
                    Text(title)
                        .style(.titleMedium)
                        .foregroundColor(.textTitle)
                        .frame(alignment: .leading)
                    Spacer()
                }
                
                HStack {
                    Text(description)
                        .style(.bodyMedium)
                        .foregroundColor(.textRegularTitle)
                        .frame(alignment: .leading)
                    Spacer()
                }
                
            }
            .padding(.init(16))
            .background(
                RoundedRectangle(cornerRadius: 12)
                    .fill(Color.backgroundItem).opacity(0.4)
                    .shadow(radius: 8)
            )
            .padding(.init(16))
            
        }
        
        .frame(height: 128)
        
        .background(
            Image(uiImage: backgroundImage)
                .resizable()
                .frame(height: 128)
                .shadow(radius: 8)
                .aspectRatio(contentMode: .fill)
                .scaledToFit()
                .cornerRadius(12)
        )
        .padding(.init(top: 16, leading: .zero, bottom: .zero, trailing: .zero))
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onClick()
        }
    }
}

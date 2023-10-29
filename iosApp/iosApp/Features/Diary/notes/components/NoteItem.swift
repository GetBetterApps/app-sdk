//
//  NoteItem.swift
//  iosApp
//
//  Created by velkonost on 29.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct NoteItem: View {
    
    let item: Area
    
    var body: some View {
        PrimaryBox {
            VStack {
                
                HStack {
                    Image(uiImage: Emoji.emoji1.icon.toUIImage()!)
                        .resizable()
                        .scaledToFit()
                        .frame(width: 32, height: 32)
                    
                    VStack {
                        Text("Very long and interesting area name bla bla bla")
                            .style(.titleMedium)
                            .foregroundColor(.textPrimary)
                            .lineLimit(1)
                            .padding(.bottom, 2)
                        
                        Text("difficult and amazing task name oalalalallalalala")
                            .style(.bodySmall)
                            .foregroundColor(.iconInactive)
                            .lineLimit(1)
                            .padding(.top, 2)
                    }
                    .padding(.leading, 6)
                }
                
            }
        }
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
        }
    }
}

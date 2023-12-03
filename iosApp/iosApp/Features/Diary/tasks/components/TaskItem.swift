//
//  TaskItem.swift
//  iosApp
//
//  Created by velkonost on 03.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import SwiftUIFlow

struct TaskItem: View {
    
    private let item: TaskUI
    
    init(item: TaskUI) {
        self.item = item
    }
    
    var body: some View {
        PrimaryBox {
            ZStack {
                VStack(spacing: 0) {
                    TaskItemHeader(
                        areaName: item.area.name,
                        taskName: item.name,
                        areaIcon: Emoji.companion.getIconById(id: item.area.emojiId as! Int32).toUIImage()!,
                        isFavorite: item.isFavorite,
                        onFavoriteClick: {
                            
                        }
                    )
                    
                    Text(item.why)
                        .style(.bodyMedium)
                        .foregroundColor(.textTitle)
                        .multilineTextAlignment(.leading)
//                        .lineLimit(5)
                        .padding(.top, 12)
                    
                    VFlow(alignment: .leading, spacing: 3) {
                        ForEach(item.abilities, id: \.self) { ability in
                            TagItem(tag: TagUI(id: ability.id!.stringValue, text: ability.name))
                        }
                    }
                    .padding(.top, 12)
                }
                .opacity(item.isCompleted ? 0.2 : 1)
                
                if item.isCompleted {
                    ZStack {
                        Image(uiImage: SharedR.images().ic_save.toUIImage()!)
                            .resizable()
                            .renderingMode(.template)
                            .foregroundColor(.green.opacity(0.5))
                            .frame(width: 128, height: 128, alignment: .center)
                    }
                }
            }
        }
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
        }
    }
}

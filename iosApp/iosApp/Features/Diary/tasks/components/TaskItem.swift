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
    private let onClick: () -> Void
    private let onFavoriteClick: () -> Void
    
    init(
        item: TaskUI,
        onClick: @escaping () -> Void,
        onFavoriteClick: @escaping () -> Void
    ) {
        self.item = item
        self.onClick = onClick
        self.onFavoriteClick = onFavoriteClick
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
                        isFavoriteLoading: item.isFavoriteLoading,
                        onFavoriteClick: onFavoriteClick
                    )
                    
                    HStack {
                        Text(item.why)
                            .style(.bodyMedium)
                            .foregroundColor(.textTitle)
                            .multilineTextAlignment(.leading)
                            .padding(.top, 12)
                        Spacer()
                    }
                    
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
                            .frame(width: 64, height: 64, alignment: .center)
                    }
                }
            }
        }
        .onTapGesture {
            onClick()
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
        }
    }
}

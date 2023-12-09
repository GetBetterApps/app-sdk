//
//  TaskActionItem.swift
//  iosApp
//
//  Created by velkonost on 09.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import SwiftUIFlow

struct TaskActionItem: View {
    
    let item: TaskUI
    let onClick: () -> Void
    
    var body: some View {
        PrimaryBox {
            ZStack {
                VStack(spacing: 0) {
                    TaskItemHeader(
                        areaName: item.area.name,
                        taskName: item.name,
                        areaIcon: Emoji.companion.getIconById(id: item.area.emojiId as! Int32).toUIImage()!,
                        isFavorite: item.isFavorite,
                        isFavoriteLoading: item.isFavoriteLoading
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
            }
        }
        .onTapGesture {
            onClick()
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
        }
    }
}

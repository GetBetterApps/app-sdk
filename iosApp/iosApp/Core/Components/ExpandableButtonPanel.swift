//
//  ExpandableButtonPanel.swift
//  iosApp
//
//  Created by velkonost on 27.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK


struct ExpandableButtonItem: Identifiable {
    let id = UUID()
    let icon: UIImage
    let iconExpanded: UIImage?
    let label: String
    private(set) var action: (() -> Void)? = nil
}

struct ExpandableButtonPanel: View {
    
    var paddingBottom: Int = 90
    let primaryItem: ExpandableButtonItem
    let secondaryItems: [ExpandableButtonItem]
    
    private let noop: () -> Void = {}
    private let size: CGFloat = 64
    private var cornerRadius: CGFloat {
        get { self.isExpanded ? 12 : 99 }
    }
    private let shadowColor = Color.black.opacity(0.4)
    private let shadowPosition: (x: CGFloat, y: CGFloat) = (x: 2, y: 2)
    private let shadowRadius: CGFloat = 3
    
    @State private var isExpanded = false
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            if self.isExpanded {
                ForEach(secondaryItems) { item in
                    HStack(alignment: .center, spacing: 0) {
                        Image(uiImage: item.icon)
                            .resizable()
                            .renderingMode(.template)
                            .scaledToFill()
                            .padding(16)
                            .frame(width: 64, height: 64)
                            .foregroundColor(.iconInactive)
                        Text(item.label)
                            .style(.titleSmall)
                            .foregroundColor(.textSecondaryTitle)
                    }
                    .padding(.init(top: .zero, leading: .zero, bottom: .zero, trailing: 18))
                    .onTapGesture {
                        let impactMed = UIImpactFeedbackGenerator(style: .medium)
                        impactMed.impactOccurred()
                        (item.action ?? noop)()
                        self.isExpanded.toggle()
                    }
                }
            }
            
            HStack(alignment: .center, spacing: 0) {
                Image(
                    uiImage: self.isExpanded && primaryItem.iconExpanded != nil ? primaryItem.iconExpanded! : primaryItem.icon
                )
                .resizable()
                .renderingMode(.template)
                .scaledToFill()
                .padding(16)
                .frame(width: 64, height: 64)
                .foregroundColor(.iconInactive)
                
                if self.isExpanded {
                    Text(primaryItem.label)
                        .style(.titleSmall)
                        .foregroundColor(.textSecondaryTitle)
                }
            }
            .onTapGesture {
                withAnimation {
                    let impactMed = UIImpactFeedbackGenerator(style: .medium)
                    impactMed.impactOccurred()
                    self.isExpanded.toggle()
                }
            }
        }
        .background(Color.backgroundIcon)
        .cornerRadius(cornerRadius)
        .shadow(
            color: shadowColor,
            radius: shadowRadius,
            x: shadowPosition.x,
            y: shadowPosition.y
        )
        .padding(.bottom, CGFloat(paddingBottom))
        .padding(.trailing, 12)
    }
}



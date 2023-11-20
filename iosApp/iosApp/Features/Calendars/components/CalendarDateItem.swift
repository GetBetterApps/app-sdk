//
//  CalendarDateItem.swift
//  iosApp
//
//  Created by velkonost on 20.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct CalendarDateItem: View {
    
    let item: DateUIItem
    let isSelected: Bool
    let onClick: (Int64) -> Void
    
    init(item: DateUIItem, isSelected: Bool, onClick: @escaping (Int64) -> Void) {
        self.item = item
        self.isSelected = isSelected
        self.onClick = onClick
    }
    
    var body: some View {
        
        VStack {
            Text(item.dayOfWeek.localized().uppercased())
                .style(.labelMedium)
                .foregroundColor(isSelected ? .textLight : .textPrimary)
            
            Text(item.day.localized())
                .style(.labelMedium)
                .foregroundColor(isSelected ? .textLight : .textPrimary)
        }
        .padding(4)
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color.backgroundItem)
                .shadow(radius: 8)
        )
        .frame(width: 52, height: 52)
        .onTapGesture {
            onClick(item.id)
        }
    }
}
//
//  AreaItem.swift
//  iosApp
//
//  Created by velkonost on 27.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AreaItem: View {
    
    let onClick: () -> Void
    
    init(onClick: @escaping () -> Void) {
        self.onClick = onClick
    }
    
    var body: some View {
        PrimaryBox {
            HStack {
                Image(uiImage: SharedR.images().ic_menu_profile.toUIImage()!)
                    .resizable()
                    .frame(width: 64, height: 64)
                    .scaledToFill()
                
                VStack(alignment: .leading) {
                    Spacer()
                    Text("Healthy health")
                        .style(.labelLarge)
                        .foregroundColor(.textTitle)
                        .multilineTextAlignment(.leading)
                    
                    Text("также у пользователя в области должны быть кастомные поля, его прогресс: добавлена ли эта область (поле users = map(userRef to exp) в области?)")
                        .style(.bodySmall)
                        .foregroundColor(.textSecondaryTitle)
                        .lineLimit(2)
                        .fixedSize(horizontal: false, vertical: true)
                        .padding(.top, 4)
                        .multilineTextAlignment(.leading)
                    
                    Spacer()
                }
                .padding(.leading, 12)
            }
        }
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onClick()
        }
    }
}

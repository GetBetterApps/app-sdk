//
//  AddAreaItem.swift
//  iosApp
//
//  Created by velkonost on 06.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AddAreaItem: View {
    
    private let item: AreaUI
    private let onAreaClick: (Int32) -> Void
    private let onAddAreaClick: (Int32) -> Void
    
    init(
        item: AreaUI,
        onAreaClick: @escaping (Int32) -> Void,
        onAddAreaClick: @escaping (Int32) -> Void
    ) {
        self.item = item
        self.onAreaClick = onAreaClick
        self.onAddAreaClick = onAddAreaClick
    }
    
    var body: some View {
        PrimaryBox {
            VStack {
                HStack {
                    Image(uiImage: item.emojiRes.toUIImage()!)
                        .resizable()
                        .padding(8)
                        .frame(width: 64, height: 64)
                        .scaledToFill()
                    
                    
                    VStack(alignment: .leading) {
                        Spacer()
                        Text(item.name)
                            .style(.labelLarge)
                            .foregroundColor(.textTitle)
                            .multilineTextAlignment(.leading)
                        
                        Text(item.description_)
                            .style(.bodySmall)
                            .foregroundColor(.textSecondaryTitle)
                            .lineLimit(2)
                            .fixedSize(horizontal: false, vertical: true)
                            .padding(.top, 4)
                            .multilineTextAlignment(.leading)
                        
                        Spacer()
                    }
                    .padding(.leading, 12)
                    Spacer()
                }.frame(minWidth: 0, maxWidth: .infinity)
                
                HStack(alignment: .center) {
                    Spacer()
                    Text("14000\nmembers")
                        .style(.labelSmall)
                        .foregroundColor(.textSecondaryTitle)
                        .multilineTextAlignment(.center)
                        .frame(width: 80)
                        .padding(4)
                        .background(
                            RoundedRectangle(cornerRadius: 8)
                                .fill(Color.backgroundIcon)
                        )
                    Spacer()
                    Text("20200\nnotes")
                        .style(.labelSmall)
                        .foregroundColor(.textSecondaryTitle)
                        .multilineTextAlignment(.center)
                        .frame(width: 80)
                        .padding(4)
                        .background(
                            RoundedRectangle(cornerRadius: 8)
                                .fill(Color.backgroundIcon)
                        )
                    Spacer()
                    Text("20200\ntasks")
                        .style(.labelSmall)
                        .foregroundColor(.textSecondaryTitle)
                        .multilineTextAlignment(.center)
                        .frame(width: 80)
                        .padding(4)
                        .background(
                            RoundedRectangle(cornerRadius: 8)
                                .fill(Color.backgroundIcon)
                        )
                    Spacer()
                }
                .padding(.top, 16)
                
                if item.termsOfMembership == TermsOfMembership.allow {
                    AppButton(
                        labelText: SharedR.strings().add_area_add_area_button.desc().localized(),
                        isLoading: item.isLoading
                    ) {
                        onAddAreaClick(item.id)
                    }
                    .padding(.top, 16)
                } else {
                    let disabledButtonLabel =
                    if item.termsOfMembership == TermsOfMembership.alreadyjoined {
                        SharedR.strings().add_area_already_added_button.desc().localized()
                    } else {
                        SharedR.strings().add_area_low_level_button.desc().localized()
                    }
                    
                    DisabledAppButton(labelText: disabledButtonLabel)
                        .padding(.top, 16)
                }
            }
        }
        .frame(minWidth: 0, maxWidth: .infinity)
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
            onAreaClick(item.id)
        }
    }
}

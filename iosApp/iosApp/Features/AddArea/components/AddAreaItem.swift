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
    
    init(item: AreaUI) {
        self.item = item
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
                        labelText: "Add Area",
                        isLoading: false
                    ) {
                        
                    }
                    .padding(.top, 16)
                } else {
                    let disabledButtonLabel =
                    if item.termsOfMembership == TermsOfMembership.alreadyjoined {
                        "Already added"
                    } else {
                        "Low level"
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
            //            onClick()
        }
    }
}

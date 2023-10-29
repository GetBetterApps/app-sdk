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
import SwiftUIFlow

struct NoteItem: View {
    
    let item: Area
    
    let tags = [
            "123",
            "wfpejgrg",
            "fkreog",
            "123",
            "wfpejgrg",
            "fkreog",
            "123",
            "wfpejgrg",
            "fkreog",
            "123",
            "wfpejgrg",
            "fkreog"
        ]
    
    var body: some View {
        PrimaryBox {
            VStack(spacing: 0) {
                
                HStack {
                    Image(uiImage: Emoji.emoji1.icon.toUIImage()!)
                        .resizable()
                        .scaledToFit()
                        .frame(width: 32, height: 32)
                    
                    VStack {
                        Spacer()
                        
                        HStack {
                            Text("Very long and interesting area name bla bla bla")
                                .style(.titleMedium)
                                .foregroundColor(.textPrimary)
                                .lineLimit(1)
                            Spacer()
                        }
                        
                        HStack {
                            Text("difficult and amazing task name oalalalallalalala")
                                .style(.bodySmall)
                                .foregroundColor(.iconInactive)
                                .lineLimit(1)
                            Spacer()
                        }
                        
                        Spacer()
                    }
                    .padding(.leading, 6)
                }
                
                VStack {
                    
                    HStack(spacing: 0) {
                        Image(uiImage: SharedR.images().ic_goal.toUIImage()!)
                            .resizable()
                            .renderingMode(.template)
                            .scaledToFit()
                            .padding(4)
                            .frame(width: 24, height: 24)
                            .foregroundColor(.iconInactive)
                            .background(
                                RoundedRectangle(cornerRadius: 8)
                                    .fill(Color.backgroundItem)
                            )
                        
                        Text("23.11.2023")
                            .style(.labelSmall)
                            .foregroundColor(.textPrimary)
                            .padding(.horizontal, 4)
                            .frame(height: 24)
                            .background(
                                RoundedRectangle(cornerRadius: 8)
                                    .fill(Color.backgroundItem)
                            )
                            .padding(.leading, 4)
                        
                        Text("4 / 6")
                            .style(.labelSmall)
                            .foregroundColor(.textPrimary)
                            .padding(.horizontal, 4)
                            .frame(height: 24)
                            .background(
                                RoundedRectangle(cornerRadius: 8)
                                    .fill(Color.backgroundItem)
                            )
                            .padding(.leading, 4)
                        
                        Spacer()
                        
                        HStack(spacing: 0) {
                            Text("1")
                                .style(.labelSmall)
                                .foregroundColor(.textPrimary)
                                .padding(.trailing, 2)
                            
                            Image(uiImage: SharedR.images().ic_media.toUIImage()!)
                                .resizable()
                                .renderingMode(.template)
                                .scaledToFit()
                                .padding(4)
                                .frame(width: 24, height: 24)
                                .foregroundColor(.iconInactive)
                                
                        }
                        .padding(.horizontal, 4)
                        .frame(height: 24)
                        .background(
                            RoundedRectangle(cornerRadius: 8)
                                .fill(Color.backgroundItem)
                        )
                        .padding(.trailing, 4)
                        
                        Image(uiImage: SharedR.images().ic_lock.toUIImage()!)
                            .resizable()
                            .renderingMode(.template)
                            .scaledToFit()
                            .padding(4)
                            .frame(width: 24, height: 24)
                            .foregroundColor(.iconInactive)
                            .background(
                                RoundedRectangle(cornerRadius: 8)
                                    .fill(Color.backgroundItem)
                            )
                    }
                    
                    Text("С 9 по 13 ноября можно будет бесплатно поиграть в стратегию Anno 1800 на консолях PlayStation 5 и Xbox Series.\n\nНемного раньше «бесплатные выходные» пройдут на ПК, со 2 по 6 ноября.\n\nДо 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%.\"До 18 ноября Deluxe-издание игры можно приобрести в PlayStation Store со скидкой 40%."
                    )
                    .style(.bodyMedium)
                    .foregroundColor(.textTitle)
                    .lineLimit(10)
                    .padding(.top, 12)
                    
                    VFlow(alignment: .leading) {
                        ForEach(tags, id: \.self) { tag in
                            TagItem(tag: TagUI(id: "0", text: tag))
                        }
                    }
                    .padding(.top, 12)
                }
                
                .padding(8)
                .background(
                    RoundedRectangle(cornerRadius: 12)
                        .fill(Color.textFieldBackground)
                )
                .padding(.top, 12)
                
            }
        }
        .onTapGesture {
            let impactMed = UIImpactFeedbackGenerator(style: .medium)
            impactMed.impactOccurred()
        }
    }
}

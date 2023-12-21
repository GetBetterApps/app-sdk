//
//  NoteDetailHeader.swift
//  iosApp
//
//  Created by velkonost on 03.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct NoteDetailHeader : View {
    
    let noteType: NoteType
    let isNotePrivate: Bool
    let likesData: LikesData
    
    let onLikeClick: () -> Void
    private let onHintClick: () -> Void
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    init(noteType: NoteType, isNotePrivate: Bool, likesData: LikesData, onLikeClick: @escaping () -> Void, onHintClick: @escaping () -> Void) {
        self.noteType = noteType
        self.isNotePrivate = isNotePrivate
        self.likesData = likesData
        self.onLikeClick = onLikeClick
        self.onHintClick = onHintClick
    }
    
    var body: some View {
        HStack {
            Image(uiImage: SharedR.images().ic_arrow_back.toUIImage()!)
                .resizable()
                .renderingMode(.template)
                .foregroundColor(.iconActive)
                .frame(width: 32, height: 32)
                .scaledToFill()
                .padding(4)
                .background(
                    RoundedRectangle(cornerRadius: 8)
                        .fill(Color.backgroundIcon)
                )
                .onTapGesture {
                    let impactMed = UIImpactFeedbackGenerator(style: .medium)
                    impactMed.impactOccurred()
                    presentationMode.wrappedValue.dismiss()
                }
            
            Text(
                noteType == NoteType.default_ ? SharedR.strings().note_detail_title.desc().localized() : SharedR.strings().goal_detail_title.desc().localized()
            )
                .style(.headlineSmall)
                .foregroundColor(.textTitle)
                .padding(.leading, 12)
            
            HintButton(onClick: onHintClick)
                .padding(.top, 4)
            
            Spacer()
            
            if isNotePrivate {
                Image(uiImage: SharedR.images().ic_lock.toUIImage()!)
                    .resizable()
                    .renderingMode(.template)
                    .foregroundColor(.iconInactive)
                    .frame(width: 32, height: 32)
                    .scaledToFill()
                    .padding(4)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .fill(Color.backgroundIcon)
                    )
            } else {
                ZStack(alignment: .center) {
                    if (!likesData.isLikesLoading) {
                        VStack {
                            Image(
                                uiImage: likesData.userLike == LikeType.positive ? SharedR.images().ic_heart.toUIImage()! : SharedR.images().ic_heart_empty.toUIImage()!
                            )
                            .resizable()
                            .renderingMode(.template)
                            .foregroundColor(.buttonGradientStart)
                            .scaledToFill()
                            .frame(width: 28, height: 28)
                            
                            Text(String(likesData.totalLikes))
                                .style(.bodySmall)
                                .foregroundColor(.textPrimary)
                        }
                        .onTapGesture {
                            onLikeClick()
                        }
                    } else {
                        HStack {
                            Spacer()
                            Loader()
                                .scaleEffect(0.5)
                            Spacer()
                        }
                    }
                }
                .frame(width: 32, height: 32)
                .animation(.easeInOut, value: likesData.isLikesLoading)
            }
        }
    }
}

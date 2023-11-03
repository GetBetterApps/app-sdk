//
//  SubNoteItem.swift
//  iosApp
//
//  Created by velkonost on 26.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct SubNoteItem: View {
    
    let item: SubNoteUI
    let onlyView: Bool
    let isCompleteVisible: Bool
    
    let onDeleteSubNote: (SubNoteUI) -> Void
    let onCompleteClick: ((SubNoteUI) -> Void)?
    
    init(item: SubNoteUI, onlyView: Bool = false, isCompleteVisible: Bool = false,
         onDeleteSubNote: @escaping (SubNoteUI) -> Void,
         onCompleteClick: ((SubNoteUI) -> Void)? = nil) {
        self.item = item
        self.onlyView = onlyView
        self.isCompleteVisible = isCompleteVisible
        self.onDeleteSubNote = onDeleteSubNote
        self.onCompleteClick = onCompleteClick
    }
    
    var body: some View {
        HStack {
            ScrollView(.horizontal, showsIndicators: false) {
                Text(item.text)
                    .style(.titleSmall)
                    .lineLimit(1)
                    .foregroundColor(.textSecondaryTitle)
                    .padding(.leading, 12)
            }
            
            Spacer()
            
            if !onlyView {
                ZStack {
                    Image(uiImage: SharedR.images().ic_close.toUIImage()!)
                        .resizable()
                        .renderingMode(.template)
                        .foregroundColor(.iconInactive)
                        .frame(width: 24, height: 24, alignment: .center)
                        .onTapGesture {
                            onDeleteSubNote(item)
                        }
                }
                .frame(width: 36, height: 36, alignment: .center)
                .background(
                    RoundedRectangle(cornerRadius: 12)
                        .fill(Color.backgroundItem)
                )
                .padding(.trailing, 12)
            }
            
            if onlyView && isCompleteVisible {
                ZStack {
                    Image(
                        uiImage: item.completionDate != nil ? SharedR.images().ic_cancel.toUIImage()! : SharedR.images().ic_save.toUIImage()!
                    )
                    .resizable()
                    .renderingMode(.template)
                    .foregroundColor(.iconInactive)
                    .frame(width: 24, height: 24, alignment: .center)
                    .onTapGesture {
                        if onCompleteClick != nil {
                            onCompleteClick!(item)
                        }
                    }
                }
                .frame(width: 36, height: 36, alignment: .center)
                .background(
                    RoundedRectangle(cornerRadius: 12)
                        .fill(Color.backgroundItem)
                )
                .padding(.trailing, 12)
            }
        }
        .animation(.easeInOut, value: onlyView)
        .frame(height: 56)
        .background(
            RoundedRectangle(cornerRadius: 12)
                .fill(Color.textFieldBackground)
        )
    }
}

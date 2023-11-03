//
//  TagsBlock.swift
//  iosApp
//
//  Created by velkonost on 26.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import SwiftUIFlow

struct TagsBlock: View {
    
    let tags: [TagUI]
    @Binding private var newTag: TagUI

    let onlyView: Bool
    
    let onNewTagChanged: (String) -> Void
    let onAddNewTag: () -> Void
    let onTagDelete: (String) -> Void

    
    init(tags: [TagUI], newTag: Binding<TagUI>, onlyView: Bool = false, onNewTagChanged: @escaping (String) -> Void, onAddNewTag: @escaping () -> Void, onTagDelete: @escaping (String) -> Void) {
        self.tags = tags
        self._newTag = newTag
        self.onlyView = onlyView
        self.onNewTagChanged = onNewTagChanged
        self.onAddNewTag = onAddNewTag
        self.onTagDelete = onTagDelete
    }
    
    var body: some View {
        PrimaryBox(
            padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero)
        ){
            VStack {
                HStack {
                    Text(SharedR.strings().create_note_tags_title.desc().localized())
                        .style(.titleMedium)
                        .foregroundColor(.textPrimary)
                        .lineLimit(1)
                    Spacer()
                }
                
                
                VFlow(alignment: .leading) {
                    ForEach(tags, id: \.self) { tag in
                        TagItem(
                            tag: tag,
                            onTagDelete: onTagDelete
                        )
                    }
                    
                    if !onlyView {
                        NewTagField(
                            value: $newTag,
                            placeholderText: SharedR.strings().create_note_tags_hint.desc().localized(),
                            onValueChanged: onNewTagChanged,
                            onAddNewTag: onAddNewTag
                        )
                    }
                }
            }
            .animation(.easeInOut, value: onlyView)
            .padding(16)
        }
    }
}

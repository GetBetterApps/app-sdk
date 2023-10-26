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
    
    let tags: [Tag]
    @Binding private var newTag: Tag
    
    let onNewTagChanged: (String) -> Void
    let onAddNewTag: () -> Void
    let onTagDelete: (String) -> Void
    
    init(tags: [Tag], newTag: Binding<Tag>, onNewTagChanged: @escaping (String) -> Void, onAddNewTag: @escaping () -> Void, onTagDelete: @escaping (String) -> Void) {
        self.tags = tags
        self._newTag = newTag
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
                    
                    NewTagField(
                        value: $newTag,
                        placeholderText: SharedR.strings().create_note_tags_hint.desc().localized(),
                        onValueChanged: onNewTagChanged,
                        onAddNewTag: onAddNewTag
                    )
                }
            }
            .padding(16)
        }
    }
}

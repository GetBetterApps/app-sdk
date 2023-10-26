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
    
    let tags: [String]
    @Binding private var newTagText: String
    
    let onNewTagChanged: (String) -> Void
    let onAddNewTag: () -> Void
    let onTagDelete: (String) -> Void
    
    init(tags: [String], newTagText: Binding<String>, onNewTagChanged: @escaping (String) -> Void, onAddNewTag: @escaping () -> Void, onTagDelete: @escaping (String) -> Void) {
        self.tags = tags
        self._newTagText = newTagText
        self.onNewTagChanged = onNewTagChanged
        self.onAddNewTag = onAddNewTag
        self.onTagDelete = onTagDelete
    }
    
    var body: some View {
        VFlow(alignment: .leading) {
            ForEach(tags, id: \.self) { tag in
                TagItem(
                    tag: tag,
                    onTagDelete: onTagDelete
                )
            }
            
            NewTagField(
                value: $newTagText,
                placeholderText: "Enter text",
                onValueChanged: onNewTagChanged,
                onAddNewTag: onAddNewTag
            )
        }
    }
}

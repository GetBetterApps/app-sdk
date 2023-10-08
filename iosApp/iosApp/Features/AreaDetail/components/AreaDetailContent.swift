//
//  AreaDetailContent.swift
//  iosApp
//
//  Created by velkonost on 08.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AreaDetailContent: View {
    
    let areaData: AreaDetailUI
    var isEditing: Bool
    
    @Binding var isEmojiPickerVisible: Bool
    let onEmojiClick: (Emoji) -> Void
    let onNameChanged: (String) -> Void
    let onDescriptionChanged: (String) -> Void
    
    init(areaData: AreaDetailUI, isEditing: Bool, isEmojiPickerVisible: Binding<Bool>, onEmojiClick: @escaping (Emoji) -> Void, onNameChanged: @escaping (String) -> Void, onDescriptionChanged: @escaping (String) -> Void) {
        self.areaData = areaData
        self.isEditing = isEditing
        self._isEmojiPickerVisible = isEmojiPickerVisible
        self.onEmojiClick = onEmojiClick
        self.onNameChanged = onNameChanged
        self.onDescriptionChanged = onDescriptionChanged
    }
    
    var body: some View {
        ScrollView(showsIndicators: false) {
            VStack {
                HStack {
                    Spacer()
                    SelectedEmojiImage(
                        selectedEmoji: areaData.emoji.icon.toUIImage()!,
                        imageSize: 96,
                        onClick: {
                            if isEditing {
                                withAnimation {
                                    isEmojiPickerVisible.toggle()
                                }
                            }
                        }
                    )
                    Spacer()
                }
                
                EmojiPicker(
                    isVisible: $isEmojiPickerVisible,
                    items: Emoji.entries,
                    onEmojiClick: onEmojiClick
                )
                
                SingleLineTextField(
                    value: areaData.name,
                    placeholderText: SharedR.strings().diary_areas_create_new_name_hint.desc().localized(),
                    isEnabled: isEditing,
                    textAlign: .center,
                    textStyle: .titleLarge,
                    paddings: EdgeInsets.init(),
                    onValueChanged: onNameChanged
                ).padding(.top, 24)
                
                MultilineTextField(
                    value: areaData.description_,
                    placeholderText: SharedR.strings().diary_areas_create_new_description_hint.desc().localized(),
                    minLines: 1,
                    isEnabled: isEditing, 
                    textAlign: .center,
                    onValueChanged: onDescriptionChanged
                )
                
                
            }.padding(20)
        }
        
    }
}

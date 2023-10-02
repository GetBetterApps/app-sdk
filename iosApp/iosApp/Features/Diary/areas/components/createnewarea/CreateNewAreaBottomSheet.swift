//
//  CreateNewAreaBottomSheet.swift
//  iosApp
//
//  Created by velkonost on 28.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct CreateNewAreaBottomSheet: View {
    
    @Binding private var state: CreateNewAreaViewState
    let emojiItems: [Emoji]
    
    let onEmojiClick: (Emoji) -> Void
    let onNameChanged: (String) -> Void
    let onDescriptionChanged: (String) -> Void
    let onRequiredLevelChanged: (Int) -> Void
    let onCreateClick: () -> Void
    
    @State private var isEmojiPickerVisible = false
    
    init(state: Binding<CreateNewAreaViewState>,
         emojiItems: [Emoji],
         onEmojiClick: @escaping (Emoji) -> Void,
         onNameChanged: @escaping (String) -> Void,
         onDescriptionChanged: @escaping (String) -> Void,
         onRequiredLevelChanged: @escaping (Int) -> Void,
         onCreateClick: @escaping () -> Void) {
        self._state = state
        self.emojiItems = emojiItems
        self.onEmojiClick = onEmojiClick
        self.onNameChanged = onNameChanged
        self.onDescriptionChanged = onDescriptionChanged
        self.onRequiredLevelChanged = onRequiredLevelChanged
        self.onCreateClick = onCreateClick
        
        //        self.selectedImage = self.state.selectedEmoji.icon.toUIImage()!
    }
    
    
    var body: some View {
        ZStack {
            Color.mainBackground
            
            VStack {
                if state.isLoading {
                    Loader()
                } else {
                    Text("Create new area")
                        .style(.headlineSmall)
                        .foregroundColor(.textTitle)
                        .frame(alignment: .center)
                    
                    HStack {
                        SelectedEmojiImage(selectedEmoji: state.selectedEmoji.icon.toUIImage()!) {
                            withAnimation {
                                isEmojiPickerVisible.toggle()
                            }
                        }
                        
                        SingleLineTextField(
                            value: state.name,
                            placeholderText: "placeholder"
                        ) { value in
                            onNameChanged(value)
                        }
                    }.padding(.top, 24)
                    
                    EmojiPicker(
                        isVisible: $isEmojiPickerVisible,
                        items: emojiItems
                    ) { value in
                        onEmojiClick(value)
                    }
                    
                    MultilineTextField(
                        value: state.description_,
                        placeholderText: "placeholder"
                    ) { value in
                        onDescriptionChanged(value)
                    }
                    
                    RequiredLevelRow(
                        title: "required level",
                        level: Int(state.requiredLevel)
                    ) { value in
                        onRequiredLevelChanged(value)
                    }
                    
                    Spacer()
                    
                    AppButton(
                        labelText: "Create",
                        isLoading: false
                    ) {
                        onCreateClick()
                    }
                    .padding(.bottom, 70)
                    
                }
            }.padding(20)
            
            
        }.edgesIgnoringSafeArea(.all)
            .onTapGesture {
                self.endTextEditing()
            }
    }
}

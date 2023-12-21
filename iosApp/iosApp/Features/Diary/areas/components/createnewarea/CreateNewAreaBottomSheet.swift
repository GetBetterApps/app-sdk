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
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct CreateNewAreaBottomSheet: View {
    
    @Binding private var state: CreateNewAreaViewState
    let emojiItems: [Emoji]
    
    let onEmojiClick: (Emoji) -> Void
    let onNameChanged: (String) -> Void
    let onDescriptionChanged: (String) -> Void
    let onRequiredLevelChanged: (Int) -> Void
    let onPrivateChanged: () -> Void
    let onCreateClick: () -> Void
    let onHintClick: () -> Void
    
    @State private var isEmojiPickerVisible = false
    
    @State private var resourceMessageText: String?
    @State private var snackBar: MessageType.SnackBar?
    @State private var showSnackBar: Bool = false
    @State private var messageDequeObserver: Task<(), Error>? = nil
    
    init(state: Binding<CreateNewAreaViewState>,
         emojiItems: [Emoji],
         onEmojiClick: @escaping (Emoji) -> Void,
         onNameChanged: @escaping (String) -> Void,
         onDescriptionChanged: @escaping (String) -> Void,
         onRequiredLevelChanged: @escaping (Int) -> Void,
         onPrivateChanged: @escaping () -> Void,
         onCreateClick: @escaping () -> Void,
         onHintClick: @escaping () -> Void
    ) {
        self._state = state
        self.emojiItems = emojiItems
        self.onEmojiClick = onEmojiClick
        self.onNameChanged = onNameChanged
        self.onDescriptionChanged = onDescriptionChanged
        self.onRequiredLevelChanged = onRequiredLevelChanged
        self.onPrivateChanged = onPrivateChanged
        self.onCreateClick = onCreateClick
        self.onHintClick = onHintClick
    }
    
    
    var body: some View {
        @State var isAreaPrivate = state.isPrivate
        
        ZStack {
            Color.mainBackground
            
            VStack {
                if state.isLoading {
                    Loader()
                } else {
                    HStack(spacing: 0) {
                        Text(SharedR.strings().diary_areas_create_new_area_title.desc().localized())
                            .style(.headlineSmall)
                            .foregroundColor(.textTitle)
                            .frame(alignment: .center)
                        HintButton(onClick: onHintClick)
                            .padding(.leading, 8)
                    }
                    
                    HStack {
                        SelectedEmojiImage(selectedEmoji: state.selectedEmoji.icon.toUIImage()!) {
                            withAnimation {
                                isEmojiPickerVisible.toggle()
                            }
                        }
                        
                        SingleLineTextField(
                            value: state.name,
                            placeholderText: SharedR.strings().diary_areas_create_new_name_hint.desc().localized()
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
                        placeholderText: SharedR.strings().diary_areas_create_new_description_hint.desc().localized()
                    ) { value in
                        onDescriptionChanged(value)
                    }
                    
                    RequiredLevelRow(
                        title: SharedR.strings().diary_areas_create_new_required_level.desc().localized(),
                        level: Int(state.requiredLevel)
                    ) { value in
                        onRequiredLevelChanged(value)
                    }
                    
                    PrivateSwitch(
                        onCheckedChange: onPrivateChanged,
                        isPrivate: $isAreaPrivate
                    )
                    
                    Spacer()
                    
                    AppButton(
                        labelText: SharedR.strings().diary_areas_create_button.desc().localized(),
                        isLoading: false
                    ) {
                        onCreateClick()
                    }
                    .padding(.bottom, 70)
                    
                }
            }.padding(20)
            
            
        }
        .edgesIgnoringSafeArea(.all)
        .onTapGesture {
            self.endTextEditing()
        }
        .snackBar(
            isShowing: $showSnackBar,
            text: resourceMessageText ?? "",
            snackBar: snackBar
        )
        .onAppear {
            if messageDequeObserver == nil {
                messageDequeObserver = Task {
                    for try await message in asyncSequence(for: MessageDeque.shared.invoke()) {
                        handle(resource: message)
                    }
                }
            }
        }
        .onDisappear {
            messageDequeObserver?.cancel()
            messageDequeObserver = nil
        }
    }
}

extension CreateNewAreaBottomSheet {
    private func handle(resource message: Message) {
        switch message.messageType {
            
        case let snackBar as MessageType.SnackBar : do {
            if showSnackBar == false {
                resourceMessageText = message.text != nil ? message.text : message.textResource?.localized()
                self.snackBar = snackBar
                withAnimation {
                    showSnackBar.toggle()
                }
                DispatchQueue.main.asyncAfter(deadline: .now() + 5) {
                    Task { try await MessageDeque.shared.dequeue() }
                }
            }
        }
            
        default: break
        }
    }
}

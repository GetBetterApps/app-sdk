//
//  ProfileDetailScreen.swift
//  iosApp
//
//  Created by velkonost on 19.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct ProfileDetailScreen: View {
    
    @StateObject var viewModel = ProfileDetailVIewModelDelegate()
    
    @Binding var userId: String?
    
    @State private var resourceMessageText: String?
    @State private var snackBar: MessageType.SnackBar?
    @State private var showSnackBar: Bool = false
    @State private var messageDequeObserver: Task<(), Error>? = nil
    
    init(userId: Binding<String?>) {
        self._userId = userId
    }
    
    var body: some View {
        @State var state = viewModel.state
        
        ZStack {
            Color.mainBackground
            
            if state.profileData.isLoading {
                VStack {
                    Loader()
                }
            } else {
                ScrollView(showsIndicators: false) {
                    LazyVStack(spacing: 0) {
                        ProfileHeader(
                            userName: state.profileData.userName,
                            avatarBytes: state.profileData.avatarBytes,
                            isLoading: state.profileData.isLoading,
                            onAvatarClick: {},
                            onSettingsClick: {},
                            showSettings: state.profileData.isLoading
                        )
                        
                        if state.profileData.experienceData != nil {
                            LevelBlock(experienceData: state.profileData.experienceData!, isOwn: false)
                        }
                        
                        if state.notesData.isLoading && state.notesData.items.isEmpty {
                            Loader()
                        } else {
                            HStack {
                                Text(SharedR.strings().diary_notes_title.desc().localized())
                                    .style(.headlineSmall)
                                    .foregroundColor(.textPrimary)
                                Spacer()
                            }.padding(.top, 24)
                            
                            ForEach(state.notesData.items, id: \.self.id) { item in
                                FeedNoteItem(
                                    item: item,
                                    onClick: { value in
                                        
                                    },
                                    onLikeClick: { value in
                                        viewModel.dispatch(action: ProfileDetailActionNoteLikeClick(value: value))
                                    }
                                )
                                .onAppear {
                                    checkPaginationThreshold(currentItemId: item.id)
                                }
                            }
                        }
                    }
                    .padding(.horizontal, 16)
                }
            }
        }
        .snackBar(
            isShowing: $showSnackBar,
            text: resourceMessageText ?? "",
            snackBar: snackBar
        )
        .onAppear {
            viewModel.onAppear(userId: userId!)
            
            if messageDequeObserver == nil {
                messageDequeObserver = Task {
                    for try await message in asyncSequence(for: MessageDeque.shared.invoke()) {
                        handle(resource: message)
                    }
                }
            }
        }
        .onDisappear {
            viewModel.onDisappear()
            messageDequeObserver?.cancel()
            messageDequeObserver = nil
        }
        .edgesIgnoringSafeArea(.all)
    }
}

extension ProfileDetailScreen {
    
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
    
    func checkPaginationThreshold(currentItemId: Int32) {
        let data = viewModel.state.notesData.items
        let loadMorePrefetch = Int(viewModel.state.notesData.loadMorePrefetch)
        let thresholdIndex = data.index(data.endIndex, offsetBy: -loadMorePrefetch)
        
        if data.firstIndex(where: { $0.id == currentItemId })! >= thresholdIndex && !viewModel.state.notesData.isLoading {
            viewModel.dispatch(action: ProfileDetailActionNotesLoadNextPage())
        }
    }
}

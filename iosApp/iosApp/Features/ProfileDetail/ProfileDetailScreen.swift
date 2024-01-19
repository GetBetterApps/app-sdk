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
    @State private var eventsObserver: Task<(), Error>? = nil
    
    private let onBlockSuccess: (() -> Void)?
    
    init(userId: Binding<String?>, onBlockSuccess: (() -> Void)? = nil) {
        self._userId = userId
        self.onBlockSuccess = onBlockSuccess
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
                    LazyVStack {
                        ProfileHeader(
                            userName: state.profileData.userName,
                            avatarUrl: state.profileData.avatarUrl,
                            isLoading: state.profileData.isLoading,
                            showSettings: state.profileData.isLoading,
                            onAvatarClick: {},
                            onSettingsClick: {},
                            onBlockUserClick: {
                                viewModel.dispatch(action: ProfileDetailActionBlockClick())
                            }
                        )
                        
                        if state.profileData.experienceData != nil {
                            LevelBlock(experienceData: state.profileData.experienceData!, isOwn: false)
                        }
                        
                        if state.notesData.isLoading && state.notesData.items.isEmpty {
                            Loader().padding(.top, 24)
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
                    
                    Spacer().frame(height: 140)
                }
                
                VStack {
                    Spacer()
                    
                    VStack {
                        Spacer()
                            .frame(height: 20)
                        
                        AppButton(
                            labelText: state.followData.state == FollowState.followed ? SharedR.strings().unfollow_title.desc().localized() : SharedR.strings().follow_title.desc().localized(),
                            isLoading: state.followData.isLoading
                        ) {
                            viewModel.dispatch(action: ProfileDetailActionFollowClick())
                        }
                        .padding(.bottom, 50)
                    }
                    .frame(minWidth: 0, maxWidth: .infinity)
                    .background(
                        Rectangle()
                            .fill(LinearGradient(gradient: Gradient(colors: [
                                .mainBackground,
                                .mainBackground,
                                .mainBackground,
                                .clear
                            ]), startPoint: .bottom, endPoint: .top)
                            )
                        
                    )
                    
                    
                }.ignoresSafeArea(.keyboard)
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
                
            observeEvents()
                
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

extension ProfileDetailScreen {
    func observeEvents() {
        if eventsObserver == nil {
            eventsObserver = Task {
                for try await event in asyncSequence(for: viewModel.delegate.events) {
                    switch(event) {
                    case _ as ProfileDetailEventBlockSuccess: do {
                        if onBlockSuccess != nil {
                            onBlockSuccess!()
                        }
                    }
                        
                    default:
                        break
                    }
                }
            }
        }
    }
    
}


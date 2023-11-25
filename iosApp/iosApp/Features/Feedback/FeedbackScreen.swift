//
//  FeedbackScreen.swift
//  iosApp
//
//  Created by velkonost on 25.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct FeedbackScreen: View {
    
    @StateViewModel var viewModel: FeedbackViewModel
    @State private var eventsObserver: Task<(), Error>? = nil
    @State private var showingCreateNewFeedbackSheet = false
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! FeedbackViewState
        
        ZStack {
            if state.isLoading && state.items.isEmpty {
                Loader().frame(alignment: .center)
            } else {
                VStack {
                    FeedbackListHeader()
                    
                    ScrollView(showsIndicators: false) {
                        LazyVStack(spacing: 0) {
                            ForEach(state.items, id: \.self) { item in
                                FeedbackItem(
                                    item: item,
                                    onClick: {
                                        
                                    }
                                )
                            }
                        }
                        .padding(.init(top: .zero, leading: 20, bottom: 100, trailing: 20))
                    }
                }
                
                VStack {
                    Spacer()
                    
                    VStack {
                        Spacer()
                            .frame(height: 20)
                        
                        AppButton(
                            labelText: SharedR.strings().diary_areas_create_button.desc().localized(),
                            isLoading: false
                        ) {
                            showingCreateNewFeedbackSheet = true
                        }
                        .padding(.bottom, 70)
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
        .sheet(isPresented: $showingCreateNewFeedbackSheet) {
            CreateNewFeedbackBottomSheet(
                newFeedbackState: state.newFeedback,
                onTypeChanged: { value in
                    viewModel.dispatch(action: NewFeedbackActionTypeChanged(value: value))
                },
                onTextChanged: { value in
                    viewModel.dispatch(action: NewFeedbackActionTextChanged(value: value))
                },
                onCreateClick: {
                    viewModel.dispatch(action: NewFeedbackActionCreateClick())
                }
            )
        }
        .onAppear {
            observeEvents()
        }
        .onDisappear {
            eventsObserver?.cancel()
            eventsObserver = nil
        }
    }
}

extension FeedbackScreen {
    func observeEvents() {
        if eventsObserver == nil {
            eventsObserver = Task {
                for try await event in asyncSequence(for: viewModel.events) {
                    switch(event) {
                    case _ as FeedbackEventNewFeedbackCreated: do {
                        showingCreateNewFeedbackSheet = false
                    }
                    
                    default:
                        break
                    }
                }
            }
        }
    }
}

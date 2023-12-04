//
//  TaskDetailScreen.swift
//  iosApp
//
//  Created by velkonost on 03.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct TaskDetailScreen : View {
    
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @StateViewModel var viewModel: TaskDetailViewModel
    
    @State private var selectedAreaId: Int32? = nil
    @State private var showingAreaDetailSheet = false
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! TaskDetailViewState
        
        ZStack {
            if state.isLoading || state.task == nil {
                VStack {
                    Loader()
                }
            } else {
                ScrollView(.vertical, showsIndicators: false) {
                    ScrollViewReader { value in
                        LazyVStack(spacing: 0) {
                            TaskDetailHeader(
                                isFavorite: state.task!.isFavorite,
                                isFavoriteLoading: state.task!.isFavoriteLoading,
                                onFavoriteClick: {
                                    
                                }
                            )
                            
                            if state.area != nil {
                                AreaData(
                                    area: state.area!,
                                    onClick: {
                                        selectedAreaId = state.area!.id
                                        showingAreaDetailSheet = true
                                    }
                                )
                            }
                            
                            HStack {
                                Text(state.task!.name)
                                    .style(.labelLarge, withSize: 18)
                                    .foregroundColor(.textLight)
                                    .padding(.top, 24)
                                Spacer()
                            }
                            
                            HStack {
                                Text(SharedR.strings().task_what_to_do_title.desc().localized())
                                    .style(.labelMedium)
                                    .foregroundColor(.textTitle)
                                    .padding(.top, 16)
                                Spacer()
                            }
                            
                            HStack {
                                Text(state.task!.whatToDo)
                                    .style(.labelMedium)
                                    .foregroundColor(.textPrimary)
                                    .padding(.top, 6)
                                Spacer()
                            }
                            
                            HStack {
                                Text(SharedR.strings().task_why_title.desc().localized())
                                    .style(.labelMedium)
                                    .foregroundColor(.textTitle)
                                    .padding(.top, 16)
                                Spacer()
                            }
                            
                            HStack {
                                Text(state.task!.why)
                                    .style(.labelMedium)
                                    .foregroundColor(.textPrimary)
                                    .padding(.top, 6)
                                Spacer()
                            }
                            
                            HStack {
                                Text(SharedR.strings().task_abilities_title.desc().localized())
                                    .style(.labelMedium)
                                    .foregroundColor(.textTitle)
                                    .padding(.top, 16)
                                Spacer()
                            }
                        }
                        .frame(alignment: .leading)
                        .padding(.horizontal, 20)
                    }
                }
            }
        }
        .sheet(isPresented: $showingAreaDetailSheet) {
            AreaDetailScreen(
                areaId: $selectedAreaId,
                onClose: {
                    showingAreaDetailSheet = false
                },
                onAreaChanged: { areaId in
                    viewModel.dispatch(action: TaskDetailActionAreaChanged())
                }
            )
        }
        
    }
}

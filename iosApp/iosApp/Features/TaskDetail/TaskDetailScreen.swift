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
                                isShortInfo: state.task!.isShortInfo,
                                isFavorite: state.task!.isFavorite,
                                isFavoriteLoading: state.task!.isFavoriteLoading,
                                onFavoriteClick: {
                                    viewModel.dispatch(action: TaskDetailActionFavoriteClick())
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
                            
                            if !state.task!.isShortInfo {
                                ForEach(state.task!.abilities, id: \.self.id ) { ability in
                                    AbilityData(
                                        item: ability,
                                        onClick: { value in
                                            
                                        }
                                    )
                                }
                                
                                Text(SharedR.strings().task_mask_as.desc().localized().uppercased())
                                    .style(.labelMedium)
                                    .foregroundColor(.textPrimary)
                                    .padding(.top, 24)
                                
                                HStack(spacing: 0) {
                                    Text(SharedR.strings().task_not_interesting_title.desc().localized())
                                        .style(.labelLarge)
                                        .foregroundColor(state.task!.isNotInteresting ? .textLight : .textPrimary)
                                        .padding(.horizontal, 16)
                                        .padding(.vertical, 8)
                                        .background(
                                            RoundedRectangle(cornerRadius: 12)
                                                .fill(state.task!.isNotInteresting ? Color.buttonGradientStart : Color.backgroundItem)
                                                .shadow(radius: state.task!.isNotInteresting ? 8 : 0)
                                        )
                                        .padding(.trailing, 6)
                                        .onTapGesture {
                                            viewModel.dispatch(action: TaskDetailActionNotInterestingClick())
                                        }
                                    
                                    Text(SharedR.strings().task_completed_title.desc().localized())
                                        .style(.labelLarge)
                                        .foregroundColor(state.task!.isCompleted ? .textLight : .textPrimary)
                                        .padding(.horizontal, 16)
                                        .padding(.vertical, 8)
                                        .background(
                                            RoundedRectangle(cornerRadius: 12)
                                                .fill(state.task!.isCompleted ? Color.buttonGradientStart : Color.backgroundItem)
                                                .shadow(radius: state.task!.isCompleted ? 8 : 0)
                                        )
                                        .padding(.leading, 6)
                                        .onTapGesture {
                                            viewModel.dispatch(action: TaskDetailActionCompletedClick())
                                        }
                                }.padding(.top, 12)
                            } else {
                                AbilityDataHidden()
                            }
                            
                            
                            Spacer().frame(height: 140)
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

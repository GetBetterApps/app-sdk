//
//  NoteDetailScreen.swift
//  iosApp
//
//  Created by velkonost on 03.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct NoteDetailScreen : View {
    
    @StateViewModel var viewModel: NoteDetailViewModel
    
    @State private var confirmDeleteNoteDialog = false
    @State private var isSubNotesBlockVisible = false
    
    @State private var selectedAreaId: Int32? = nil
    @State private var showingAreaDetailSheet = false
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! NoteDetailViewState
        
        ZStack {
            Color.mainBackground
            
            if state.isLoading {
                VStack {
                    Loader()
                }
            } else {
                ScrollView(.vertical, showsIndicators: false) {
                    NoteDetailHeader(
                        noteType: state.noteType,
                        isNotePrivate: state.isNotePrivate
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
                    
                }
//                .ignoresSafeArea(.all)
                .padding(.bottom, 40)
                .padding(.horizontal, 20)
            }
        }
        .sheet(isPresented: $showingAreaDetailSheet) {
            AreaDetailScreen(
                areaId: $selectedAreaId,
                onClose: {
                    showingAreaDetailSheet = false
                },
                onAreaChanged: { areaId in
                    viewModel.dispatch(action: NoteDetailActionAreaChanged())
                }
            )
        }
    }
}

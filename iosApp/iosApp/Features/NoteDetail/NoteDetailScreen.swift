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
                    
                    
                }
//                .ignoresSafeArea(.all)
                .padding(.bottom, 40)
                .padding(.horizontal, 20)
            }
        }
    }
}

//
//  CreateNewNoteBottomSheet.swift
//  iosApp
//
//  Created by velkonost on 12.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import SwiftUIPager

struct AreaWrapper : Identifiable, Equatable, Hashable {
    var id: String = UUID().uuidString
    
    var area: Area
}

struct CreateNewNoteBottomSheet: View {
    
    let isLoading: Bool
    let areas: [Area]
    
    @State private var isAreaPickerVisible = false
    @State var currentAreaIndex: Int = 0
    @StateObject var areaPage: Page = .first()
    
    let items: [String] = ["1", "2", "3"]
    
    init(isLoading: Bool, areas: [Area]) {
        self.isLoading = isLoading
        self.areas = areas
    }
    
    var body: some View {
        ZStack {
            Color.mainBackground
            
            VStack {
                if isLoading {
                    Loader()
                } else {
                    Text(SharedR.strings().diary_areas_create_new_area_title.desc().localized())
                        .style(.headlineSmall)
                        .foregroundColor(.textTitle)
                        .frame(alignment: .center)
                    
                    PrimaryBox(
                        padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero)
                    ) {
                        VStack {
                            HStack {
                                Image(uiImage: SharedR.images().emoji_1.toUIImage()!)
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 32, height: 32)
                                
                                Text("selected area name")
                                    .style(.titleMedium)
                                    .foregroundColor(.textPrimary)
                                    .padding(.leading, 12)
                            }
                            .padding(16)
                            .frame(minWidth: 0, maxWidth: .infinity)
                            .onTapGesture {
                                withAnimation {
                                    isAreaPickerVisible.toggle()
                                }
                            }
                            
                            if isAreaPickerVisible {
                                Pager(
                                    page: areaPage,
                                    data: areas.map(
                                        { area in AreaWrapper(area: area) }
                                    ),
                                    id: \.self.area.id
                                ) { areaWrapper in
                                    
                                    ZStack {
                                        VStack {
                                            Image(uiImage: Emoji.companion.getIconById(id: areaWrapper.area.emojiId as! Int32).toUIImage()!)
                                                .resizable()
                                                .scaledToFit()
                                                .frame(width: 64, height: 64)
                                            
                                            Text(areaWrapper.area.name)
                                                .style(.titleLarge)
                                                .foregroundColor(.textPrimary)
                                                .padding(.top, 12)
                                        }
                                    }
                                    .frame(minWidth: 0, maxWidth: .infinity)
                                    .padding(16)
                                    .background(
                                        RoundedRectangle(cornerRadius: 12)
                                            .fill(Color.textFieldBackground)
                                    )
                                }
                                .interactive(rotation: true)
                                .interactive(scale: 0.8)
                                .alignment(.center)
                                .preferredItemSize(CGSize(width: 300, height: 150))
                                .frame(height: 150)
                                .padding(.bottom, 16)
                            }
                        }
                    }
                    Spacer()
                }
                
            }
            .padding(20)
        }
        .ignoresSafeArea(.all)
    }
}

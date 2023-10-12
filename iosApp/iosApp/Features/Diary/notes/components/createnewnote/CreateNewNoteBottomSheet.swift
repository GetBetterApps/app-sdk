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

struct AreaWrapper : Identifiable {
    var id: String = UUID().uuidString
    
    var area: Area
}

struct CreateNewNoteBottomSheet: View {
    
    let isLoading: Bool
    let areas: [Area]
    
    @State private var isAreaPickerVisible = false
    @State var currentAreaIndex: Int = 0
    
    
    let rows = [
        GridItem(.adaptive(minimum: 80))
    ]
    
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
                                ZStack {
                                    Carousel(
                                        index: $currentAreaIndex,
                                        items: areas.map(
                                            { area in AreaWrapper(area: area) }
                                        )
                                    ) { item in
                                        ZStack {
                                            VStack {
                                                Image(uiImage: Emoji.companion.getIconById(id: item.area.emojiId as! Int32).toUIImage()!)
                                                    .resizable()
                                                    .scaledToFit()
                                                    .frame(width: 64, height: 64)
                                                
                                                Text(item.area.name)
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
                                    .frame(width: UIScreen.main.bounds.width - 80, height: 150)
                                    
                                    .padding(.bottom, 16)
                                }
                                .frame(maxWidth: 150)
                                
                                
                            }
                        }
                    }
                    
                    .frame(width: UIScreen.main.bounds.width - 40)
                    
                    
                    Spacer()
                }
                
            }
            .padding(20)
        }
        .ignoresSafeArea(.all)
    }
}

struct Carousel<Content: View, T: Identifiable> : View {
    var content: (T) -> Content
    var list: [T]
    
    var spacing: CGFloat
    var trailingSpace: CGFloat
    @Binding var index: Int
    
    init(spacing: CGFloat = 15, trailingSpace: CGFloat = 100, index: Binding<Int>, items: [T], @ViewBuilder content: @escaping (T) -> Content) {
        
        self.list = items
        self.spacing = spacing
        self.trailingSpace = trailingSpace
        self._index = index
        self.content = content
    }
    
    @GestureState var offset: CGFloat = 0
    @State var currentIndex: Int = 0
    
    var body: some View {
        
        GeometryReader { proxy in
            
            let width = proxy.size.width - (trailingSpace - spacing)
            let adjustMentWidth = (trailingSpace / 2) - spacing
            
            HStack(spacing: spacing) {
                ForEach(list) { item in
                    content(item)
                        .frame(width: proxy.size.width - trailingSpace, height: 150)
                }
            }
            .padding(.horizontal, spacing)
            .offset(x: (CGFloat(currentIndex) * -width) + (currentIndex != 0 ? adjustMentWidth : 0) + offset)
            
            .gesture(
                DragGesture()
                    .updating($offset, body: { value, out, _ in
                        out = value.translation.width
                    })
                    .onEnded({ value in
                        let offsetX = value.translation.width
                        let progress = -offsetX / width
                        let roundIndex = progress.rounded()
                        currentIndex = max(min(currentIndex + Int(roundIndex), list.count - 1), 0)
                        currentIndex = index
                    })
                    .onChanged({ value in
                        let offsetX = value.translation.width
                        let progress = -offsetX / width
                        let roundIndex = progress.rounded()
                        index = max(min(currentIndex + Int(roundIndex), list.count - 1), 0)
                    })
            )
            
        }
        .animation(.easeInOut, value: offset == 0)
    }
}

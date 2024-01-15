//
//  AreaPicker.swift
//  iosApp
//
//  Created by velkonost on 25.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import SwiftUIPager

struct AreaPicker: View {
    
    let areas: [Area]
    private var selectedArea: Area?
    private var forceSelectedArea: Area?
    
    let noteType: NoteType
    let onAreaSelect: (Area) -> Void
    @Binding private var isAreaPickerVisible: Bool
    
    @StateObject var areaPage: Page = .first()
    
    init(areas: [Area], forceSelectedArea: Area?, selectedArea: Area?, noteType: NoteType, onAreaSelect: @escaping (Area) -> Void, isAreaPickerVisible: Binding<Bool>) {
        self.areas = areas
        self.selectedArea = selectedArea
        self.forceSelectedArea = forceSelectedArea
        self.noteType = noteType
        self.onAreaSelect = onAreaSelect
        self._isAreaPickerVisible = isAreaPickerVisible
        
    }
    
    var body: some View {
        PrimaryBox(
            padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero)
        ) {
            VStack {
                AreaPickerHeader(
                    selectedArea: selectedArea,
                    noteType: noteType,
                    isAreaPickerVisible: isAreaPickerVisible
                ) {
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
                        AreaPickerItem(area: areaWrapper.area)
                    }
                    .interactive(rotation: true)
                    .interactive(scale: 0.8)
                    .alignment(.center)
                    .onPageChanged({ page in
                        onAreaSelect(areas[page])
                    })
                    .preferredItemSize(CGSize(width: 280, height: 140))
                    .frame(height: 150)
                    .padding(.bottom, 16)
                }
            }
            .onAppear {
                if forceSelectedArea != nil {
                    let index = areas.firstIndex(where: { area in
                        area.id == forceSelectedArea!.id
                    })
                    
                    if index != nil {
                        areaPage.update(.new(index: index!))
                    }
                }
            }
        }
    }
}

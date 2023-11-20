//
//  CalendarsScreen.swift
//  iosApp
//
//  Created by velkonost on 21.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct CalendarsScreen: View {
    
    @StateViewModel var viewModel: CalendarsViewModel
    @State private var scrollTarget: Int64?
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! CalendarsViewState
        
        VStack(spacing: 0) {
            if state.datesState.selectedDate != nil {
                VStack(spacing: 0) {
                    HStack {
                        Text(state.datesState.selectedDate!.monthDay.localized().capitalized)
                            .style(.headlineSmall)
                            .foregroundColor(.textSecondaryTitle)
                        Spacer()
                    }
                    
                    HStack {
                        Text(state.datesState.selectedDate!.year.localized())
                            .style(.bodyLarge)
                            .foregroundColor(.textPrimary)
                            .padding(.top, 6)
                        Spacer()
                    }
                }
                .padding(.horizontal, 16)
                .animation(.easeInOut, value: state.datesState.selectedDate)
            }
            
            ScrollViewReader { view in
                ScrollView(.horizontal, showsIndicators: false) {
                    LazyHStack {
                        ForEach(state.datesState.items, id: \.self.id) { item in
                            CalendarDateItem(
                                item: item,
                                isSelected: item.id == state.datesState.selectedDate?.id,
                                onClick: { value in
                                    viewModel.dispatch(action: CalendarsActionDateClick(id: value))
                                }
                            )
                            .id(item.id)
                            
                        }
                    }
                    .onChange(of: scrollTarget) { target in
                        if let target = target {
                            scrollTarget = nil
                            
                            withAnimation {
                                view.scrollTo(target, anchor: .center)
                            }
                        }
                    }
                }
            }
            .onChange(of: state.datesState.selectedDate) { value in
                if value != nil {
                    scrollTarget = value!.id
                }
            }
            
            Spacer().frame(maxHeight: .infinity)
        }
        .frame(maxHeight: .infinity)
        
    }
}

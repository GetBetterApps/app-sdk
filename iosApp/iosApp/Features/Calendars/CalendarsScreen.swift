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
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! CalendarsViewState
        
        VStack {
            if state.datesState.selectedDate != nil {
                VStack(spacing: 0) {
                    Text(state.datesState.selectedDate!.monthDay.localized().capitalized)
                        .style(.headlineSmall)
                        .foregroundColor(.textSecondaryTitle)
                    
                    Text(state.datesState.selectedDate!.year.localized())
                        .style(.bodyLarge)
                        .foregroundColor(.textPrimary)
                        .padding(.top, 6)
                }.animation(.easeInOut, value: state.datesState.selectedDate)
            }
            
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
                    }
                }
            }
        }
    }
}

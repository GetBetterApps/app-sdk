//
//  CompletionDateBlock.swift
//  iosApp
//
//  Created by velkonost on 28.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct CompletionDateBlock: View {
    
    @State private var showDate: Bool = false
    @State private var hidenDate: Date = Date()

    @Binding var date: Date?
    
    init() {
        _date = Binding<Date?>(get: {
            Date.now
        }, set: { value in
            
        })
        
    }
    
    var body: some View {
        PrimaryBox(
            padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero)
        ) {
            HStack {
                Text(SharedR.strings().create_note_completion_date_title.desc().localized())
                    .style(.titleMedium)
                    .multilineTextAlignment(.leading)
                    .foregroundColor(.textPrimary)
                
                Spacer()
                
                if showDate {
                    Button {
                        showDate = false
                        date = nil
                    } label: {
                        Image(systemName: "xmark.circle")
                            .resizable()
                            .frame(width: 16, height: 16)
                            .tint(.textPrimary)
                    }
                    
                    DatePicker(
                        SharedR.strings().create_note_completion_date_title.desc().localized(),
                        selection: $hidenDate,
                        in: Date()...,
                        displayedComponents: .date
                        
                    )
                    .labelsHidden()
                    .onChange(of: hidenDate) { newDate in
                        date = newDate
                    }
                    
                } else {
                    Button {
                        showDate = true
                        date = hidenDate
                    } label: {
                        Text(SharedR.strings().create_note_completion_date_hint.desc().localized())
                            .multilineTextAlignment(.center)
                            .foregroundColor(.textPrimary)
                    }
                    .frame(width: 120, height: 34)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .fill(Color.mainBackground)
                    )
                    .multilineTextAlignment(.trailing)
                }
            }
            .padding(.trailing, 16)
            .padding(.leading, 16)
            .frame(height: 60)
        }
    }
}


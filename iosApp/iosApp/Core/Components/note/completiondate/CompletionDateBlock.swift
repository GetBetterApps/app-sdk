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
    
    let enabled: Bool
    let isLoading: Bool
    let initialValue: Int64?
    let initialValueStr: String?
    let isCompleteVisible: Bool
    let completionDateStr: String?

    @Binding var date: Date?
    
    let onSetCompletionDate: (Int64?) -> Void
    let onCompleteClick: (() -> Void)?
    
    init(
        enabled: Bool = true,
        isLoading: Bool = false,
        initialValue: Int64? = nil,
        initialValueStr: String? = nil,
        isCompleteVisible: Bool = false,
        completionDateStr: String? = nil,
        onSetCompletionDate: @escaping (Int64?) -> Void,
        onCompleteClick: (() -> Void)? = nil
    ) {
        self.enabled = enabled
        self.isLoading = isLoading
        self.initialValue = initialValue
        self.initialValueStr = initialValueStr
        self.isCompleteVisible = isCompleteVisible
        self.completionDateStr = completionDateStr
        self.onSetCompletionDate = onSetCompletionDate
        self.onCompleteClick = onCompleteClick
        
        _date = Binding<Date?>(get: { 
            initialValue != nil ? Date(milliseconds: initialValue!) : Date.now
        }, set: { value in })
        
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
                
                if showDate && enabled {
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
                        onSetCompletionDate(Int64((newDate.timeIntervalSince1970 * 1000.0).rounded()))
                    }
                    
                } else {
                    Button {
                        if enabled {
                            showDate = true
                            date = hidenDate
                        }
                    } label: {
                        Text(
                            initialValueStr != nil ? initialValueStr! :
                            SharedR.strings().create_note_completion_date_hint.desc().localized()
                        )
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


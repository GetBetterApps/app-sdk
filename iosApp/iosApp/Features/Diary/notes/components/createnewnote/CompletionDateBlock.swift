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
    @Binding var date: Date?

    
    var body: some View {
        ZStack {
            HStack {
                Text(label)
                    .multilineTextAlignment(.leading)
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
                        label,
                        selection: $hidenDate,
                        in: range,
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
                        Text(prompt)
                            .multilineTextAlignment(.center)
                            .foregroundColor(.textPrimary)
                    }
                    .frame(width: 120, height: 34)
                    .background(
                        RoundedRectangle(cornerRadius: 8)
                            .fill(Color.customPickerBackground)
                    )
                    .multilineTextAlignment(.trailing)
                }
            }
        }
    }
}


//
//  NoteItemHeader.swift
//  iosApp
//
//  Created by velkonost on 29.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct NoteItemHeader: View {
    
    let areaName: String
    let taskName: String?
    let areaIcon: UIImage
    
    init(areaName: String, taskName: String? = nil, areaIcon: UIImage) {
        self.areaName = areaName
        self.taskName = taskName
        self.areaIcon = areaIcon
    }

    var body: some View {
        HStack {
            Image(uiImage: areaIcon)
                .resizable()
                .scaledToFit()
                .frame(width: 32, height: 32)
            
            VStack {
                Spacer()
                
                HStack {
                    Text(areaName)
                        .style(.titleMedium)
                        .foregroundColor(.textPrimary)
                        .lineLimit(1)
                    Spacer()
                }
                
                if taskName != nil {
                    HStack {
                        Text(taskName!)
                            .style(.bodySmall)
                            .foregroundColor(.iconInactive)
                            .lineLimit(1)
                        Spacer()
                    }
                }
                
                Spacer()
            }
            .padding(.leading, 6)
        }
    }
}

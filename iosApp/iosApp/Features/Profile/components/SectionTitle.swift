//
//  SectionTitle.swift
//  iosApp
//
//  Created by velkonost on 20.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct SectionTitle: View {
    let text: String
    
    init(text: String) {
        self.text = text
    }
    
    var body: some View {
        HStack {
            Text(text.uppercased())
                .foregroundColor(.textSecondaryTitle)
                .style(.titleSmall)
                .padding(.init(top: 48, leading: .zero, bottom: .zero, trailing: .zero))
            Spacer()
        }
        
    }
}

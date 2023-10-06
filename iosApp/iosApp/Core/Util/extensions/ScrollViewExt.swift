//
//  ScrollViewExt.swift
//  iosApp
//
//  Created by velkonost on 06.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension ScrollView {
    func fadingEdge() -> some View {
        return self.mask(LinearGradient(gradient: Gradient(stops: [
            .init(color: .clear, location: 0),
            .init(color: .black, location: 0.1),
            .init(color: .black, location: 0.75),
            .init(color: .clear, location: 1)
        ]), startPoint: .top, endPoint: .bottom))
    }
}

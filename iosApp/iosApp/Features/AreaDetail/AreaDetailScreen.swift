//
//  AreaDetailScreen.swift
//  iosApp
//
//  Created by velkonost on 07.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct AreaDetailScreen: View {
    
    @StateObject var viewModel = AreaDetailVIewModelDelegate()
    let areaId: String
    
    init(areaId: String) {
        self.areaId = areaId
    }
    
    var body: some View {
        
        ZStack {
            Loader()
        }
    }
}

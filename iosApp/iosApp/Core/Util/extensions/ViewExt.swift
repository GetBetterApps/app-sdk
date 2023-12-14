//
//  ViewExt.swift
//  iosApp
//
//  Created by velkonost on 10.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension View {
    func endTextEditing() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder),
                                        to: nil, from: nil, for: nil)
    }
    
    func placeholder<Content: View>(
            when shouldShow: Bool,
            alignment: Alignment = .leading,
            @ViewBuilder placeholder: () -> Content) -> some View {

            ZStack(alignment: alignment) {
                placeholder().opacity(shouldShow ? 1 : 0)
                self
            }
        }
    
    func snapshot() -> UIImage? {
           let controller = UIHostingController(rootView: self)
           let view = controller.view

           let targetSize = controller.view.intrinsicContentSize
           view?.bounds = CGRect(origin: .zero, size: targetSize)
           view?.backgroundColor = .clear

           let renderer = UIGraphicsImageRenderer(size: targetSize)

           return renderer.image { _ in
               view?.drawHierarchy(in: controller.view.bounds, afterScreenUpdates: true)
           }
       }
}

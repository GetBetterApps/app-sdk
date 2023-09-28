//
//  TextExt.swift
//  iosApp
//
//  Created by velkonost on 10.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

public extension Text {
    func style(_ style: TextStyle, withSize size: CGFloat = CGFloat.nan) -> some View {
        return self
            .font(Font(style.font.uiFont(withSize: !size.isNaN ? size : style.size)))
            .tracking(style.tracking)
            .lineSpacing(style.lineSpacing)
    }
}


public extension TextField {
    func style(_ style: TextStyle, withSize size: CGFloat = CGFloat.nan) -> some View {
        return self
            .font(Font(style.font.uiFont(withSize: !size.isNaN ? size : style.size)))
//            .tracking(style.tracking)
//            .lineSpacing(style.lineSpacing)
    }
}

public extension TextEditor {
    func style(_ style: TextStyle, withSize size: CGFloat = CGFloat.nan) -> some View {
        return self
            .font(Font(style.font.uiFont(withSize: !size.isNaN ? size : style.size)))
//            .tracking(style.tracking)
//            .lineSpacing(style.lineSpacing)
    }
}


public extension SecureField {
    func style(_ style: TextStyle, withSize size: CGFloat = CGFloat.nan) -> some View {
        return self
            .font(Font(style.font.uiFont(withSize: !size.isNaN ? size : style.size)))
//            .tracking(style.tracking)
//            .lineSpacing(style.lineSpacing)
    }
}

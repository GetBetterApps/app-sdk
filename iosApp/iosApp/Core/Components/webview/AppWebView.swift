//
//  AppWebView.swift
//  iosApp
//
//  Created by velkonost on 11.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import WebKit

struct AppWebView: UIViewRepresentable {
    // 1
    let link: Binding<String>

    
    // 2
    func makeUIView(context: Context) -> WKWebView {

        return WKWebView()
    }
    
    // 3
    func updateUIView(_ webView: WKWebView, context: Context) {

        let request = URLRequest(url: URL(string: link.wrappedValue)!)
        webView.load(request)
    }
}

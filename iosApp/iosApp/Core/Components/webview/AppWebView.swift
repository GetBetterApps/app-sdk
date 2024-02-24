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
    @Binding var isVisible: Bool

    
    // 2
    func makeUIView(context: Context) -> WKWebView {
        let webView = WKWebView()
        webView.navigationDelegate = context.coordinator
        
        return webView
    }
    
    // 3
    func updateUIView(_ webView: WKWebView, context: Context) {

        let request = URLRequest(url: URL(string: link.wrappedValue)!)
        webView.load(request)
    }
    
    
    func makeCoordinator() -> WebViewCoordinator {
        WebViewCoordinator(self)
    }
    
    class WebViewCoordinator: NSObject, WKNavigationDelegate {
           var parent: AppWebView
           
           init(_ parent: AppWebView) {
               self.parent = parent
           }
           
           func webView(_ webView: WKWebView, decidePolicyFor navigationAction: WKNavigationAction, decisionHandler: @escaping (WKNavigationActionPolicy) -> Void) {
               let urlForClose = "yookassaReturn"
               if let urlStr = navigationAction.request.url?.absoluteString, urlStr.contains(urlForClose) {
                   parent.isVisible = false
               }
               decisionHandler(.allow)
           }
           
       }
    
}

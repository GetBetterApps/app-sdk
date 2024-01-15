//
//  SnackBar.swift
//  iosApp
//
//  Created by velkonost on 15.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK


struct Snackbar: View {
    
    @Binding var isShowing: Bool
    private let presenting: AnyView
    
    private let text: String
    private let snackBar: MessageType.SnackBar?
    
    @State private var isActionTapped: Bool?
    private var isBeingDismissedByAction: Bool {
        snackBar?.actionLabel != nil
    }
    
    init<Presenting>(
        isShowing: Binding<Bool>,
        presenting: Presenting,
        text: String,
        snackBar: MessageType.SnackBar?
    ) where Presenting: View {
        self._isShowing = isShowing
        self.presenting = AnyView(presenting)
        self.text = text
        self.snackBar = snackBar
    }
    
    var body: some View {
        GeometryReader { proxy in
            ZStack(alignment: .center) {
                self.presenting
                VStack {
                    Spacer()
                    if self.isShowing {
                        HStack {
                            Text(self.text)
                                .style(.bodyMedium)
                                .foregroundColor(.textLight)
                            
                            Spacer()
                            
                            if let action = self.snackBar?.actionLabel {
                                Text(action)
                                    .style(.titleMedium)
                                    .foregroundColor(.textLight)
                                    .onTapGesture {
                                        self.isActionTapped = true
                                        self.snackBar?.onAction()
                                        withAnimation {
                                            self.isShowing = false
                                        }
                                    }
                            }
                        }
                        .padding()
                        .background(
                            RoundedRectangle(cornerRadius: 12)
                                .fill(Color.buttonGradientStart)
                                .shadow(radius: 8)
                            
                        )
//                        .shadow(radius: 8)
//                        .background(Color.buttonGradientStart)
//                        .cornerRadius(12)
                        
                        .padding(.init(top: 16, leading: 16, bottom: 70, trailing: 16))
                        .transition(.move(edge: .bottom).combined(with: .opacity))
                        .animation(.default)
                        .onAppear {
                            guard !self.isBeingDismissedByAction else { return }
                            DispatchQueue.main.asyncAfter(deadline: .now() + 5) {
                                withAnimation {
                                    self.isShowing = false
                                }
                            }
                        }
                        .onDisappear {
                            guard !(self.isActionTapped ?? true) else { return }
                            DispatchQueue.main.asyncAfter(deadline: .now()) {
                                snackBar?.onDismiss()
                            }
                        }
                    }
                }
            }
        }
    }
}

extension View {
    func snackBar(
        isShowing: Binding<Bool>,
        text: String,
        snackBar: MessageType.SnackBar?
    ) -> some View {
        Snackbar(
            isShowing: isShowing,
            presenting: self,
            text: text,
            snackBar: snackBar
        )
    }
}


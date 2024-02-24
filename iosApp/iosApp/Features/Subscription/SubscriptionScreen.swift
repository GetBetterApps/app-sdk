//
//  SubscriptionScreen.swift
//  iosApp
//
//  Created by velkonost on 10.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync
import Lottie

struct SubscriptionScreen: View {
    
    @Environment(\.colorScheme) var colorScheme
    @Environment(\.presentationMode) var presentationMode: Binding<PresentationMode>
    
    @StateViewModel var viewModel: SubscriptionViewModel
    
    @State private var firstPointVisible: Bool = false
    @State private var secondPointVisible: Bool = false
    @State private var thirdPointVisible: Bool = false
    @State private var forthPointVisible: Bool = false
    @State private var fifthPointVisible: Bool = false
    @State private var sixthPointVisible: Bool = false
    
    @State private var logoVisible: Bool = false
    @State private var titleVisible: Bool = false
    @State private var subscriptionTextVisible: Bool = false
    @State private var buttonVisible: Bool = false
    
    @State private var offersSheetVisible: Bool = false
    @State var offersSheetHeight: CGFloat = .zero
    
    @State var webViewVisible: Bool = false
    @State var cancelAutoRenewDialogVisible: Bool = false
    
    var body: some View {
        @State var state = viewModel.viewStateValue as! SubscriptionViewState
        
        ZStack(alignment: .topLeading) {
            
            Color.mainBackground.ignoresSafeArea(.all)
            
            ScrollView(showsIndicators: false) {
                VStack(alignment: .leading, spacing: .zero) {
                    HStack {
                        Image(uiImage: SharedR.images().ic_close.toUIImage()!)
                            .resizable()
                            .renderingMode(.template)
                            .frame(width: 24, height: 24)
                            .foregroundColor(.iconActive)
                            .onTapGesture {
                                presentationMode.wrappedValue.dismiss()
                            }
                        
                        Spacer()
                        
                        //                        Text(SharedR.strings().paywall_restore.desc().localized())
                        //                            .style(.titleSmall)
                        //                            .foregroundColor(.textSecondaryTitle)
                    }
                    
                    if logoVisible {
                        Image(uiImage: colorScheme == .dark ? SharedR.images().ic_getbetter_light_.toUIImage()! : SharedR.images().ic_getbetter_dark_.toUIImage()!)
                            .resizable()
                            .scaledToFit()
                            .padding(.top, 16)
                            .opacity(0.8)
                    }
                    
                    Spacer().frame(height: 16)
                    
                    if titleVisible {
                        HStack {
                            Spacer()
                            Text(SharedR.strings().paywall_title.desc().localized())
                                .style(.headlineSmall)
                                .foregroundColor(.textTitle)
                            Spacer()
                        }
                    }
                    
                    Spacer().frame(height: 16)
                    
                    if firstPointVisible {
                        SubscriptionPoint(
                            title: SharedR.strings().paywall_point_1.desc().localized(),
                            index: 0
                        )
                    }
                    
                    if secondPointVisible {
                        SubscriptionPoint(
                            title: SharedR.strings().paywall_point_2.desc().localized(),
                            index: 1
                        )
                    }
                    
                    if thirdPointVisible {
                        SubscriptionPoint(
                            title: SharedR.strings().paywall_point_3.desc().localized(),
                            index: 2
                        )
                    }
                    
                    if forthPointVisible {
                        SubscriptionPoint(
                            title: SharedR.strings().paywall_point_4.desc().localized(),
                            index: 3
                        )
                    }
                    
                    if fifthPointVisible {
                        SubscriptionPoint(
                            title: SharedR.strings().paywall_point_5.desc().localized(),
                            index: 4
                        )
                    }
                    
                    if sixthPointVisible {
                        SubscriptionPoint(
                            title: SharedR.strings().paywall_point_6.desc().localized(),
                            index: 5
                        )
                    }
                    
//                    if state.subscription.isActive {
                        if subscriptionTextVisible && state.subscription.isActive  {
                            HStack {
                                Spacer()
                                
                                Text(state.subscription.expirationText.localized())
                                    .style(.bodyMedium)
                                    .foregroundColor(.textTitle)
                                    .multilineTextAlignment(.center)
                                
                                Spacer()
                            }
                            .padding(.top, 32)
                        }
//                    }
                    
                    
                    if state.subscription.cancelAutoRenewEnable || !state.subscription.isActive {
                        if buttonVisible {
                            HStack {
                                Spacer()
                                AppButton(
                                    labelText: !state.subscription.isActive ? SharedR.strings().continue_btn.desc().localized() : SharedR.strings().subscription_cancel_autorenew.desc().localized(),
                                    isLoading: state.isLoading,
                                    onClick: {
                                        if state.subscription.isActive {
                                            cancelAutoRenewDialogVisible = true
                                        } else {
                                            offersSheetVisible = true
                                        }
                                        
                                    }
                                )
                                Spacer()
                            }
                            .padding(.top, 32)
                        }
                    }
                    
                    if !state.subscription.isActive {
                        if buttonVisible {
                            Text(SharedR.strings().paywall_footer.desc().localized())
                                .style(.bodySmall, withSize: 11)
                                .foregroundColor(.textSecondaryTitle)
                                .multilineTextAlignment(.center)
                                .padding(.top, 24)
                        }
                    }
                    
                    Spacer().frame(height: 48)
                    
                }
                .padding(.init(top: 50, leading: 16, bottom: 0, trailing: 16))
            }
        }
        .edgesIgnoringSafeArea(.all)
        .sheet(isPresented: $offersSheetVisible) {
            OffersSheet(
                isLoading: state.isLoading,
                items: state.items,
                selectedItem: state.selectedItem,
                sheetHeight: $offersSheetHeight,
                itemClick: { value in
                    viewModel.dispatch(action: SubscriptionActionSubscriptionItemClick(value: value))
                },
                purchaseClick: {
                    viewModel.dispatch(action: SubscriptionActionSubscriptionPurchaseClick())
                }
            )
        }
        .sheet(isPresented: $webViewVisible) {
            AppWebView(
                link: Binding(get: { state.paymentUrl ?? "" }, set: { _ in}),
                isVisible: $webViewVisible
            )
            .ignoresSafeArea(.all)
        }
        .onAppear {
            withAnimation(.bouncy.delay(0.1)) {
                logoVisible = true
            }
            
            withAnimation(.bouncy.delay(0.2)) {
                titleVisible = true
            }
            
            withAnimation(.bouncy.delay(0.3)) {
                firstPointVisible = true
            }
            
            withAnimation(.bouncy.delay(0.4)) {
                secondPointVisible = true
            }
            
            withAnimation(.bouncy.delay(0.5)) {
                thirdPointVisible = true
            }
            
            withAnimation(.bouncy.delay(0.6)) {
                forthPointVisible = true
            }
            
            withAnimation(.bouncy.delay(0.7)) {
                fifthPointVisible = true
            }
            
            withAnimation(.bouncy.delay(0.8)) {
                sixthPointVisible = true
            }
            
            withAnimation(.bouncy.delay(0.9)) {
                            subscriptionTextVisible = true
                        }
            
            withAnimation(.bouncy.delay(1)) {
                buttonVisible = true
            }
        }
        .onChange(of: state.paymentUrl) { newValue in
            if newValue != nil && !newValue!.isEmpty {
                webViewVisible = true
            }
            
            offersSheetVisible = false
        }
        .onChange(of: webViewVisible) { newValue in
            if !webViewVisible {
                viewModel.dispatch(action: SubscriptionActionSubscriptionPurchaseProcessEnded())
            }
        }
    }
}

struct SubscriptionPoint: View {
    
    private let index: Int
    private let title: String
    
    init(title: String, index: Int) {
        self.title = title
        self.index = index
    }
    
    @State private var animVisible: Bool = false
    
    var body: some View {
        HStack(spacing: 0) {
            if animVisible {
                OnboardingLottieView(resource: SharedR.files().anim_mark.path)
                    .frame(width: 24, height: 24)
                    .scaleEffect(0.1)
            } else {
                Spacer().frame(width: 24, height: 24)
            }
            
            Text(title)
                .style(.labelMedium)
                .foregroundColor(.textTitle)
                .multilineTextAlignment(.leading)
                .padding(.leading, 8)
            
            Spacer()
            
        }
        .padding(.top, 16)
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.1 + 0.1 * Double(index)) {
                withAnimation(.bouncy) {
                    animVisible = true
                }
            }
            
        }
    }
}

struct SubscriptionLottieView: UIViewRepresentable {
    
    var resource: String
    
    var loopMode: LottieLoopMode = .playOnce
    
    func updateUIView(_ uiView: UIViewType, context: Context) {}
    
    func makeUIView(context: Context) -> Lottie.LottieAnimationView {
        let animationView = LottieAnimationView(filePath: resource)
        
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = loopMode
        animationView.play()
        
        return animationView
    }
}

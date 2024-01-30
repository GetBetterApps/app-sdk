//
//  AdView.swift
//  iosApp
//
//  Created by velkonost on 25.01.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK
import YandexMobileAds

struct AdView: View {
    
    var body: some View {
        PrimaryBox(
            padding: .init(top: .zero, leading: .zero, bottom: .zero, trailing: .zero)
        ) {
            VStack {
                Loader()
                Text(SharedR.strings().ad_title.desc().localized().capitalized)
                    .style(.headlineSmall)
                    .foregroundColor(.onboardingBackgroundGradientStart)
                    .padding(.top, 6)
            }
            .frame(height: 300)
            
            AdBannerView()
                .clipShape(.rect(cornerRadii: .init(topLeading: 12, bottomLeading: 12, bottomTrailing: 12, topTrailing: 12)))
            
        }
        .frame(height: 300)
    }
}

struct AdBannerView: UIViewControllerRepresentable {
    typealias UIViewControllerType = InlineBannerViewController
    
    func makeUIViewController(context: Context) -> InlineBannerViewController {
        let ad = InlineBannerViewController()
        ad.loadAd()
        return ad
    }
    
    func updateUIViewController(_ uiViewController: InlineBannerViewController, context: Context) {
        // Updates the state of the specified view controller with new information from SwiftUI.
    }
}

final class InlineBannerViewController: UIViewController {
    private lazy var adView: YMAAdView = {
        let adSize = YMABannerAdSize.inlineSize(withWidth: UIScreen.screenWidth - 40, maxHeight: 300)
        
        let adView = YMAAdView(adUnitID: UtilBuildKonfig.shared.AD_ID, adSize: adSize)
        adView.delegate = self
        adView.translatesAutoresizingMaskIntoConstraints = false
        return adView
    }()
    
    func loadAd() {
        adView.loadAd()
        view.addSubview(adView)
    }
}

extension InlineBannerViewController: YMAAdViewDelegate {
    func adViewDidLoad(_ adView: YMAAdView) {
        // This method will call after successfully loading
    }
    
    func adViewDidFailLoading(_ adView: YMAAdView, error: Error) {
        // This method will call after getting any error while loading the ad
    }
}

#ifdef __OBJC__
#import <UIKit/UIKit.h>
#else
#ifndef FOUNDATION_EXPORT
#if defined(__cplusplus)
#define FOUNDATION_EXPORT extern "C"
#else
#define FOUNDATION_EXPORT extern
#endif
#endif
#endif

#import "BigoAdLogger.h"
#import "BigoAdSize.h"
#import "BigoBannerAd.h"
#import "BigoBannerAdLoader.h"
#import "BigoBannerAdRequest.h"
#import "BigoInterstitialAd.h"
#import "BigoInterstitialAdLoader.h"
#import "BigoInterstitialAdRequest.h"
#import "BigoInterstitialNativeAd.h"
#import "BigoNativeBannerAdLoader.h"
#import "BigoNativeBannerAdRequest.h"
#import "BigoNativeAd.h"
#import "BigoNativeAdLoader.h"
#import "BigoNativeAdRequest.h"
#import "BigoRewardVideoAd.h"
#import "BigoRewardVideoAdInteractionDelegate.h"
#import "BigoRewardVideoAdLoader.h"
#import "BigoRewardVideoAdRequest.h"
#import "BigoSplashAd.h"
#import "BigoSplashAdLoader.h"
#import "BigoSplashAdRequest.h"
#import "BigoRequest.h"
#import "BGAdLogDelegate.h"
#import "BigoADS.h"
#import "BigoAdSdk.h"
#import "BigoConsentOptions.h"
#import "BigoUrl.h"
#import "BigoAdComponentView.h"
#import "BigoAdMediaView.h"
#import "BigoAdOptionsView.h"
#import "UIView+BigoNativeAdViewTag.h"
#import "BGAdBid.h"
#import "BigoAd.h"
#import "BigoAdConfig.h"
#import "BigoAdError.h"
#import "BigoAdErrorCode.h"
#import "BigoAdGender.h"
#import "BigoAdInnerErrorCode.h"
#import "BigoAdInteractionDelegate.h"
#import "BigoAdLoader.h"
#import "BigoAdRequest.h"
#import "BigoVideoController.h"

FOUNDATION_EXPORT double BigoADSVersionNumber;
FOUNDATION_EXPORT const unsigned char BigoADSVersionString[];


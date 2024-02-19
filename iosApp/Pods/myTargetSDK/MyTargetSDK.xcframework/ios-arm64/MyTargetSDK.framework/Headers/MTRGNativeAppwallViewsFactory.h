//
//  MTRGNativeAppwallViewsFactory.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 4/12/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

@class MTRGAppwallAdView;
@class MTRGNativeAppwallBanner;
@class MTRGAppwallBannerAdView;
@protocol MTRGAppwallBannerAdViewDelegate;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class-factory for native appwall views.
 */
@interface MTRGNativeAppwallViewsFactory : NSObject

/**
 @discussion Factory for appwall banner ad view for appwall banner.
 
 @param appwallBanner Instance of MTRGNativeAppwallBanner.
 @param delegate Instance conforms protocol MTRGAppwallBannerAdViewDelegate.
 
 @return Instance of MTRGAppwallBannerAdView.
 */
+ (MTRGAppwallBannerAdView *)bannerViewWithBanner:(MTRGNativeAppwallBanner *)appwallBanner
                                         delegate:(nullable id

<MTRGAppwallBannerAdViewDelegate>)
delegate;

/**
 @discussion Factory for appwall banner view without banner.
 
 @param delegate Instance conforms protocol MTRGAppwallBannerAdViewDelegate.
 
 @return Instance of MTRGAppwallBannerAdView.
 */
+ (MTRGAppwallBannerAdView *)bannerViewWithDelegate:(nullable id

<MTRGAppwallBannerAdViewDelegate>)
delegate;

/**
 @discussion Factory for app wall ad view with array of banners.
 
 @param banners Array of MTRGNativeAppwallBanner instances.
 
 @return Instance of MTRGAppwallAdView.
 */
+ (MTRGAppwallAdView *)appwallAdViewWithBanners:(NSArray

<MTRGNativeAppwallBanner *> *)
banners;

@end

NS_ASSUME_NONNULL_END

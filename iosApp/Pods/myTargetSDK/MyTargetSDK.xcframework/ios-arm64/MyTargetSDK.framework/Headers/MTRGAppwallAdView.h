//
//  MTRGAppwallAdView.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 4/12/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

@class MTRGNativeAppwallBanner;

NS_ASSUME_NONNULL_BEGIN

@protocol MTRGAppwallAdViewDelegate <NSObject>

- (void)appwallAdViewOnClickWithBanner:(MTRGNativeAppwallBanner *)banner;

- (void)appwallAdViewOnSlideToBanners:(NSArray

<MTRGNativeAppwallBanner *> *)
banners;

@end

/**
 @discussion Class for Appwall ad view
 */
@interface MTRGAppwallAdView : UIView

/**
 @discussion Delegate for MTRGAppwallAdView. Must implements MTRGAppwallAdViewDelegate protocol.
 */
@property(nonatomic, weak, nullable) id <MTRGAppwallAdViewDelegate> delegate;

/**
 @discussion Static constructor of MTRGAppwallAdView.
 
 @param banners Array of banners.
 
 @return Instance of MTRGAppwallAdView.
 */
+ (instancetype)appwallAdViewWithBanners:(NSArray

<MTRGNativeAppwallBanner *> *)
banners;

- (instancetype)init

NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END

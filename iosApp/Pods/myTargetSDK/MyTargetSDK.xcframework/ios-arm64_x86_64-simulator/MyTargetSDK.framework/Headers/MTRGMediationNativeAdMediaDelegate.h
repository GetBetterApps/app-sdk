//
//  MTRGMediationNativeAdMediaDelegate.h
//  MyTargetSDK
//
//  Created by igor.sorokin on 17.10.2022.
//  Copyright © 2022 VK. All rights reserved.
//

@protocol MTRGMediationNativeAdAdapter;

/**
 @discussion Protocol for mediation of native ad media delegates.
 */
@protocol MTRGMediationNativeAdMediaDelegate <NSObject>

/**
 @discussion Calls when icon loaded for the ad.

 @param adapter Current adapter.
 */
- (void)onIconLoadWithAdapter:(id <MTRGMediationNativeAdAdapter>)adapter;

/**
 @discussion Calls when image loaded for the ad.

 @param adapter Current adapter.
 */
- (void)onImageLoadWithAdapter:(id <MTRGMediationNativeAdAdapter>)adapter;

/**
 @discussion Calls when adChoices image loaded for the ad.

 @param adapter Current adapter.
 */
- (void)onAdChoicesIconLoadWithAdapter:(id <MTRGMediationNativeAdAdapter>)adapter;

@end

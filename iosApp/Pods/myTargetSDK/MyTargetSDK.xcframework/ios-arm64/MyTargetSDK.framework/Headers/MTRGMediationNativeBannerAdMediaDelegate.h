//
//  MTRGMediationNativeBannerAdMediaDelegate.h
//  MyTargetSDK
//
//  Created by igor.sorokin on 17.10.2022.
//  Copyright © 2022 VK. All rights reserved.
//

@protocol MTRGMediationNativeBannerAdAdapter;

/**
 @discussion Native banner ad's media delegate protocol.
 */
@protocol MTRGMediationNativeBannerAdMediaDelegate <NSObject>

/**
 @discussion Call on load icon for native banner ad.

 @param adapter Current adapter.
 */
- (void)onIconLoadWithAdapter:(id <MTRGMediationNativeBannerAdAdapter>)adapter;

/**
 @discussion Calls when adChoices image loaded for the ad.

 @param adapter Current adapter.
 */
- (void)onAdChoicesIconLoadWithAdapter:(id <MTRGMediationNativeBannerAdAdapter>)adapter;

@end

//
//  MTRGMediationNativeBannerAdChoicesOptionDelegate.h
//  myTargetSDK 5.20.2
//
//  Created by igor.sorokin on 11.10.2022.
//  Copyright © 2022 VK. All rights reserved.
//

@protocol MTRGMediationNativeBannerAdAdapter;

/**
 @discussion Protocol for AdChoices close options in native ad mediation.
 */
@protocol MTRGMediationNativeBannerAdChoicesOptionDelegate <NSObject>

/**
 @discussion Calls when ad should close.
 */
- (BOOL)shouldCloseAutomatically:(id <MTRGMediationNativeBannerAdAdapter>)adapter;

/**
 @discussion Calls if `shouldCloseAutomatically` returns YES. Ad should close automatically.

 @param adapter Current adapter.
 */
- (void)onCloseAutomatically:(id <MTRGMediationNativeBannerAdAdapter>)adapter;

/**
 @discussion Calls if `shouldCloseAutomatically` returns NO. Ad should close manually.

 @param adapter Current adapter.
 */
- (void)closeIfAutomaticallyDisabled:(id <MTRGMediationNativeBannerAdAdapter>)adapter;

@end

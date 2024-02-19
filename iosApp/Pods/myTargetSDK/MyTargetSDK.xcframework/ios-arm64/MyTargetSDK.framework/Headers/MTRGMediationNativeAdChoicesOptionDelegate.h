//
//  MTRGMediationNativeAdChoicesOptionDelegate.h
//  MyTargetSDK
//
//  Created by igor.sorokin on 10.10.2022.
//  Copyright © 2022 VK. All rights reserved.
//

@protocol MTRGMediationNativeAdAdapter;

/**
 @discussion Protocol for AdChoices close options in native ad mediation.
 */
@protocol MTRGMediationNativeAdChoicesOptionDelegate <NSObject>

/**
 @discussion Calls when ad should close.
 */
- (BOOL)shouldCloseAutomatically:(id <MTRGMediationNativeAdAdapter>)adapter;

/**
 @discussion Calls if `shouldCloseAutomatically` returns YES. Ad should close automatically.

 @param adapter Current adapter.
 */
- (void)onCloseAutomatically:(id <MTRGMediationNativeAdAdapter>)adapter;

/**
 @discussion Calls if `shouldCloseAutomatically` returns NO. Ad should close manually.

 @param adapter Current adapter.
 */
- (void)closeIfAutomaticallyDisabled:(id <MTRGMediationNativeAdAdapter>)adapter;

@end

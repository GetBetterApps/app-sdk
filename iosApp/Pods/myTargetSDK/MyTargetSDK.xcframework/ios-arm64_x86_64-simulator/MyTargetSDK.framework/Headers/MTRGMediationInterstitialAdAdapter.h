//
//  MTRGMediationInterstitialAdAdapter.h
//  myTargetSDK 5.20.2
//
// Copyright (c) 2019 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MyTargetSDK/MTRGMediationAdapter.h>

@class MTRGMediationAdConfig;
@protocol MTRGMediationInterstitialAdAdapter;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Interstitial ad delegate's protocol.
 */
@protocol MTRGMediationInterstitialAdDelegate <NSObject>

/**
 @discussion Call on load adapter.
 
 @param adapter The adapter for mediation.
 */
- (void)onLoadWithAdapter:(id <MTRGMediationInterstitialAdAdapter>)adapter;

/**
 @discussion Calls if there is no ad.

 @param error An error code/description.
 @param adapter Current adapter.
 */
- (void)onLoadFailedWithError:(NSError *)error
                      adapter:(id <MTRGMediationInterstitialAdAdapter>)adapter NS_SWIFT_NAME

(
onLoadFailed(error
:adapter:));

/**
 @discussion Calls on click.
 
 @param adapter Current adapter.
 */
- (void)onClickWithAdapter:(id <MTRGMediationInterstitialAdAdapter>)adapter;

/**
 @discussion Calls on close the adapter.
 
 @param adapter Current adapter.
 */
- (void)onCloseWithAdapter:(id <MTRGMediationInterstitialAdAdapter>)adapter;

/**
 @discussion Calls on video complete for the adapter.
 
 @param adapter Current adapter.
 */
- (void)onVideoCompleteWithAdapter:(id <MTRGMediationInterstitialAdAdapter>)adapter;

/**
 @discussion Calls on display the ad by adapter.
 
 @param adapter Current adapter.
 */
- (void)onDisplayWithAdapter:(id <MTRGMediationInterstitialAdAdapter>)adapter;

/**
 @discussion Calls on leave the application while ad is showing.
 
 @param adapter Current adapter.
 */
- (void)onLeaveApplicationWithAdapter:(id <MTRGMediationInterstitialAdAdapter>)adapter;

@optional

/**
 @discussion Call if there is no ad.

 @param reason The reason why there is no ad.
 @param adapter Current adapter.
 */
- (void)onNoAdWithReason:(NSString *)reason
                 adapter:(id <MTRGMediationInterstitialAdAdapter>)adapter __attribute__((deprecated("use onLoadFailed method instead.")));

@end

/**
 @discussion Protocol for adapters.
 */
@protocol MTRGMediationInterstitialAdAdapter <MTRGMediationAdapter>

/**
 @discussion Delegate for the interstitial ad adapter. Must conforms MTRGMediationInterstitialAdDelegate.
 */
@property(nonatomic, weak, nullable) id <MTRGMediationInterstitialAdDelegate> delegate;

/**
 @discussion Loads adapter with ad configuration.
 
 @param mediationAdConfig Configuration for the adapter.
 */
- (void)loadWithMediationAdConfig:(MTRGMediationAdConfig *)mediationAdConfig;

/**
 @discussion Show on the controller.
 
 @param controller Controller to show.
 */
- (void)showWithController:(UIViewController *)controller;

/**
 @discussion Close the adapter.
 */
- (void)close;

@end

NS_ASSUME_NONNULL_END

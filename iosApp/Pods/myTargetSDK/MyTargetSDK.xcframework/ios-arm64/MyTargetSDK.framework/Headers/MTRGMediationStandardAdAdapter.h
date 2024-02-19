//
//  MTRGMediationStandardAdAdapter.h
//  myTargetSDK 5.20.2
//
// Copyright (c) 2019 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MyTargetSDK/MTRGMediationAdapter.h>
#import <MyTargetSDK/MTRGAdView.h>

@protocol MTRGMediationStandardAdAdapter;
@class MTRGMediationAdConfig;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Protocol for standard ad of mediation.
 */
@protocol MTRGMediationStandardAdDelegate <NSObject>

/**
 @discussion Calls on load adapter with ad view.
 
 @param adView View with ad.
 @param adapter Instance conforms protocol MTRGMediationStandedAdAdapter.
 */
- (void)onLoadWithAdView:(UIView *)adView
                 adapter:(id <MTRGMediationStandardAdAdapter>)adapter;

/**
 @discussion Calls if there is no ad.

 @param error An error code/description.
 @param adapter Current adapter.
 */
- (void)onLoadFailedWithError:(NSError *)error
                      adapter:(id <MTRGMediationStandardAdAdapter>)adapter NS_SWIFT_NAME

(
onLoadFailed(error
:adapter:));

/**
 @discussion Calls on click for adapter.
 
 @param adapter Adapter for which click was handled.
 */
- (void)onAdClickWithAdapter:(id <MTRGMediationStandardAdAdapter>)adapter;

/**
 @discussion Calls on ad show for adapter.
 
 @param adapter Adapter for which ad was showed.
 */
- (void)onAdShowWithAdapter:(id <MTRGMediationStandardAdAdapter>)adapter;

/**
 @discussion Calls on modal show.
 
 @param adapter Adapter for which calls the method on modal show.
 */
- (void)onShowModalWithAdapter:(id <MTRGMediationStandardAdAdapter>)adapter;

/**
 @discussion Calls on modal dismiss.
 
 @param adapter Adapter for which calls the method on modal dismiss.
 */
- (void)onDismissModalWithAdapter:(id <MTRGMediationStandardAdAdapter>)adapter;

/**
 @discussion Calls on leave application with adapter.
 
 @param adapter Adapter for which calls the method on leave.
 */
- (void)onLeaveApplicationWithAdapter:(id <MTRGMediationStandardAdAdapter>)adapter;

@optional

/**
 @discussion Calls when there is no ad for adapter.

 @param reason String with a reason.
 @param adapter Adapter which has no ad.
 */
- (void)onNoAdWithReason:(NSString *)reason
                 adapter:(id <MTRGMediationStandardAdAdapter>)adapter __attribute__((deprecated("use onLoadFailed method instead.")));

@end

/**
 @discussion Protocol for implementation of adapters
 */
@protocol MTRGMediationStandardAdAdapter <MTRGMediationAdapter>

/**
 @discussion Instance conforms MTRGMediationStandadAdDelegate protocol.
 */
@property(nonatomic, weak, nullable) id <MTRGMediationStandardAdDelegate> delegate;

/**
 @discussion Instance of UIViewController which used to adapter.
 */
@property(nonatomic, weak, nullable) UIViewController *viewController;

/**
 @discussion Method to load mediation with config.
 
 @param mediationAdConfig Mediation ad config. Instance of MTRGMediationAdConfig.
 @param adSize Size of ad. Instance of MTRGAdSize.
 */
- (void)loadWithMediationAdConfig:(MTRGMediationAdConfig *)mediationAdConfig adSize:(MTRGAdSize *)adSize;

@end

NS_ASSUME_NONNULL_END

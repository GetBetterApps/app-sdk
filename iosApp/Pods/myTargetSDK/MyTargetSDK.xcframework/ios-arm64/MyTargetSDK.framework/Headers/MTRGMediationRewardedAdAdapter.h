//
//  MTRGMediationRewardedAdAdapter.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 13.08.2020.
//  Copyright © 2020 Mail.ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MyTargetSDK/MTRGMediationAdapter.h>

@class MTRGReward;
@class MTRGMediationAdConfig;
@protocol MTRGMediationRewardedAdAdapter;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Delegate of rewarded ad.
 */
@protocol MTRGMediationRewardedAdDelegate <NSObject>

/**
 @discussion Calls on load the ad with adapter.
 
 @param adapter Current adapter.
 */
- (void)onLoadWithAdapter:(id <MTRGMediationRewardedAdAdapter>)adapter;

/**
 @discussion Calls if there is no ad.

 @param error An error code/description.
 @param adapter Current adapter.
 */
- (void)onLoadFailedWithError:(NSError *)error
                      adapter:(id <MTRGMediationRewardedAdAdapter>)adapter NS_SWIFT_NAME

(
onLoadFailed(error
:adapter:));

/**
 @discussion Calls on click.
 
 @param adapter Current adapter.
 */
- (void)onClickWithAdapter:(id <MTRGMediationRewardedAdAdapter>)adapter;

/**
 @discussion Calls on close the ad with adapter.
 
 @param adapter Current adapter.
 */
- (void)onCloseWithAdapter:(id <MTRGMediationRewardedAdAdapter>)adapter;

/**
 @discussion Calls on reward for the ad with adapter.
 
 @param reward Received reward.
 @param adapter Current adapter.
 */
- (void)onReward:(MTRGReward *)reward adapter:(id <MTRGMediationRewardedAdAdapter>)adapter;

/**
 @discussion Calls on display the ad with adapter.
 
 @param adapter Current adapter.
 */
- (void)onDisplayWithAdapter:(id <MTRGMediationRewardedAdAdapter>)adapter;

/**
 @discussion Calls on leave the application while ad is showing.
 
 @param adapter Current adapter.
 */
- (void)onLeaveApplicationWithAdapter:(id <MTRGMediationRewardedAdAdapter>)adapter;

@optional

/**
 @discussion Call if there is no ad in the adapter.

 @param reason The reason why there is no ad.
 @param adapter Current adapter.
 */
- (void)onNoAdWithReason:(NSString *)reason
                 adapter:(id <MTRGMediationRewardedAdAdapter>)adapter __attribute__((deprecated("use onLoadFailed method instead.")));

@end

/**
 @discussion Protocol for reward adapters.
 */
@protocol MTRGMediationRewardedAdAdapter <MTRGMediationAdapter>

/**
 @discussion Delegate for the adapter. Must conforms MTRGMediationRewardedAdDelegate protocol.
 */
@property(nonatomic, weak, nullable) id <MTRGMediationRewardedAdDelegate> delegate;

/**
 @discussion Loads adapter with mediation ad configuration.
 
 @param mediationAdConfig Configuration of the mediation ad.
 */
- (void)loadWithMediationAdConfig:(MTRGMediationAdConfig *)mediationAdConfig;

/**
 @discussion Show ad with controller.
 
 @param controller Current controller.
 */
- (void)showWithController:(UIViewController *)controller;

/**
 @discussion Closes the adapter.
 */
- (void)close;

@end

NS_ASSUME_NONNULL_END

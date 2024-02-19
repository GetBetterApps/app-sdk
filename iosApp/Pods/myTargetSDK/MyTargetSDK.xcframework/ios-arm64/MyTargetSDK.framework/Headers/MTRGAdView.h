//
//  MTRGAdView.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 3/22/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

@class MTRGAdView;
@class MTRGAdSize;
@class MTRGCustomParams;
@protocol MTRGAdNetworkConfigProtocol;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Protocol describes interface for MTRGAdView delegate.
 */
@protocol MTRGAdViewDelegate <NSObject>

/**
@discussion Calls on load the ad view. Required.

@param adView Loaded ad view.
*/
- (void)onLoadWithAdView:(MTRGAdView *)adView;

/**
 @discussion Calls if there is no ad.

 @param error An error code/description.
 @param adView Current ad view.
 */
- (void)onLoadFailedWithError:(NSError *)error adView:(MTRGAdView *)adView NS_SWIFT_NAME

(
onLoadFailed(error
:adView:));

@optional

/**
 @discussion Calls when there is no ad for ad view.

 @param reason String with a reason.
 @param adView Current ad view.
 */
- (void)onNoAdWithReason:(NSString *)reason adView:(MTRGAdView *)adView __attribute__((deprecated("use onLoadFailed method instead.")));

/**
 @discussion Calls on click.
 
 @param adView Instance of MTRGAdView which detects click.
 */
- (void)onAdClickWithAdView:(MTRGAdView *)adView;

/**
 @discussion Calls on show ad.
 
 @param adView Instance of MTRGAdView which shows ad.
 */
- (void)onAdShowWithAdView:(MTRGAdView *)adView;

/**
 @discussion Calls on modal show.
 
 @param adView Instance of MTRGAdView which shows modal.
 */
- (void)onShowModalWithAdView:(MTRGAdView *)adView;

/**
 @discussion Calls on modal dismiss.
 
 @param adView Instance of MTRGAdView which dismiss modal.
 */
- (void)onDismissModalWithAdView:(MTRGAdView *)adView;

/**
 @discussion Calls on leave application.
 
 @param adView Instance of MTRGAdView while leave application.
 */
- (void)onLeaveApplicationWithAdView:(MTRGAdView *)adView;

@end

/**
 @discussion Class describes view which contains ad.
 */
@interface MTRGAdView : UIView

/**
 @discussion Instance conforms MTRGAdViewDelegate protocol.
 */
@property(nonatomic, weak, nullable) id <MTRGAdViewDelegate> delegate;

/**
 @discussion Instance of UIViewController which hosts the view.
 */
@property(nonatomic, weak, nullable) UIViewController *viewController;

/**
 @discussion Custom parameters of the view.
 */
@property(nonatomic, readonly) MTRGCustomParams *customParams;

/**
 @discussion Flag determines mediation status.
 */
@property(nonatomic) BOOL mediationEnabled;

/**
 @discussion Ad size.
 */
@property(nonatomic) MTRGAdSize *adSize;

/**
 @discussion Slot identifier.
 */
@property(nonatomic, readonly) NSUInteger slotId;

/**
 @discussion Flag determines should ad be refreshed or not.
 */
@property(nonatomic, readonly) BOOL shouldRefreshAd;

/**
 @discussion String contains ad source.
 */
@property(nonatomic, readonly, nullable) NSString *adSource;

/**
 @discussion Ad source priority.
 */
@property(nonatomic, readonly) float adSourcePriority;

/**
@discussion Static constructor. Creates view with slot identifier.

@param slotId Slot identifier.
 
@return Instance of MTRGAdView.
*/
+ (instancetype)adViewWithSlotId:(NSUInteger)slotId;

/**
 @discussion Static constructor. Creates view with slot identifier and flag for ad refresh.
 
 @param slotId Slot identifier.
 @param shouldRefreshAd Flag determines should ad be refreshed or not.
 
 @return Instance of MTRGAdView.
 */
+ (instancetype)adViewWithSlotId:(NSUInteger)slotId shouldRefreshAd:(BOOL)shouldRefreshAd;

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

- (instancetype)initWithFrame:(CGRect)frame NS_UNAVAILABLE;

- (nullable instancetype)initWithCoder:(NSCoder *)coder NS_UNAVAILABLE;

/**
 @discussion Load the view.
 */
- (void)load;

/**
 @discussion Load the view with bid identifier.
 
 @param bidId Bid identifier.
 */
- (void)loadFromBid:(NSString *)bidId;

/**
 @discussion Additional parameters for mediation.

 @param adNetworkConfig An object implemented MTRGAdNetworkConfigProtocol.
 */
- (void)setAdNetworkConfig:(nullable id

<MTRGAdNetworkConfigProtocol>)
adNetworkConfig networkName
:(NSString *)
networkName;

@end

NS_ASSUME_NONNULL_END

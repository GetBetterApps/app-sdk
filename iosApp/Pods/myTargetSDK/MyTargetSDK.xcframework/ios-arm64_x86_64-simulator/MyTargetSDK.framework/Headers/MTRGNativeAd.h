//
//  MTRGNativeAd.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 2/1/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <MyTargetSDK/MTRGBaseAd.h>
#import <MyTargetSDK/MTRGNativeAdProtocol.h>

@class MTRGNativeAd;
@class MTRGNativePromoBanner;
@class MTRGImageData;
@protocol MTRGMenuFactory;
@protocol MTRGNativeAdChoicesOptionDelegate;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Delegate's protocol for the native ad.
 */
@protocol MTRGNativeAdDelegate <NSObject>

/**
 @discussion Calls on load native promo banner. (Required)
 
 @param promoBanner Loaded banner.
 @param nativeAd Current ad.
 */
- (void)onLoadWithNativePromoBanner:(MTRGNativePromoBanner *)promoBanner nativeAd:(MTRGNativeAd *)nativeAd;

/**
 @discussion Calls if there is no ad.

 @param error An error code/description.
 @param nativeAd Current ad.
 */
- (void)onLoadFailedWithError:(NSError *)error nativeAd:(MTRGNativeAd *)nativeAd NS_SWIFT_NAME

(
onLoadFailed(error
:nativeAd:));

@optional

/**
 @discussion Calls if there is no ad.

 @param reason The reason why there is no ad.
 @param nativeAd Current ad.
 */
- (void)onNoAdWithReason:(NSString *)reason nativeAd:(MTRGNativeAd *)nativeAd __attribute__((deprecated("use onLoadFailed method instead.")));

/**
 @discussion Class on show the native ad.
 
 @param nativeAd Current ad.
 */
- (void)onAdShowWithNativeAd:(MTRGNativeAd *)nativeAd;

/**
 @discussion Calls on click by native ad.
 
 @param nativeAd Current ad.
 */
- (void)onAdClickWithNativeAd:(MTRGNativeAd *)nativeAd;

/**
 @discussion Calls on modal show.
 
 @param nativeAd Current ad.
 */
- (void)onShowModalWithNativeAd:(MTRGNativeAd *)nativeAd;

/**
 @discussion Calls on modal dismiss.
 
 @param nativeAd Current ad.
 */
- (void)onDismissModalWithNativeAd:(MTRGNativeAd *)nativeAd;

/**
 @discussion Calls on application leave.
 
 @param nativeAd Current ad.
 */
- (void)onLeaveApplicationWithNativeAd:(MTRGNativeAd *)nativeAd;

/**
 @discussion Calls when video plays with current ad.
 
 @param nativeAd Current ad.
 */
- (void)onVideoPlayWithNativeAd:(MTRGNativeAd *)nativeAd;

/**
 @discussion Calls when video pauses with current ad.
 
 @param nativeAd Current ad.
 */
- (void)onVideoPauseWithNativeAd:(MTRGNativeAd *)nativeAd;

/**
 @discussion Calls when video completes with current ad.
 
 @param nativeAd Current ad.
 */
- (void)onVideoCompleteWithNativeAd:(MTRGNativeAd *)nativeAd;

@end

/**
 @discussion Native ad media delegate protocol.
 */
@protocol MTRGNativeAdMediaDelegate <NSObject>

/**
 @discussion Calls when icon loaded for the ad.
 
 @param nativeAd Current ad.
 */
- (void)onIconLoadWithNativeAd:(MTRGNativeAd *)nativeAd;

/**
 @discussion Calls when image loaded for the ad.
 
 @param nativeAd Current ad.
 */
- (void)onImageLoadWithNativeAd:(MTRGNativeAd *)nativeAd;

/**
 @discussion Calls when adChoices image loaded for the ad.

 @param nativeAd Current ad.
 */
- (void)onAdChoicesIconLoadWithNativeAd:(MTRGNativeAd *)nativeAd;

@end

/**
 @discussion Base class for native ad.
 */
@interface MTRGNativeAd : MTRGBaseAd <MTRGNativeAdProtocol>

/**
 @discussion Placement for ad choices.
 */
@property(nonatomic) MTRGAdChoicesPlacement adChoicesPlacement;

/**
 @discussion Delegate for the ad. Must conforms MTRGNativeAdDelegate protocol.
 */
@property(nonatomic, weak, nullable) id <MTRGNativeAdDelegate> delegate;

/**
 @discussion Delegate for the media of the ad. Must conforms MTRGNativeAdMediaDelegate protocol.
 */
@property(nonatomic, weak, nullable) id <MTRGNativeAdMediaDelegate> mediaDelegate;

/**
 @discussion Delegate for the AdChoices options. Must conforms MTRGNativeAdChoicesOptionDelegate protocol.
 See MTRGNativeAdChoicesOptionDelegate.h
 */
@property(nonatomic, weak, nullable) id <MTRGNativeAdChoicesOptionDelegate> adChoicesOptionDelegate;

/**
 @discussion Promo banner for the ad.
 */
@property(nonatomic, readonly, nullable) MTRGNativePromoBanner *banner;

/**
 @discussion Static constructor. Create instance of the class with slot identifier.
 
 @param slotId Slot identifier.
 
 @return Instance of the class.
 */
+ (instancetype)nativeAdWithSlotId:(NSUInteger)slotId;

/**
 @discussion Static constructor. Create instance of the class with slot identifier and menu factory.

 @param slotId Slot identifier.
 @param adChoicesMenuFactory Menu factory for drawing adChoices menu manually. See MTRGMenuFactory protocol.

 @return Instance of the class.
 */
+ (instancetype)nativeAdWithSlotId:(NSUInteger)slotId adChoicesMenuFactory:(id <MTRGMenuFactory>)adChoicesMenuFactory;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Create instance of the class with slot identifier.
 
 @param slotId Slot identifier.
 
 @return Instance of the class.
 */
- (instancetype)initWithSlotId:(NSUInteger)slotId;

/**
 @discussion Create instance of the class with slot identifier.

 @param slotId Slot identifier.
 @param adChoicesMenuFactory Menu factory for drawing adChoices menu manually. See MTRGMenuFactory protocol.

 @return Instance of the class.
 */
- (instancetype)initWithSlotId:(NSUInteger)slotId adChoicesMenuFactory:(id <MTRGMenuFactory>)adChoicesMenuFactory;

/**
 @discussion Loads the ad.
 */
- (void)load;

/**
 @discussion Loads the ad from bid identifier.
 
 @param bidId Bid identifier for the ad.
 */
- (void)loadFromBid:(NSString *)bidId;

/**
 @discussion Registers view for the ad.
 
 @param containerView View for the ad.
 @param controller Controller for the ad.
 */
- (void)registerView:(UIView *)containerView withController:(UIViewController *)controller;

/**
 @discussion Registers view for the ad.
 
 @param containerView View for the ad.
 @param controller Controller for the ad.
 @param clickableViews Array of clickable views.
 */
- (void)registerView:(UIView *)containerView
      withController:(UIViewController *)controller
  withClickableViews:(nullable NSArray

<UIView *> *)
clickableViews;

/**
 @discussion Unregister view for the ad.
 */
- (void)unregisterView;

/**
 @discussion Method to handle adChoices click.

 @param viewController Used UIViewController.
 @param sourceView UIView for iPad popover.
 */
- (void)handleAdChoicesClickWithController:(UIViewController *)viewController sourceView:(nullable UIView

*)
sourceView NS_SWIFT_NAME(handleAdChoicesClick(controller
:sourceView:));

@end

NS_ASSUME_NONNULL_END

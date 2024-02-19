//
//  MTRGNativeAppwallAd.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 4/12/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MyTargetSDK/MTRGBaseAd.h>

@class MTRGNativeAppwallAd;
@class MTRGImageData;
@class MTRGNativeAppwallBanner;
@class MTRGAppwallAdView;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Delegate's protocol for native appwall ad.
 */
@protocol MTRGNativeAppwallAdDelegate <NSObject>

/**
 @discussion Calls on load ad with banners. Required.
 
 @param banners Array of MTRGNativeAppwallBanner instances.
 @param appwallAd Appwall ad as an instance of MTRGNativeAppwallAd.
 */
- (void)onLoadWithBanners:(NSArray

<MTRGNativeAppwallBanner *> *)
banners appwallAd
:(MTRGNativeAppwallAd *)
appwallAd;

/**
 @discussion Calls if there is no ad.

 @param error An error code/description.
 @param appwallAd Appwall ad for which no ad was found.
 */
- (void)onLoadFailedWithError:(NSError *)error appwallAd:(MTRGNativeAppwallAd *)appwallAd NS_SWIFT_NAME

(
onLoadFailed(error
:appwallAd:));

@optional

/**
 @discussion Calls when there is no ad.

 @param reason The reason why there is no ad.
 @param appwallAd Appwall ad for which no ad was found.
 */
- (void)onNoAdWithReason:(NSString *)reason appwallAd:(MTRGNativeAppwallAd *)appwallAd __attribute__((deprecated("use onLoadFailed method instead.")));

/**
 @discussion Calls when click on appwall add was happened. Optional.
 
 @param appwallAd Which ad has been clicked.
 @param banner Banner instance.
 */
- (void)onAdClickWithNativeAppwallAd:(MTRGNativeAppwallAd *)appwallAd banner:(MTRGNativeAppwallBanner *)banner;

/**
 @discussion Calls when native appwall ad was showed modal.
 
 @param appwallAd Which ad has been showed modal.
 */
- (void)onShowModalWithNativeAppwallAd:(MTRGNativeAppwallAd *)appwallAd;

/**
 @discussion Calls when native appwall ad was dismissed after modal showing.
 
 @param appwallAd Which ad has been dismissed.
 */
- (void)onDismissModalWithNativeAppwallAd:(MTRGNativeAppwallAd *)appwallAd;

/**
 @discussion Calls when app has been left while appwall ad showing.
 
 @param appwallAd Which ad has been showed on leave.
 */
- (void)onLeaveApplicationWithNativeAppwallAd:(MTRGNativeAppwallAd *)appwallAd;

@end

/**
 @discussion Class for native appwall ad.
 */
@interface MTRGNativeAppwallAd : MTRGBaseAd

/**
 @discussion Delegate which conforms protocol MTRGNativeAppwallAdDelegate.
 */
@property(nonatomic, weak, nullable) id <MTRGNativeAppwallAdDelegate> delegate;

/**
 @discussion Array of banners.
 */
@property(nonatomic, readonly) NSArray<MTRGNativeAppwallBanner *> *banners;

/**
 @discussion Tiitle for appwall ad.
 */
@property(nonatomic, copy) NSString *title;

/**
 @discussion Title for close button.
 */
@property(nonatomic, copy) NSString *closeButtonTitle;

/**
 @discussion Period of cache in seconds.
 */
@property(nonatomic) NSTimeInterval cachePeriodInSec;

/**
 @discussion Flag to autoload images.
 */
@property(nonatomic) BOOL autoLoadImages;

/**
 @discussion Method to load image to the UIImageView.
 
 @param imageData Instance of MTRGImageData.
 @param imageView Instance of UIImageView where image will be presented.
 */
+ (void)loadImage:(MTRGImageData *)imageData toView:(UIImageView *)imageView;

/**
 @discussion Static constructor of MTRGNativeAppwallAd
 
 @param slotId Slot ID for native appwall add.
 
 @return Instance of MTRGNativeAppwallAd
 */
+ (instancetype)nativeAppwallAdWithSlotId:(NSUInteger)slotId;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Constructor of MTRGNativeAppwallAd
 
 @param slotId Slot ID for native appwall add.
 
 @return Instance of MTRGNativeAppwallAd
 */
- (instancetype)initWithSlotId:(NSUInteger)slotId;

/**
 @discussion Method to load ad.
 */
- (void)load;

/**
 @discussion Method to show ad in UIViewController.
 
 @param controller Instance of UIViewController to show ad.
 */
- (void)showWithController:(UIViewController *)controller;

/**
 @discussion Method to close the ad.
 */
- (void)close;

/**
 @discussion Method to register appwall ad view with specific UIViewController.
 
 @param appwallAdView Appwall ad view that will bed showed in controller.
 @param controller Instance of UIViewController where ad view will be showed.
 */
- (void)registerAppwallAdView:(MTRGAppwallAdView *)appwallAdView withController:(UIViewController *)controller;

/**
 @discussion Method to unregister the ad view.
 */
- (void)unregisterAppwallAdView;

/**
 @discussion Method to check notifications.
 
 @return Boolean value, notifications is exist or not.
 */
- (BOOL)hasNotifications;

/**
 @discussion Method to handle banner show.
 
 @param banner Instance of MTRGNativeAppwallBanner.
 */
- (void)handleBannerShow:(MTRGNativeAppwallBanner *)banner;

/**
 @discussion Method to handle array of banners show.
 
 @param banners Array of instances of MTRGNativeAppwallBanner.
 */
- (void)handleBannersShow:(NSArray

<MTRGNativeAppwallBanner *> *)
banners;

/**
 @discussion Method to handler click on banner.
 
 @param banner Instance of MTRGNativeAppwallBanner on which will register clicks.
 @param controller Instance of UIViewController.
 */
- (void)handleBannerClick:(MTRGNativeAppwallBanner *)banner withController:(UIViewController *)controller;

@end

NS_ASSUME_NONNULL_END

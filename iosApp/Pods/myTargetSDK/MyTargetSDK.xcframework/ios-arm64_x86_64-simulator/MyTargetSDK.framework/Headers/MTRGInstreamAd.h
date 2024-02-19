//
//  MTRGInstreamAd.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 5/4/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MyTargetSDK/MTRGBaseAd.h>

@protocol MTRGInstreamAdPlayer;
@protocol MTRGMenuFactory;
@class MTRGInstreamAd;
@class AVPlayer;
@class MTRGInstreamAdCompanionBanner;
@class MTRGShoppableAdsItem;
@class MTRGInstreamAdVideoMotionBanner;
@protocol MTRGInstreamAdVideoMotionPlayer;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Instream ad banner.
 */
@interface MTRGInstreamAdBanner : NSObject

/**
 @discussion The bundle identifier.
 */
@property(nonatomic, readonly, nullable) NSString *bundleId;

/**
 @discussion Duration of showing the banner.
 */
@property(nonatomic, readonly) NSTimeInterval duration;

/**
 @discussion Allows pause or not.
 */
@property(nonatomic, readonly) BOOL allowPause;

/**
 @discussion Allows close or not.
 */
@property(nonatomic, readonly) BOOL allowClose;

/**
 @discussion Delay to close the banner.
 */
@property(nonatomic, readonly) NSTimeInterval allowCloseDelay;

/**
 @discussion Size of the banner.
 */
@property(nonatomic, readonly) CGSize size;

/**
 @discussion CTA text.
 */
@property(nonatomic, readonly) NSString *ctaText;

/**
 @discussion The banner identifier.
 */
@property(nonatomic, readonly) NSString *bannerId;

/**
 @discussion If banner has a companion html page.
 */
@property(nonatomic, readonly) BOOL hasShoppable;

/**
 @discussion Advertising label.
 */
@property(nonatomic, readonly) NSString *advertisingLabel;

/**
 @discussion Disclaimer for the banner.
 */
@property(nonatomic, readonly, nullable) NSString *disclaimer;

/**
 @discussion Age restrictions for the banner.
 */
@property(nonatomic, readonly, nullable) NSString *ageRestrictions;

/**
 @discussion AdChoices image.
 */
@property(nonatomic, readonly, nullable) UIImage *adChoicesImage;

/**
 @discussion If banner has AdChoices.
 */
@property(nonatomic, readonly) BOOL hasAdChoices;

/**
 @discussion Array of Instances of banner's companions(MTRGInstreamAdCompanionBanner).
 */
@property(nonatomic, readonly) NSArray<MTRGInstreamAdCompanionBanner *> *companionBanners;

/**
 @discussion Array of Instances of shoppable cards (MTRGShoppableAdsItem).
 */
@property(nonatomic, readonly, nullable) NSArray<MTRGShoppableAdsItem *> *shoppableAdsItems;

- (instancetype)init

NS_UNAVAILABLE;

@end

/**
 @discussion Protocol of delegate of instream ad.
 */
@protocol MTRGInstreamAdDelegate <NSObject>

/**
 @discussion Calls when instream ad is loaded. Required.
 
 @param instreamAd Current instream ad.
 */
- (void)onLoadWithInstreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion Calls if there is no ad.

 @param error An error code/description.
 @param instreamAd Current instream ad.
 */
- (void)onLoadFailedWithError:(NSError *)error instreamAd:(MTRGInstreamAd *)instreamAd NS_SWIFT_NAME

(
onLoadFailed(error
:instreamAd:));

@optional

/**
 @discussion Calls if there is no ad.

 @param reason Reason why there is no ad.
 @param instreamAd Current instream ad.
 */
- (void)onNoAdWithReason:(NSString *)reason instreamAd:(MTRGInstreamAd *)instreamAd __attribute__((deprecated("use onLoadFailed method instead.")));

/**
 @discussion Calls on error for the ad.
 
 @param reason Reason of the error.
 @param instreamAd Current instream ad.
 */
- (void)onErrorWithReason:(NSString *)reason instreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion Calls on banner's start.
 
 @param banner Current instream ad banner.
 @param instreamAd Current instream ad.
 */
- (void)onBannerStart:(MTRGInstreamAdBanner *)banner instreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion Calls on banner's start.

 @param banner Current instream ad video motion banner.
 @param instreamAd Current instream ad.
 */
- (void)onVideoMotionBannerStart:(MTRGInstreamAdVideoMotionBanner *)banner instreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion Calls on banner's complete.
 
 @param banner Current instream ad banner.
 @param instreamAd Current instream ad.
 */
- (void)onBannerComplete:(MTRGInstreamAdBanner *)banner instreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion Calls on banner's complete.

 @param banner Current instream ad video motion banner.
 @param instreamAd Current instream ad.
 */
- (void)onVideoMotionBannerComplete:(MTRGInstreamAdVideoMotionBanner *)banner instreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion Calls on banner's time left change.
 
 @param timeLeft Remaining playback time.
 @param duration Duration of video.
 @param instreamAd Current instream ad.
 */
- (void)onBannerTimeLeftChange:(NSTimeInterval)timeLeft duration:(NSTimeInterval)duration instreamAd:(MTRGInstreamAd *)instreamAd;


/**
 @discussion Calls on banner's complete with section.
 
 @param section Current section of the ad banner.
 @param instreamAd Current instream ad.
 */
- (void)onCompleteWithSection:(NSString *)section instreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion Calls on banner's modal show.
 
 @param instreamAd Current instream ad.
 */
- (void)onShowModalWithInstreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion Calls on banner's modal dismiss.
 
 @param instreamAd Current instream ad.
 */
- (void)onDismissModalWithInstreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion Calls on application leave while banner is showing.
 
 @param instreamAd Current instream ad.
 */
- (void)onLeaveApplicationWithInstreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion The method is called by clicking in the adChoices menu on certain items, such as "Complain", so the content should be hidden. You should call skip() method.

 @param banner Current instream ad banner.
 @param instreamAd Current instream ad.
 */
- (void)onBannerShouldClose:(MTRGInstreamAdBanner *)banner instreamAd:(MTRGInstreamAd *)instreamAd;

/**
 @discussion The method is called by clicking in the adChoices menu on certain items, such as "Complain", so the content should be hidden. You should call skip() method.

 @param banner Current instream ad video motion banner.
 @param instreamAd Current instream ad.
 */
- (void)onVideoMotionShouldClose:(MTRGInstreamAdVideoMotionBanner *)banner instreamAd:(MTRGInstreamAd *)instreamAd;

@end

/**
 @discussion Class for an instream ad.
 */
@interface MTRGInstreamAd : MTRGBaseAd

/**
 @discussion Delegate of the ad.
 */
@property(nonatomic, weak, nullable) id <MTRGInstreamAdDelegate> delegate;

/**
 @discussion Player for the ad. Conforms MTRGInstreamAdPlayer protocol.
 */
@property(nonatomic, nullable) id <MTRGInstreamAdPlayer> player;

/**
 @discussion Player for VideoMotion banner. Conforms MTRGVideoMotionPlayer protocol.
 */
@property(nonatomic, nullable) id <MTRGInstreamAdVideoMotionPlayer> videoMotionPlayer;

/**
 @discussion Array of midpoint for the ad.
 */
@property(nonatomic, readonly, copy) NSArray<NSNumber *> *midpoints;

/**
 @discussion Array of section names.
 */
@property(nonatomic, readonly) NSArray<NSString *> *videoSectionNames;

/**
 @discussion Instance of the AVPlayer.
 */
@property(nonatomic, readonly, nullable) AVPlayer *avPlayer;

/**
 @discussion Parameter sets quality of playing video.
 */
@property(nonatomic) NSUInteger videoQuality;

/**
 @discussion Timeout of loading the ad.
 */
@property(nonatomic) NSUInteger loadingTimeout;

/**
 @discussion Determines ad shows fullscreen or not.
 */
@property(nonatomic) BOOL fullscreen;

/**
 @discussion Volume of the ad.
 */
@property(nonatomic) float volume;

/**
 @discussion View for companion html page.
 */
@property(nonatomic, readonly, nullable) UIView *shoppableView;

/**
 @discussion Determines ad shows shoppable view or not.
 */
@property(nonatomic) BOOL shoppablePresented;

/**
 @discussion Static constructor. Creates instream ad with slot identifier.
 
 @param slotId Slot identifier.
 
 @return Instance of the class.
 */
+ (instancetype)instreamAdWithSlotId:(NSUInteger)slotId;

/**
 @discussion Static constructor. Creates instream ad with slot identifier and menu factory.

 @param slotId Slot identifier.
 @param menuFactory Menu factory.

 @return Instance of the class.
 */
+ (instancetype)instreamAdWithSlotId:(NSUInteger)slotId menuFactory:(id <MTRGMenuFactory>)menuFactory;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Creates instream ad with slot identifier.
 
 @param slotId Slot identifier.
 
 @return Instance of the class.
 */
- (instancetype)initWithSlotId:(NSUInteger)slotId;

/**
 @discussion Creates instream ad with slot identifier and menu factory.

 @param slotId Slot identifier.
 @param menuFactory Menu factory.

 @return Instance of the class.
 */
- (instancetype)initWithSlotId:(NSUInteger)slotId menuFactory:(id <MTRGMenuFactory>)menuFactory;

/**
 @discussion Loads the ad.
 */
- (void)load;

/**
 @discussion Pauses the ad.
 */
- (void)pause;

/**
 @discussion Resumes the ad.
 */
- (void)resume;

/**
 @discussion Stops the ad.
 */
- (void)stop;

/**
 @discussion Skips the ad.
 */
- (void)skip;

/**
 @discussion Skips the banner.
 */
- (void)skipBanner;

/**
 @discussion Method to handle click with specific controller.
 
 @param controller Instance of UIViewController.
 */
- (void)handleClickWithController:(UIViewController *)controller;

/**
 @discussion Method to handle companion click.

 @param companionBanner The companion for the ad.
 @param controller Used UIViewController.
 */
- (void)handleCompanionClick:(MTRGInstreamAdCompanionBanner *)companionBanner withController:(UIViewController *)controller;

/**
 @discussion Method to handle companion show.

 @param companionBanner The companion for the ad.
 */
- (void)handleCompanionShow:(MTRGInstreamAdCompanionBanner *)companionBanner;

/**
 @discussion Method to handle adChoices click.

 @param viewController Used UIViewController.
 @param sourceView UIView for iPad popover.
 */
- (void)handleAdChoicesClickWithController:(UIViewController *)viewController sourceView:(nullable UIView

*)
sourceView NS_SWIFT_NAME(handleAdChoicesClick(with
:sourceView:));

/**
 @discussion Starts preroll.
 */
- (void)startPreroll;

/**
 @discussion Stops preroll.
 */
- (void)startPostroll;

/**
 @discussion Starts pauseroll.
 */
- (void)startPauseroll;

/**
 @discussion Starts midroll at the point.
 
 @param point Point to start the midroll.
 */
- (void)startMidrollWithPoint:(NSNumber *)point;

/**
 @discussion Starts using of the default player.
 */
- (void)useDefaultPlayer;

/**
 @discussion Method to configure midpoints for the ad.
 
 @param midpointsP Array of midpoints.
 @param videoDuration Duration of the video ad.
 */
- (void)configureMidpointsP:(nullable NSArray

<NSNumber *> *)
midpointsP
        forVideoDuration
:(NSTimeInterval)
videoDuration;

/**
 @discussion Method to configure midpoints for the ad.
 
 @param midpoints Array of midpoints.
 @param videoDuration Duration of the video ad.
 */
- (void)configureMidpoints:(nullable NSArray

<NSNumber *> *)
midpoints
        forVideoDuration
:(NSTimeInterval)
videoDuration;

/**
 @discussion Method to configure midpoints for the ad.
 
 @param videoDuration Duration of the video ad.
 */
- (void)configureMidpointsForVideoDuration:(NSTimeInterval)videoDuration;

/**
 @discussion Method to handle shoppable item show.

 @param identifier Shoppable card id
 */
- (void)shoppableAdsItemShowForIdentifier:(nonnull NSString

*)
identifier;

/**
 @discussion Method to handle shoppable item click.

 @param identifier Shoppable card id
 */
- (void)shoppableAdsItemClickForIdentifier:(nonnull NSString

*)
identifier;

@end

NS_ASSUME_NONNULL_END

//
//  MTRGInstreamResearch.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 19/02/2019.
//  Copyright © 2019 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MyTargetSDK/MTRGBaseAd.h>

@class MTRGInstreamResearch;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Instream research delegate's protocol.
 */
@protocol MTRGInstreamResearchDelegate <NSObject>

/**
 @discussion Calls on load instream research.
 
 @param instreamResearch Current instream research.
 */
- (void)onLoadWithInstreamResearch:(MTRGInstreamResearch *)instreamResearch;

/**
 @discussion Calls if there is no data.

 @param error An error code/description.
 @param instreamResearch Current instream research.
 */
- (void)onLoadFailedWithError:(NSError *)error instreamResearch:(MTRGInstreamResearch *)instreamResearch NS_SWIFT_NAME

(
onLoadFailed(error
:instreamResearch:));

@optional

/**
 @discussion Calls when there is no data for the instream research.

 @param reason The reason why there is no data.
 @param instreamResearch Current instream research.
 */
- (void)onNoDataWithReason:(NSString *)reason instreamResearch:(MTRGInstreamResearch *)instreamResearch __attribute__((deprecated("use onLoadFailed method instead.")));

@end

/**
 @discussion Class for instream research.
 */
@interface MTRGInstreamResearch : MTRGBaseAd

/**
 @discussion Instream research delegate. Must conforms MTRGInstreamResearchDelegate protocol.
 */
@property(nonatomic, weak, nullable) id <MTRGInstreamResearchDelegate> delegate;

/**
 @discussion Static constructor. Creates instance of the class with slot identifier and duration of instream ad.
 
 @param slotId Slot identifier.
 @param duration Duration of instream ad.
 
 @return Instance of the class.
 */
+ (instancetype)instreamResearchWithSlotId:(NSUInteger)slotId duration:(NSTimeInterval)duration;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Loads the ad.
 */
- (void)load;

/**
 @discussion Registers a player view.
 
 @param view Player view.
 */
- (void)registerPlayerView:(UIView *)view;

/**
 @discussion Unregisters player view.
 */
- (void)unregisterPlayerView;

/**
 @discussion Tracks progress.
 
 @param progress Progress to track.
 */
- (void)trackProgress:(NSTimeInterval)progress;

/**
 @discussion Tracks pause.
 */
- (void)trackPause;

/**
 @discussion Tracks resume.
 */
- (void)trackResume;

/**
 @discussion Tracks mute.
 
 @param isMuted Sets mute or not.
 */
- (void)trackMute:(BOOL)isMuted;

/**
 @discussion Tracks fullscreen.
 
 @param isFullscreen Sets fullscreen or not.
 */
- (void)trackFullscreen:(BOOL)isFullscreen;

@end

NS_ASSUME_NONNULL_END

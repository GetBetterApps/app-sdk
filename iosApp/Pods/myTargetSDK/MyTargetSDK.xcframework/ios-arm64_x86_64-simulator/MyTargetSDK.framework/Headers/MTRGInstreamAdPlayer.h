//
//  MTRGInstreamAdPlayer.h
//  myTargetSDK 5.20.2
//
//  Created by Anton Bulankin on 21.09.16.
//  Copyright © 2016 Mail.ru. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@protocol MTRGInstreamAdPlayerDelegate <NSObject>

/**
 @discussion Calls on video start.
 */
- (void)onAdVideoStart;

/**
 @discussion Calls on video pause.
 */
- (void)onAdVideoPause;

/**
 @discussion Calls on video resume.
 */
- (void)onAdVideoResume;

/**
 @discussion Calls on video stop.
 */
- (void)onAdVideoStop;

/**
 @discussion Calls on error.
 
 @param reason Reason of the error.
 */
- (void)onAdVideoErrorWithReason:(NSString *)reason;

/**
 @discussion Calls on ad video complete.
 */
- (void)onAdVideoComplete;

@end

/**
 @discussion Video player for instream video ad.
 */
@protocol MTRGInstreamAdPlayer <NSObject>

/**
 @discussion Duration of the video ad.
 */
@property(nonatomic, readonly) NSTimeInterval adVideoDuration;

/**
 @discussion Elapsed time for the video ad.
 */
@property(nonatomic, readonly) NSTimeInterval adVideoTimeElapsed;

/**
 @discussion Delegate of the player. Must conforms MTRGInstreamAdPlayerDelegate protocol.
 */
@property(nonatomic, weak, nullable) id <MTRGInstreamAdPlayerDelegate> adPlayerDelegate;

/**
 @discussion UIView for the player.
 */
@property(nonatomic, readonly) UIView *adPlayerView;

/**
 @discussion Volume of the player.
 */
@property(nonatomic) float volume;

/**
 @discussion Starts player the ad from the URL.
 
 @param url URL of the video.
 */
- (void)playAdVideoWithUrl:(NSURL *)url;

/**
 @discussion Pauses the video.
 */
- (void)pauseAdVideo;

/**
 @discussion Resumes the video.
 */
- (void)resumeAdVideo;

/**
 @discussion Stops the video.
 */
- (void)stopAdVideo;

@end

NS_ASSUME_NONNULL_END

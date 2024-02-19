//
//  MTRGInstreamAudioAdPlayer.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 5/25/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Protocol describes interface of a player for the instream audio ad.
 */
@protocol MTRGInstreamAudioAdPlayerDelegate <NSObject>

/**
 @discussion Calls on audio start.
 */
- (void)onAdAudioStart;

/**
 @discussion Calls on audio pause.
 */
- (void)onAdAudioPause;

/**
 @discussion Calls on audio resume.
 */
- (void)onAdAudioResume;

/**
 @discussion Calls on audio stop.
 */
- (void)onAdAudioStop;

/**
 @discussion Calls on audio error.
 
 @param reason Reason of the error.
 */
- (void)onAdAudioErrorWithReason:(NSString *)reason;

/**
 @discussion Calls when audio ad is complete.
 */
- (void)onAdAudioComplete;

@end

/**
 @discussion Audio player for instream audio ad.
 */
@protocol MTRGInstreamAudioAdPlayer <NSObject>

/**
 @discussion Duration of the audio ad.
 */
@property(nonatomic, readonly) NSTimeInterval adAudioDuration;

/**
 @discussion Elapsed time for the audio ad.
 */
@property(nonatomic, readonly) NSTimeInterval adAudioTimeElapsed;

/**
 @discussion Ad audio player delegate.
 */
@property(nonatomic, weak, nullable) id <MTRGInstreamAudioAdPlayerDelegate> adPlayerDelegate;

/**
 @discussion Volume set for the player.
 */
@property(nonatomic) float volume;

/**
 @discussion Play the ad with URL.
 
 @param url URL of the audio.
 */
- (void)playAdAudioWithUrl:(NSURL *)url;

/**
 @discussion Pause the audio.
 */
- (void)pauseAdAudio;

/**
 @discussion Resume the audio.
 */
- (void)resumeAdAudio;

/**
 @discussion Stop the audio.
 */
- (void)stopAdAudio;

@end

NS_ASSUME_NONNULL_END

//
//  BigoVideoController.h
//  adsdk
//
//  Created by 李剑锋 on 2020/2/26.
//  Copyright © 2020 BIGO. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

///>  onVideoStart --> onPlay --> onPause --> onPlay --> onVideoEnd
@class BigoVideoController;

@protocol BGVideoLifeCallbackDelegate <NSObject>

@optional
- (void)onVideoStart:(BigoVideoController *)videoController;

- (void)onVideoPlay:(BigoVideoController *)videoController;

- (void)onVideoPause:(BigoVideoController *)videoController;

- (void)onVideoEnd:(BigoVideoController *)videoController;

- (void)onVideo:(BigoVideoController *)videoController mute:(BOOL)mute;

- (void)onVideoProgressChange:(BigoVideoController *)videoController currentTimeMillis:(int32_t)currentTimeMillis totalTimeMillis:(int32_t)totalTimeMillis;

@end

@protocol BGBufferedVideoLifeCallbackDelegate <BGVideoLifeCallbackDelegate>

- (void)onVideoBuffering; //视频缓冲中
- (void)onVideoBuffered; //视频缓冲完成

@end

@interface BigoVideoController : NSObject

@property(nonatomic, assign, readonly) BOOL isMuted;
@property(nonatomic, assign, readonly) BOOL isPlaying;
@property(nonatomic, assign, readonly) BOOL isPaused;

@property(nonatomic, weak) id <BGVideoLifeCallbackDelegate> delegate;

- (void)play;

- (void)pause;

- (void)mute:(BOOL)mute;

@end

NS_ASSUME_NONNULL_END

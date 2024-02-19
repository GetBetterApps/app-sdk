//
//  MTRGVideoData.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 2/9/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTargetSDK/MTRGMediaData.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class of video data
 */
@interface MTRGVideoData : MTRGMediaData

/**
 @discussion Path to video data
 */
@property(atomic, readonly, nullable) NSString *path;

/**
 @discussion Flag defines that video can be cached
 */
@property(nonatomic, readonly) BOOL isCacheable;

/**
 @discussion Method choose best video from array with defined quality
 
 @param videoDatas Array of instances of MTRGVideoData
 @param videoQuality Number defines quality of video to choose
 
 @return Instance of MTRGVideoData
 */
+ (nullable MTRGVideoData

*)chooseBestFromArray:(NSArray<MTRGVideoData *> *)
videoDatas videoQuality
:(NSUInteger)
videoQuality;

/**
 @discussion Static constructor of MTRGVideoData
 
 @param url URL of video data
 @param size Size of video to display
 
 @return Instance of MTRGVideoData
 */
+ (instancetype)videoDataWithUrl:(NSString *)url size:(CGSize)size;

/**
 @discussion Method creates URL of video
 
 @return Instance of NSURL
 */
- (nullable NSURL

*)
createVideoUrl;

@end

NS_ASSUME_NONNULL_END

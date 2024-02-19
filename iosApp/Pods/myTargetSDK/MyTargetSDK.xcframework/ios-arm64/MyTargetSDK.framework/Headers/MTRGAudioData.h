//
//  MTRGAudioData.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 2/9/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTargetSDK/MTRGMediaData.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class for audio data
 */
@interface MTRGAudioData : MTRGMediaData

@property(nonatomic, readonly) NSUInteger bitrate;

/**
 @discussion Static constructor of MTRGAudioData
 
 @param url Contains url of audio data
 @param bitrate Contains bitrate of audio data
 
 @return Instance of MTRGAudioData
 */
+ (instancetype)audioDataWithUrl:(NSString *)url bitrate:(NSUInteger)bitrate;

@end

NS_ASSUME_NONNULL_END

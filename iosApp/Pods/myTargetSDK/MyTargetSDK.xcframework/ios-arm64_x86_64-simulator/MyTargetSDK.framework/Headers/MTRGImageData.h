//
//  MTRGImageData.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 2/9/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTargetSDK/MTRGMediaData.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class of image data
 */
@interface MTRGImageData : MTRGMediaData

/**
 @discussion Instance of UIImage
 */
@property(atomic, readonly, nullable) UIImage *image;

/**
 @discussion Define use cache for image or not
 */
@property(atomic) BOOL useCache;

/**
 @discussion Sets capacity of image cache in bytes
 
 @param capacityInBytes Number of bytes
 */
+ (void)setCacheCapacity:(NSUInteger)capacityInBytes;

/**
 @discussion Static constructor
 
 @param url Image url
 
 @return Instance of MTRGImageData
 */
+ (instancetype)imageDataWithUrl:(NSString *)url;

@end

NS_ASSUME_NONNULL_END

//
//  MTRGAdSize.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 01.07.2020.
//  Copyright © 2020 Mail.ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Ad size types
 */
typedef enum : NSUInteger {
    MTRGAdSizeType320x50,
    MTRGAdSizeType300x250,
    MTRGAdSizeType728x90,
    MTRGAdSizeTypeAdaptive
} MTRGAdSizeType;

/**
 @discussion Class defines few sizes of ad.
 */
@interface MTRGAdSize : NSObject

/**
 @discussion Converts MTRGAdSize to CGSize.
 */
@property(nonatomic, readonly) CGSize size;

/**
 @discussion Type of ad size.
 */
@property(nonatomic, readonly) MTRGAdSizeType type;

/**
 @discussion Ad size for 320x50
 
 @return Instance of MTRGAdSize.
 */
+ (instancetype)adSize320x50;

/**
 @discussion Ad size for 300x250
 
 @return Instance of MTRGAdSize.
 */
+ (instancetype)adSize300x250;

/**
 @discussion Ad size for 728x90
 
 @return Instance of MTRGAdSize.
 */
+ (instancetype)adSize728x90;

/**
 @discussion Ad size for current orientation
 
 @return Instance of MTRGAdSize.
 */
+ (instancetype)adSizeForCurrentOrientation;

/**
 @discussion Ad size for current orientation with specific width.
 
 @return Instance of MTRGAdSize.
 */
+ (instancetype)adSizeForCurrentOrientationForWidth:(CGFloat)width;

/**
 @discussion Ad size for current orientation with specific width and limited height.
 
 @return Instance of MTRGAdSize.
 */
+ (instancetype)adSizeForCurrentOrientationForWidth:(CGFloat)width maxHeight:(CGFloat)maxHeight;

@end

NS_ASSUME_NONNULL_END

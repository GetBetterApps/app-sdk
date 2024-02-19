//
//  MTRGMediaData.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 2/9/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class of media data
 */
@interface MTRGMediaData : NSObject

/**
 @discussion URL of media data
 */
@property(nonatomic, readonly, copy) NSString *url;

/**
 @discussion Data of media
 */
@property(atomic, nullable) id data;

/**
 @discussion Size of media to display
 */
@property(atomic) CGSize size;

/**
 @discussion Constructor of MTRGMediaData
 
 @param url URL of media
 
 @return Instance of MTRGMediaData
 */
- (instancetype)initWithUrl:(NSString *)url;

@end

NS_ASSUME_NONNULL_END

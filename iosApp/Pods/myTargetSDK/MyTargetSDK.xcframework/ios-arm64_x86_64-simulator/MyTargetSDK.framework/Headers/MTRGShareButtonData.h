//
//  MTRGShareButtonData.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 16/01/2019.
//  Copyright © 2019 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class of share button data
 */
@interface MTRGShareButtonData : NSObject

/**
 @discussion Name of button
 */
@property(nonatomic, readonly) NSString *name;

/**
 @discussion URL for sharing
 */
@property(nonatomic, readonly) NSString *url;

/**
 @discussion URL of image
 */
@property(nonatomic, readonly) NSString *imageUrl;

/**
 @discussion Static constructor of MTRGShareButton
 
 @param name Name of button
 @param url URL for sharing
 @param imageUrl URL of image
 
 @return Instance of MTRGShareButton
 */
+ (instancetype)shareButtonWithName:(NSString *)name url:(NSString *)url imageUrl:(NSString *)imageUrl;

- (instancetype)init

NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END

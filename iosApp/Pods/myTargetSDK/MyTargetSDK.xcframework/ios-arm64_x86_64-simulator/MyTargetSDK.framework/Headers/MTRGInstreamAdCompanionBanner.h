//
//  MTRGInstreamAdCompanionBanner.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 12.01.2022.
//  Copyright © 2022 Mail.ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class-companion for instream banner
 */
@interface MTRGInstreamAdCompanionBanner : NSObject

/**
 @discussion Banner's width.
 */
@property(nonatomic, readonly) NSUInteger width;

/**
 @discussion Banner's height.
 */
@property(nonatomic, readonly) NSUInteger height;

/**
 @discussion Asset's width.
 */
@property(nonatomic, readonly) NSUInteger assetWidth;

/**
 @discussion Asset's height.
 */
@property(nonatomic, readonly) NSUInteger assetHeight;

/**
 @discussion Expanded width.
 */
@property(nonatomic, readonly) NSUInteger expandedWidth;

/**
 @discussion Expanded height.
 */
@property(nonatomic, readonly) NSUInteger expandedHeight;

/**
 @discussion Flag determines clickable banner or not.
 */
@property(nonatomic, readonly) BOOL isClickable;

/**
 @discussion String for static resource.
 */
@property(nonatomic, readonly, copy, nullable) NSString *staticResource;

/**
 @discussion String for iFrame resource.
 */
@property(nonatomic, readonly, copy, nullable) NSString *iframeResource;

/**
 @discussion String for HTML resource.
 */
@property(nonatomic, readonly, copy, nullable) NSString *htmlResource;

/**
 @discussion API.
 */
@property(nonatomic, readonly, copy, nullable) NSString *apiFramework;

/**
 @discussion Slot identifier.
 */
@property(nonatomic, readonly, copy, nullable) NSString *adSlotID;

/**
 @discussion Required string.
 */
@property(nonatomic, readonly, copy, nullable) NSString *required;

- (instancetype)init

NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END

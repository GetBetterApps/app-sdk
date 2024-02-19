//
//  MTRGBaseAd.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 2/1/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

@class MTRGCustomParams;
@protocol MTRGAdNetworkConfigProtocol;

typedef NS_ENUM(NSInteger, MTRGAdLoadingError
);

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Base class of ad.
 */
@interface MTRGBaseAd : NSObject

/**
 @discussion Custom parameters
 */
@property(nonatomic, readonly) MTRGCustomParams *customParams;

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/*
@discussion Additional parameters for mediation.

@param adNetworkConfig An object implemented MTRGAdNetworkConfigProtocol.
*/
- (void)setAdNetworkConfig:(nullable id

<MTRGAdNetworkConfigProtocol>)
adNetworkConfig networkName
:(NSString *)
networkName;

@end

NS_ASSUME_NONNULL_END

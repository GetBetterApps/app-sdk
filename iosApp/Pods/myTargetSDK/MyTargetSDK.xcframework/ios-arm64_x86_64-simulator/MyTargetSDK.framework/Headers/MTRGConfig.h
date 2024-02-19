//
//  MTRGConfig.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 19.01.2021.
//  Copyright © 2021 Mail.ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

@class MTRGConfig;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Builder for MTRGConfig
 */
@interface MTRGConfigBuilder : NSObject

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Method defines track location or not
 
 @param trackingLocation Flag for tracking location
 
 @return Instance of MTRGConfigBuilder
 */
- (instancetype)withTrackingLocation:(BOOL)trackingLocation NS_SWIFT_NAME

(
withTrackingLocation(_
:));

/**
 @discussion Method adds test devices
 
 @param testDevices Array of devices identifiers
 
 @return Instance of MTRGConfigBuilder
 */
- (instancetype)withTestDevices:(nullable NSArray

<NSString *> *)
testDevices NS_SWIFT_NAME(withTestDevices(_
:));

/**
 @discussion Method to build MTRGConfig instance
 
 @return Instance of MTRGConfig
 */
- (MTRGConfig *)build

NS_SWIFT_NAME (build());

@end

/**
 @discussion Class to store and manage configuration of SDK
 */
@interface MTRGConfig : NSObject

/**
 @discussion Returns the location flag
 */
@property(nonatomic, readonly) BOOL isTrackLocationEnabled;

/**
 @discussion Returns the array of test devices
 */
@property(nonatomic, readonly, nullable) NSArray<NSString *> *testDevices;

/**
 @discussion Method creates builder of MTRGConfig instance
 
 @return Instance of MTRGConfigBuilder
 */
+ (MTRGConfigBuilder *)newBuilder

NS_SWIFT_NAME (newBuilder());

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END

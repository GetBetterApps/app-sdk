//
//  MTRGAntiFraudConfigBuilder
//  MyTrackerSDK 3.1.5
//
//  Created by Alexander Zakatnov on 26.01.2022.
//  Copyright © 2022 Mail.Ru Group. All rights reserved.
//


#import <Foundation/Foundation.h>

@class MRAntiFraudConfig;

NS_ASSUME_NONNULL_BEGIN

@interface MRAntiFraudConfigBuilder : NSObject

/**
 @discussion Enables or disables magnetic field sensor tracking
 
 @param useMagneticFieldSensor If true, the tracker handle events from magnetic field sensor
 
 @return Instance of MRAntiFraudConfigBuilder
 */
- (instancetype)useMagneticFieldSensor:(BOOL)useMagneticFieldSensor NS_SWIFT_NAME

(
useMagneticFieldSensor(_
:));

/**
 @discussion Enables or disables gyroscope sensor tracking
 
 @param useGyroscopeSensor If true, the tracker handle events from gyroscope sensor
 
 @return Instance of MRAntiFraudConfigBuilder
 */
- (instancetype)useGyroscopeSensor:(BOOL)useGyroscopeSensor NS_SWIFT_NAME

(
useGyroscopeSensor(_
:));

/**
 @discussion Enables or disables pressure sensor tracking
 
 @param usePressureSensor If true, the tracker handle events from pressure sensor
 
 @return Instance of MRAntiFraudConfigBuilder
 */
- (instancetype)usePressureSensor:(BOOL)usePressureSensor NS_SWIFT_NAME

(
usePressureSensor(_
:));

/**
 @discussion Creates new anti-fraud config instance with previously specified values
 
 @return Instance of MRAntiFraudConfig
 */
- (MRAntiFraudConfig *)build

NS_SWIFT_NAME (build());

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END

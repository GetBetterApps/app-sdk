//
//  MRAntiFraudConfig
//  MyTrackerSDK 3.1.5
//
//  Created by Alexander Zakatnov on 25.01.2022.
//  Copyright © 2022 Mail.Ru Group. All rights reserved.
//


#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRAntiFraudConfigBuilder.h>

NS_ASSUME_NONNULL_BEGIN

@interface MRAntiFraudConfig : NSObject

@property(nonatomic, readonly) BOOL useMagneticFieldSensor
NS_SWIFT_NAME(useMagneticSensor);

@property(nonatomic, readonly) BOOL useGyroscope
NS_SWIFT_NAME(useGyroscope);

@property(nonatomic, readonly) BOOL usePressureSensor
NS_SWIFT_NAME(usePressureSensor);

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Creates new builder-class instance for this class
 
 @return Instance of MRAntiFraudConfigBuilder
 */
+ (MRAntiFraudConfigBuilder *)newBuilder

NS_SWIFT_NAME (newBuilder());

@end

NS_ASSUME_NONNULL_END

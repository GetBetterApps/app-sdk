//
//  MRMiniAppEventMainBuilder.h
//  MyTrackerSDK 3.1.5
//
//  Created by Alexander Zakatnov on 05.11.2020.
//  Copyright © 2020 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

@class MRMiniAppOpenEventBuilder;
@class MRMiniAppCloseEventBuilder;
@class MRMiniAppUserEventBuilder;
@class MRMiniAppCustomEventBuilder;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Factory-class for creating specific builders of mini-app events
*/
@interface MRMiniAppEventMainBuilder : NSObject

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Create new builder for mini-app open event
 
 @param query Query string
 @return Mini-app open event builder
*/
- (MRMiniAppOpenEventBuilder *)openEvent:(NSString *)query NS_SWIFT_NAME

(
openEvent(query
:));

/**
 @discussion Create new builder for mini-app close event
 
 @return Mini-app close event builder
*/
- (MRMiniAppCloseEventBuilder *)closeEvent

NS_SWIFT_NAME (closeEvent());

/**
 @discussion Create new builder for mini-app login event
 
 @return Mini-app login event builder
*/
- (MRMiniAppUserEventBuilder *)loginEvent

NS_SWIFT_NAME (loginEvent());

/**
 @discussion Create new builder for mini-app registration event
 
 @return Mini-app registration event builder
*/
- (MRMiniAppUserEventBuilder *)registrationEvent

NS_SWIFT_NAME (registrationEvent());

/**
 @discussion Create new builder for mini-app custom event
 
 @param name Name of custom event in mini-app
 @return Mini-app custom event builder
*/
- (MRMiniAppCustomEventBuilder *)customEvent:(NSString *)name NS_SWIFT_NAME

(
customEvent(eventName
:));

@end

NS_ASSUME_NONNULL_END

//
//  MRMiniAppEvent.h
//  MyTrackerSDK 3.1.5
//
//  Created by Alexander Zakatnov on 05.11.2020.
//  Copyright © 2020 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRMyTrackerEvent.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class describes an event in mini-app
*/
@interface MRMiniAppEvent : MRMyTrackerEvent

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Mini-app identifier
*/
@property(nonatomic, readonly) NSString *miniAppId
NS_SWIFT_NAME(miniAppId);

/**
 @discussion Platform dependent user identifier
*/
@property(nonatomic, readonly) NSString *platformUserId
NS_SWIFT_NAME(platformUserId);

/**
 @discussion Query string
*/
@property(nullable, nonatomic, readonly) NSString *query
NS_SWIFT_NAME(query);

/**
 @discussion Custom user identifier
*/
@property(nullable, nonatomic, readonly) NSString *customUserId
NS_SWIFT_NAME(customUserId);

/**
 @discussion Custom event name
*/
@property(nullable, nonatomic, readonly) NSString *name
NS_SWIFT_NAME(name);

/**
 @discussion Additional event key-value params
*/
@property(nullable, nonatomic, readonly) NSDictionary<NSString *, NSString *> *eventParams
NS_SWIFT_NAME(eventParams);

@end

NS_ASSUME_NONNULL_END

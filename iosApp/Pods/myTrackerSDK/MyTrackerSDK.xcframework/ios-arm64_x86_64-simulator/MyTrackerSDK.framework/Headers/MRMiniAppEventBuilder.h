//
//  MRMiniAppEventBuilder.h
//  MyTrackerSDK 3.1.5
//
//  Created by Alexander Zakatnov on 05.11.2020.
//  Copyright © 2020 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRMiniAppEvent.h>
#import <MyTrackerSDK/MRMiniAppEventMainBuilder.h>

NS_ASSUME_NONNULL_BEGIN

/**
 Builder-class for MRMiniAppEvent
 */
@interface MRMiniAppEventBuilder : NSObject

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion: Creates new instance of class NOTE: For efficiency you could create one instance per appId-userId pair
 
 @param miniAppId Mini app identifier
 @param platformUserId Platform dependent user identifier
 
 @return Factory object for creating builders with defined appId-userId pair
*/
+ (MRMiniAppEventMainBuilder *)newEventBuilder:(NSString *)miniAppId
                                platformUserId:(NSString *)platformUserId NS_SWIFT_NAME

(
newEventBuilder(miniAppId
:platformUserId:));

/**
 @discussion Creates new mini-app event

 @return New instance of MRMiniAppEvent
*/
- (MRMiniAppEvent *)build

NS_SWIFT_NAME (build());

@end


NS_ASSUME_NONNULL_END

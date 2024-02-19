//
//  MRMiniAppCustomEventBuilder.h
//  MyTrackerSDK 3.1.5
//
//  Created by Alexander Zakatnov on 10.11.2020.
//  Copyright © 2020 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRMiniAppEventBuilder.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Builder-class for mini-app custom event
 */
@interface MRMiniAppCustomEventBuilder : MRMiniAppEventBuilder

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Sets the new value of user identifier
 
 @param customUserId New value of user identifier
 
 @return Instance of MRMiniAppUserEventBuilder
 */
- (instancetype)withCustomUserId:(nullable NSString

*)
customUserId NS_SWIFT_NAME(withCustomUserId(_
:));

/**
 @discussion Sets the new value of event parameters
 
 @param eventParams New dictionary with event parameters
 
 @return Instance of MRMiniAppCustomEventBuilder
 */
- (instancetype)withEventParams:(nullable NSDictionary

<NSString *, NSString *> *)
eventParams NS_SWIFT_NAME(withEventParams(_
:));

@end

NS_ASSUME_NONNULL_END

//
//  MRPersonalizeAPI.h
//  MyTrackerSDK 3.1.5
//
//  Created by Andrey Seredkin on 17.11.2022.
//  Copyright © 2022 VK. All rights reserved.
//

#import <Foundation/Foundation.h>

@class MRPersonalizeRequest;
@class MRPersonalizeResponse;

NS_ASSUME_NONNULL_BEGIN

@interface MRPersonalizeAPI : NSObject

/**
 @discussion Configure an array of test devices which must be used to make a debug calls to server.

 @param testDevices An array with identifiers of test devices.
*/
+ (void)configureApiWithTestDevices:(nonnull NSArray

<NSString *> *)
testDevices;

/**
 @discussion Request an Item.

 @param request Instance of MRPersonalizeRequest object.
 @param completionQueue Queue for completionHandler call, optional. Default is mainQueue
 @param completionHandler Block/Closure for callback with response.
*/
+ (void)itemWithRequest:(nonnull MRPersonalizeRequest

*)
request
        completionQueue
:(
nullable NSOperationQueue
*)
completionQueue
        completionHandler
:(nonnull void (^)(
MRPersonalizeResponse *_Nonnull
response))
completionHandler;

/**
 @discussion Request a Set.

 @param request Instance of MRPersonalizeRequest object.
 @param completionQueue Queue for completionHandler call, optional. Default is mainQueue
 @param completionHandler Block/Closure for callback with response.
*/
+ (void)setWithRequest:(nonnull MRPersonalizeRequest

*)
request
        completionQueue
:(
nullable NSOperationQueue
*)
completionQueue
        completionHandler
:(nonnull void (^)(
MRPersonalizeResponse *_Nonnull
response))
completionHandler;

/**
 @discussion Request a Ranking.

 @param request Instance of MRPersonalizeRequest object.
 @param completionQueue Queue for completionHandler call, optional. Default is mainQueue
 @param completionHandler Block/Closure for callback with response.
*/
+ (void)rankingWithRequest:(nonnull MRPersonalizeRequest

*)
request
        completionQueue
:(
nullable NSOperationQueue
*)
completionQueue
        completionHandler
:(nonnull void (^)(
MRPersonalizeResponse *_Nonnull
response))
completionHandler;

@end

NS_ASSUME_NONNULL_END

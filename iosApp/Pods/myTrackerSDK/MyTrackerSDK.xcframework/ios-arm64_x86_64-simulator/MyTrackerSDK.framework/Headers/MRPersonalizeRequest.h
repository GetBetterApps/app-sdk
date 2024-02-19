//
//  MRPersonalizeRequest.h
//  MyTrackerSDK 3.1.5
//
//  Created by Andrey Seredkin on 25.10.2022.
//  Copyright © 2022 VK. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRFinal.h>

NS_ASSUME_NONNULL_BEGIN

MR_FINAL

@interface MRPersonalizeRequest : NSObject

/**
 @discussion An array of placements, optional.
*/
@property(nonatomic, nullable, readonly) NSArray<NSString *> *placements;

/**
 @discussion User identifier.
*/
@property(nonatomic, nonnull, readonly) NSString *userId;

/**
 @discussion Reset flag.
*/
@property(nonatomic, readonly) BOOL reset;

/**
 @discussion Custom parameters, optional.
*/
@property(nonatomic, nullable, readonly) NSDictionary<NSString *, NSString *> *customParams;

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion: Creates new instance of class.

 @param placements An array of placements, optional.
 @param userId User identifier.
 @param customParams Custom parameters, optional.

 @return An instance of MRPersonalizeRequest class.
*/
- (instancetype)initWithPlacements:(nonnull NSArray

<NSString *> *)
placements
        userId
:(NSString *)
userId
        customParams
:(
nullable NSDictionary<NSString *, NSString *>
*)
customParams NS_SWIFT_NAME(init(placements
:userId:customParams:));

/**
 @discussion: Creates new instance of class.

 @param placements An array of placements, optional.
 @param userId User identifier.
 @param reset Reset flag.
 @param customParams Custom parameters, optional.

 @return An instance of MRPersonalizeRequest class.
*/
- (instancetype)initWithPlacements:(nonnull NSArray

<NSString *> *)
placements
        userId
:(NSString *)
userId
        reset
:(BOOL)
reset
        customParams
:(
nullable NSDictionary<NSString *, NSString *>
*)
customParams NS_SWIFT_NAME(init(placements
:userId:reset:customParams:));

@end

NS_ASSUME_NONNULL_END

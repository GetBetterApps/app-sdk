//
//  MRPersonalizePlacement.h
//  MyTrackerSDK 3.1.5
//
//  Created by Andrey Seredkin on 25.10.2022.
//  Copyright © 2022 VK. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRFinal.h>

@class MRPersonalizeOffer;

NS_ASSUME_NONNULL_BEGIN

MR_FINAL

@interface MRPersonalizePlacement : NSObject

/**
 @discussion Placement identifier.
*/
@property(nonatomic, nonnull, readonly) NSString *placementId;

/**
 @discussion Identifier of A/B test.
*/
@property(nonatomic, readonly) int testId;

/**
 @discussion Group identifier.
*/
@property(nonatomic, readonly) int groupId;

/**
 @discussion Offer instance.
*/
@property(nonatomic, nonnull, readonly) MRPersonalizeOffer *offer;

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion: Creates new instance of class.

 @param placementId Placement identifier.
 @param testId Identifier of A/B test.
 @param groupId Group identifier.
 @param offer Offer instance.

 @return An instance of MRPersonalizePlacement class.
*/
- (instancetype)initWithPlacementId:(nonnull NSString

*)
placementId
        testId
:(int)
testId
        groupId
:(int)
groupId
        offer
:(
nonnull MRPersonalizeOffer
*)
offer NS_SWIFT_NAME(init(placementId
:testId:groupId:offer:));

@end

NS_ASSUME_NONNULL_END

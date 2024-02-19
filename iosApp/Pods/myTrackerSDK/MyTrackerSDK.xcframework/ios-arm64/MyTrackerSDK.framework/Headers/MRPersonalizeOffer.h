//
//  MRPersonalizeOffer.h
//  MyTrackerSDK 3.1.5
//
//  Created by Andrey Seredkin on 25.10.2022.
//  Copyright © 2022 VK. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRFinal.h>

@class MRPersonalizeItem;

NS_ASSUME_NONNULL_BEGIN

MR_FINAL

@interface MRPersonalizeOffer : NSObject

/**
 @discussion Offer identifier.
*/
@property(nonatomic, readonly) int offerId;

/**
 @discussion An array of MRPersonalizeItem items.
*/
@property(nonatomic, nonnull, readonly) NSArray<MRPersonalizeItem *> *items;

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion: Creates new instance of class.

 @param offerId Offer identifier.
 @param items An array of MRPersonalizeItem items.

 @return An instance of MRPersonalizeOffer class.
*/
- (instancetype)initWithOfferId:(int)offerId
                          items:(nonnull NSArray

<MRPersonalizeItem *> *)
items NS_SWIFT_NAME(init(offerId
:items:));

@end

NS_ASSUME_NONNULL_END

//
//  MROffer.h
//  MyTrackerSDK 3.1.5
//
//  Created by Alexander Zakatnov on 27.04.2021.
//  Copyright © 2021 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRFinal.h>

NS_ASSUME_NONNULL_BEGIN

@interface MROffer : NSObject

@property(nonatomic, readonly) NSString *placementId
NS_SWIFT_NAME(placementId);

@property(nonatomic, readonly) NSString *itemId
NS_SWIFT_NAME(itemId);

@property(nonatomic, readonly) double price
NS_SWIFT_NAME(price);

@property(nonatomic, readonly) double discountPrice
NS_SWIFT_NAME(discountPrice);

@property(nonatomic, readonly) int value
NS_SWIFT_NAME(value);

@property(nonatomic, readonly) int discountValue
NS_SWIFT_NAME(discountValue);

@property(nonatomic, readonly) int testId
NS_SWIFT_NAME(testId);

@property(nonatomic, readonly) int splitId
NS_SWIFT_NAME(splitId);

@end

NS_ASSUME_NONNULL_END

//
//  MRPersonalizeItem.h
//  MyTrackerSDK 3.1.5
//
//  Created by Andrey Seredkin on 25.10.2022.
//  Copyright © 2022 VK. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRFinal.h>

NS_ASSUME_NONNULL_BEGIN

MR_FINAL

@interface MRPersonalizeItem : NSObject

/**
 @discussion SKU value.
*/
@property(nonatomic, nonnull, readonly) NSString *sku;

/**
 @discussion Item payload.
*/
@property(nonatomic, nonnull, readonly) NSString *payload;

/**
 @discussion Item price.
*/
@property(nonatomic, readonly) double price;

/**
 @discussion Item price with discount.
*/
@property(nonatomic, readonly) int discountPrice;

/**
 @discussion Item value.
*/
@property(nonatomic, readonly) double value;

/**
 @discussion Item value with discount.
*/
@property(nonatomic, readonly) int discountValue;

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion: Creates new instance of class.

 @param sku SKU value.
 @param payload Item payload.
 @param price Item price.
 @param discountPrice Item price with discount.
 @param value Item value.
 @param discountValue Item value with discount.

 @return An instance of MRPersonalizeItem class.
*/
- (instancetype)initWithSku:(nonnull NSString

*)
sku
        payload
:(
nonnull NSString
*)
payload
        price
:(double)
price
        discountPrice
:(int)
discountPrice
        value
:(double)
value
        discountValue
:(int)
discountValue NS_SWIFT_NAME(init(sku
:payload:price:discountPrice:value:discountValue:));

@end

NS_ASSUME_NONNULL_END

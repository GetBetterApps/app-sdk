//
//  MTRGNativePromoCard.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 18.10.16.
//  Copyright © 2016 Mail.ru. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTargetSDK/MTRGImageData.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Data model for a promo card.
 */
@interface MTRGNativePromoCard : NSObject

/**
 @discussion Title for the card.
 */
@property(nonatomic, readonly, copy, nullable) NSString *title;

/**
 @discussion Description text for the card.
 */
@property(nonatomic, readonly, copy, nullable) NSString *descriptionText;

/**
 @discussion CTA text for the card.
 */
@property(nonatomic, readonly, copy, nullable) NSString *ctaText;

/**
 @discussion Image data for the card.
 */
@property(nonatomic, readonly, nullable) MTRGImageData *image;

/**
 @discussion Discount text for the card.
 */
@property(nonatomic, readonly, nullable) NSString *discount;

@end

NS_ASSUME_NONNULL_END

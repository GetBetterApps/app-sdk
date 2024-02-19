//
//  MTRGMediationNativeAdBanner.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 18/04/2019.
//  Copyright © 2019 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTargetSDK/MTRGNavigationType.h>

@class MTRGImageData;
@class MTRGNativePromoCard;
@class MTRGNativePromoBanner;
@class MTRGNativeBanner;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class for native ad banner.
 */
@interface MTRGMediationNativeAdBanner : NSObject

/**
 @discussion Advertising label of the banner.
 */
@property(nonatomic, copy, nullable) NSString *advertisingLabel;

/**
 @discussion Age restrictions.
 */
@property(nonatomic, copy, nullable) NSString *ageRestrictions;

/**
 @discussion Title of the banner.
 */
@property(nonatomic, copy, nullable) NSString *title;

/**
 @discussion Description of the banner.
 */
@property(nonatomic, copy, nullable) NSString *descriptionText;

/**
 @discussion Disclaimer of the banner.
 */
@property(nonatomic, copy, nullable) NSString *disclaimer;

/**
 @discussion Category of the banner.
 */
@property(nonatomic, copy, nullable) NSString *category;

/**
 @discussion Subcategory of the banner.
 */
@property(nonatomic, copy, nullable) NSString *subcategory;

/**
 @discussion Domain of the banner.
 */
@property(nonatomic, copy, nullable) NSString *domain;

/**
 @discussion CTA text of the banner.
 */
@property(nonatomic, copy, nullable) NSString *ctaText;

/**
 @discussion Rating  of the banner.
 */
@property(nonatomic, nullable) NSNumber *rating;

/**
 @discussion Votes of the banner.
 */
@property(nonatomic) NSUInteger votes;

/**
 @discussion Type of navigation of the banner.
 */
@property(nonatomic) MTRGNavigationType navigationType;

/**
 @discussion Icon of the banner.
 */
@property(nonatomic, nullable) MTRGImageData *icon;

/**
 @discussion Image of the banner.
 */
@property(nonatomic, nullable) MTRGImageData *image;

/**
 @discussion Array of cards of the banner.
 */
@property(nonatomic) NSMutableArray<MTRGNativePromoCard *> *cards;

/**
 @discussion Flag shows video exist or not.
 */
@property(nonatomic) BOOL hasVideo;

/**
 @discussion AdChoices image.
 */
@property(nonatomic, nullable) MTRGImageData *adChoicesIcon;

/**
 @discussion If banner has AdChoices.
 */
@property(nonatomic) BOOL hasAdChoices;

/**
 @discussion Creates native promo banner.
 
 @return Promo banner.
 */
- (MTRGNativePromoBanner *)createNativePromoBanner;

/**
 @discussion Creates native banner.
 
 @return Instance of MTRGNativeBanner.
 */
- (MTRGNativeBanner *)createNativeBanner;

@end

NS_ASSUME_NONNULL_END

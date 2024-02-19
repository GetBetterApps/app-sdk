//
//  MTRGNativeViewsFactory.h
//  myTargetSDK 5.20.2
//
//  Created by Anton Bulankin on 17.11.14.
//  Copyright (c) 2014 Mail.ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreGraphics/CGBase.h>

@class MTRGNativeBannerAdView;
@class MTRGNativeAdView;
@class MTRGMediaAdView;
@class MTRGIconAdView;
@class MTRGNativeCardAdView;
@class MTRGPromoCardCollectionView;
@class MTRGAdChoicesView;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Views factory class.
 */
@interface MTRGNativeViewsFactory : NSObject

/**
 @discussion Creates banner ad view.
 
 @return Instance of MTRGNativeBannerAdView.
 */
+ (MTRGNativeBannerAdView *)createNativeBannerAdView;

/**
 @discussion Creates  ad view.
 
 @return Instance of MTRGNativeAdView.
 */
+ (MTRGNativeAdView *)createNativeAdView;

/**
 @discussion Creates  ad view with extended card.
 
 @return Instance of MTRGNativeAdView.
 */
+ (MTRGNativeAdView *)createNativeAdViewWithExtendedCard;

/**
 @discussion Creates media ad view.
 
 @return Instance of MTRGMediaAdView.
 */
+ (MTRGMediaAdView *)createMediaAdView;

/**
 @discussion Creates icon ad view.
 
 @return Instance of MTRGIconAdView.
 */
+ (MTRGIconAdView *)createIconAdView;

/**
 @discussion Creates native card ad view.
 
 @return Instance of MTRGNativeCardAdView.
 */
+ (MTRGNativeCardAdView *)createNativeCardAdView;

/**
 @discussion Creates promo card collection view.
 
 @return Instance of MTRGPromoCardCollectionView.
 */
+ (MTRGPromoCardCollectionView *)createPromoCardCollectionView;

/**
 @discussion Creates promo card collection view with additional options.
 
 @param cardScaleFactor Scale for cards (relation of card's width to collection width).
 @param cardSpacing Spacing between cards.

 @return Instance of MTRGPromoCardCollectionView.
 */
+ (MTRGPromoCardCollectionView *)createPromoCardCollectionViewWithCardScaleFactor:(CGFloat)cardScaleFactor
                                                                      cardSpacing:(CGFloat)cardSpacing;

/**
 @discussion Creates ad choices view.
 
 @return Instance of MTRGAdChoicesView.
 */
+ (MTRGAdChoicesView *)createAdChoicesView;

@end

NS_ASSUME_NONNULL_END

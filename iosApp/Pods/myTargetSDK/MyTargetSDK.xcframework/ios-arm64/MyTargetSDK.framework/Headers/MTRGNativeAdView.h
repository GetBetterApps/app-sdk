//
//  MTRGNativeAdView.h
//  myTargetSDK 5.20.2
//
//  Created by Anton Bulankin on 05.12.14.
//  Copyright (c) 2014 Mail.ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

@class MTRGPromoCardCollectionView;
@class MTRGNativePromoBanner;
@class MTRGStarsRatingLabel;
@class MTRGMediaAdView;
@class MTRGIconAdView;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class for native ad view.
 */
@interface MTRGNativeAdView : UIView

/**
 @discussion Native promo banner.
 */
@property(nonatomic, nullable) MTRGNativePromoBanner *banner;

/**
 @discussion Background color of the view.
 */
@property(nonatomic, nullable) UIColor *backgroundColor;

/**
 @discussion Age restrictions label for the view.
 */
@property(nonatomic, readonly) UILabel *ageRestrictionsLabel;

/**
 @discussion Label for the ad.
 */
@property(nonatomic, readonly) UILabel *adLabel;

/**
 @discussion Label for a title.
 */
@property(nonatomic, readonly) UILabel *titleLabel;

/**
 @discussion Label for a description.
 */
@property(nonatomic, readonly) UILabel *descriptionLabel;

/**
 @discussion Icon view.
 */
@property(nonatomic, readonly) MTRGIconAdView *iconAdView;

/**
 @discussion Media view.
 */
@property(nonatomic, readonly) MTRGMediaAdView *mediaAdView;

/**
 @discussion View with cards.
 */
@property(nonatomic, readonly, nullable) MTRGPromoCardCollectionView *cardCollectionView;

/**
 @discussion Label with a domain.
 */
@property(nonatomic, readonly) UILabel *domainLabel;

/**
 @discussion Label with a category.
 */
@property(nonatomic, readonly) UILabel *categoryLabel;

/**
 @discussion Label with a disclaimer.
 */
@property(nonatomic, readonly) UILabel *disclaimerLabel;

/**
 @discussion Rating stars label.
 */
@property(nonatomic, readonly) MTRGStarsRatingLabel *ratingStarsLabel;

/**
 @discussion Label with votes.
 */
@property(nonatomic, readonly) UILabel *votesLabel;

/**
 @discussion View for a button.
 */
@property(nonatomic, readonly) UIView *buttonView;

/**
 @discussion Special label for a button.
 */
@property(nonatomic, readonly) UILabel *buttonToLabel;

/**
 @discussion Content margins.
 */
@property(nonatomic) UIEdgeInsets contentMargins;

/**
 @discussion Ad label margins.
 */
@property(nonatomic) UIEdgeInsets adLabelMargins;

/**
 @discussion Age restrictions margins.
 */
@property(nonatomic) UIEdgeInsets ageRestrictionsMargins;

/**
 @discussion Title margins.
 */
@property(nonatomic) UIEdgeInsets titleMargins;

/**
 @discussion Domain margins.
 */
@property(nonatomic) UIEdgeInsets domainMargins;

/**
 @discussion Category margins.
 */
@property(nonatomic) UIEdgeInsets categoryMargins;

/**
 @discussion Description margins.
 */
@property(nonatomic) UIEdgeInsets descriptionMargins;

/**
 @discussion Disclaimer margins.
 */
@property(nonatomic) UIEdgeInsets disclaimerMargins;

/**
 @discussion Image margins.
 */
@property(nonatomic) UIEdgeInsets imageMargins;

/**
 @discussion Icon margins.
 */
@property(nonatomic) UIEdgeInsets iconMargins;

/**
 @discussion Rating starts margins.
 */
@property(nonatomic) UIEdgeInsets ratingStarsMargins;

/**
 @discussion Votes margins.
 */
@property(nonatomic) UIEdgeInsets votesMargins;

/**
 @discussion Button margins.
 */
@property(nonatomic) UIEdgeInsets buttonMargins;

/**
 @discussion Button caption margins.
 */
@property(nonatomic) UIEdgeInsets buttonCaptionMargins;

/**
 @discussion Static constructor. Creates instance of the class.
 
 @return Instance of the class.
 */
+ (instancetype)create;

/**
 @discussion Static constructor. Creates instance of the class with extended card.
 
 @return Instance of the class.
 */
+ (instancetype)createWithExtendedCard;

@end

NS_ASSUME_NONNULL_END

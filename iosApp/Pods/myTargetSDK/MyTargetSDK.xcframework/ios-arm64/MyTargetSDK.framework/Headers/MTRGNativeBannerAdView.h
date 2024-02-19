//
//  MTRGNativeBannerAdView.h
//  myTargetSDK 5.20.2
//
//  Created by Anton Bulankin on 05.12.14.
//  Copyright (c) 2014 Mail.ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

@class MTRGNativeBanner;
@class MTRGStarsRatingLabel;

@class MTRGIconAdView;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Native banner ad view class.
 */
@interface MTRGNativeBannerAdView : UIView

/**
 @discussion Banner for the view.
 */
@property(nonatomic, nullable) MTRGNativeBanner *banner;

/**
 @discussion Background color of the view.
 */
@property(nonatomic, nullable) UIColor *backgroundColor;

/**
 @discussion Age restrictions label for the view.
 */
@property(nonatomic, readonly, nullable) UILabel *ageRestrictionsLabel;

/**
 @discussion Label for the ad.
 */
@property(nonatomic, readonly, nullable) UILabel *adLabel;

/**
 @discussion Icon view.
 */
@property(nonatomic, readonly, nullable) MTRGIconAdView *iconAdView;

/**
 @discussion Label with a domain.
 */
@property(nonatomic, readonly, nullable) UILabel *domainLabel;

/**
 @discussion Label with a disclaimer.
 */
@property(nonatomic, readonly, nullable) UILabel *disclaimerLabel;

/**
 @discussion Rating stars label.
 */
@property(nonatomic, readonly, nullable) MTRGStarsRatingLabel *ratingStarsLabel;

/**
 @discussion Label with votes.
 */
@property(nonatomic, readonly, nullable) UILabel *votesLabel;

/**
 @discussion View for a button.
 */
@property(nonatomic, readonly, nullable) UIView *buttonView;

/**
 @discussion Special label for a button.
 */
@property(nonatomic, readonly, nullable) UILabel *buttonToLabel;

/**
 @discussion Label for a title.
 */
@property(nonatomic, readonly, nullable) UILabel *titleLabel;

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
 @discussion Disclaimer margins.
 */
@property(nonatomic) UIEdgeInsets disclaimerMargins;

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

@end

NS_ASSUME_NONNULL_END

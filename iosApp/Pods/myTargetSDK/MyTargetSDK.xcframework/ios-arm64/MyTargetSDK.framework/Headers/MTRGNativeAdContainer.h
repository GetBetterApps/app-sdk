//
//  MTRGNativeAdContainer.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 21/05/2019.
//  Copyright © 2019 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Container for the native ad.
 */
@interface MTRGNativeAdContainer : UIView

/**
 @discussion Ad view.
 */
@property(nonatomic, nullable) UIView *adView;

/**
 @discussion Advertising view.
 */
@property(nonatomic, nullable) UIView *advertisingView;

/**
 @discussion View for displaying age restrictions.
 */
@property(nonatomic, nullable) UIView *ageRestrictionsView;

/**
 @discussion View with title.
 */
@property(nonatomic, nullable) UIView *titleView;

/**
 @discussion View with description.
 */
@property(nonatomic, nullable) UIView *descriptionView;

/**
 @discussion View with category.
 */
@property(nonatomic, nullable) UIView *categoryView;

/**
 @discussion View with icon.
 */
@property(nonatomic, nullable) UIView *iconView;

/**
 @discussion View with media.
 */
@property(nonatomic, nullable) UIView *mediaView;

/**
 @discussion View with rating.
 */
@property(nonatomic, nullable) UIView *ratingView;

/**
 @discussion View with votes.
 */
@property(nonatomic, nullable) UIView *votesView;

/**
 @discussion View with domain.
 */
@property(nonatomic, nullable) UIView *domainView;

/**
 @discussion View with disclaimer.
 */
@property(nonatomic, nullable) UIView *disclaimerView;

/**
 @discussion View with CTA.
 */
@property(nonatomic, nullable) UIView *ctaView;

/**
 @discussion Static constructor. Creates instance of the class.
 
 @param adView View for the ad.
 
 @return Instance of the class.
 */
+ (instancetype)createWithAdView:(UIView *)adView;

@end

NS_ASSUME_NONNULL_END

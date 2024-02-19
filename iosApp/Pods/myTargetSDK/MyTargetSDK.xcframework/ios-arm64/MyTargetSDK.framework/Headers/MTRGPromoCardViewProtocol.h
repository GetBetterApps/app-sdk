//
//  MTRGPromoCardViewProtocol.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 20.10.16.
//  Copyright © 2016 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@class MTRGMediaAdView;

/**
 @discussion Protocol for promo card views.
 */
@protocol MTRGPromoCardViewProtocol <NSObject>

@required

/**
 @discussion Label for a title.
 */
@property(nonatomic, readonly) UILabel *titleLabel;

/**
 @discussion Label for a description.
 */
@property(nonatomic, readonly) UILabel *descriptionLabel;

/**
 @discussion Label for a CTA button.
 */
@property(nonatomic, readonly) UILabel *ctaButtonLabel;

/**
 @discussion Media view.
 */
@property(nonatomic, readonly) MTRGMediaAdView *mediaAdView;

/**
 @discussion Returns height of the card for a width.
 
 @param width Width of the card.
 
 @return Calculated height.
 */
- (CGFloat)heightWithCardWidth:(CGFloat)width;

@end

NS_ASSUME_NONNULL_END

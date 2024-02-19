//
//  MTRGNativeCardAdView.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 20.10.16.
//  Copyright © 2016 Mail.ru. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MyTargetSDK/MTRGPromoCardViewProtocol.h>
#import <MyTargetSDK/MTRGMediaAdView.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class for native card ad view.
 */
@interface MTRGNativeCardAdView : UICollectionViewCell <MTRGPromoCardViewProtocol>

/**
 @discussion Label for a title.
 */
@property(nonatomic, readonly) UILabel *titleLabel;

/**
 @discussion Label for a description.
 */
@property(nonatomic, readonly) UILabel *descriptionLabel;

/**
 @discussion CTA button label.
 */
@property(nonatomic, readonly) UILabel *ctaButtonLabel;

/**
 @discussion Media view.
 */
@property(nonatomic, readonly) MTRGMediaAdView *mediaAdView;

/**
 @discussion Static constructor. Creates instance of the class.
 
 @return Instance of the class.
 */
+ (instancetype)create;

/**
 @discussion Returns height of the card for a width.
 
 @param width Width of the card.
 
 @return Calculated height.
 */
- (CGFloat)heightWithCardWidth:(CGFloat)width;

@end

NS_ASSUME_NONNULL_END

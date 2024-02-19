//
//  MTRGStarsRatingLabel.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 27.01.17.
//  Copyright © 2017 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class to create stars rating label. Inherits UILabel
 */
IB_DESIGNABLE

@interface MTRGStarsRatingLabel : UILabel

/**
 @discussion Rating number
 */
@property(nonatomic, nullable) IBInspectable NSNumber
*
rating;

/**
 @discussion Static constructor of MTRGStarsRatingLabel
 
 @param rating Defines number of stars
 
 @return Instance of MTRGStarsRatingLabel
 */
+ (instancetype)ratingLabelWithRating:(NSNumber *)rating;

/**
 @discussion Constructor of MTRGStarsRatingLabel
 
 @param rating Defines number of stars
 
 @return Instance of MTRGStarsRatingLabel
 */
- (instancetype)initWithRating:(NSNumber *)rating;

@end

NS_ASSUME_NONNULL_END

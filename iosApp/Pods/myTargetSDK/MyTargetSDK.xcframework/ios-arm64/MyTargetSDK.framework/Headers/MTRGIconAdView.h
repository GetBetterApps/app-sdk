//
//  MTRGIconAdView.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 18/02/2020.
//  Copyright © 2020 Mail.ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion View for an icon.
 */
@interface MTRGIconAdView : UIView

/**
 @discussion Image view for the icon.
 */
@property(nonatomic, readonly) UIImageView *imageView;

/**
 @discussion Static constructor. Creates instance of the class.
 
 @return Instance of MTRGIconAdView.
 */
+ (instancetype)create;

@end

NS_ASSUME_NONNULL_END

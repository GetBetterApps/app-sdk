//
//  MTRGAdChoicesView.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 10.03.2021.
//  Copyright © 2021 Mail.ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class to create ad choices view. Inherits UIView
 */
@interface MTRGAdChoicesView : UIView

/**
 @discussion UIImageView to display in Ad Choices View
 */
@property(nonatomic, readonly) UIImageView *imageView;

/**
 @discussion Static constructor
 
 @return Instance of MTRGAdChoicesView
 */
+ (instancetype)create;

@end

NS_ASSUME_NONNULL_END

//
//  MTRGAppwallBannerAdView.h
//  myTargetSDK 5.20.2
//
//  Created by Anton Bulankin on 15.01.15.
//  Copyright (c) 2015 Mail.ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

@class MTRGNativeAppwallBanner;

NS_ASSUME_NONNULL_BEGIN

@class MTRGAppwallBannerAdView;

@protocol MTRGAppwallBannerAdViewDelegate <NSObject>

- (void)appwallBannerAdViewOnClickWithView:(MTRGAppwallBannerAdView *)bannerAdView;

- (void)appwallBannerAdViewOnCancelSelectWithView:(MTRGAppwallBannerAdView *)bannerAdView;

@optional

- (BOOL)appwallBannerAdViewAllowStartSelect:(MTRGAppwallBannerAdView *)bannerAdView;

@end

/**
 @discussion Class Appwall banner ad view
 */
@interface MTRGAppwallBannerAdView : UIView

/**
 @discussion Delegate for MTRGAppwallBannerAdView. Must implements MTRGAppwallBannerAdViewDelegate protocol.
 */
@property(nonatomic, weak, nullable) id <MTRGAppwallBannerAdViewDelegate> delegate;

/**
 @discussion Instance of MTRGNativeAppwallBanner. Contains info for banner ad view.
 */
@property(nonatomic, nullable) MTRGNativeAppwallBanner *appwallBanner;

/**
 @discussion Describes title margins
 */
@property(nonatomic) UIEdgeInsets titleMargins;

/**
 @discussion Describes description margins.
 */
@property(nonatomic) UIEdgeInsets descriptionMargins;

/**
 @discussion Describes icon margins.
 */
@property(nonatomic) UIEdgeInsets iconMargins;

/**
 @discussion Describes rating stars margins.
 */
@property(nonatomic) UIEdgeInsets ratingStarsMargins;

/**
 @discussion Describes votes margins.
 */
@property(nonatomic) UIEdgeInsets votesMargins;

/**
 @discussion Describes  go to app margins
 */
@property(nonatomic) UIEdgeInsets gotoAppMargins;

/**
 @discussion Describes cross notification margins.
 */
@property(nonatomic) UIEdgeInsets crossNotifMargins;

/**
 @discussion Describes  coins view margins.
 */
@property(nonatomic) UIEdgeInsets coinsViewMargins;

/**
 @discussion Describes coins text margins.
 */
@property(nonatomic) UIEdgeInsets coinsTextMargins;

/**
 @discussion Describes paddings.
 */
@property(nonatomic) UIEdgeInsets paddings;

/**
 @discussion Describes  touch color.
 */
@property(nonatomic, nullable) UIColor *touchColor;

/**
 @discussion Describes normal color.
 */
@property(nonatomic, nullable) UIColor *normalColor;

/**
 @discussion Describes  icon size.
 */
@property(nonatomic) CGSize iconSize;

/**
 @discussion Describes rating size.
 */
@property(nonatomic) CGSize ratingSize;

/**
 @discussion Describes coin view size.
 */
@property(nonatomic) CGSize coinViewSize;

/**
 @discussion Describes coin size.
 */
@property(nonatomic) CGSize coinSize;

/**
 @discussion Describes  go to app size.
 */
@property(nonatomic) CGSize gotoAppSize;

/**
 @discussion Describes  status image size.
 */
@property(nonatomic) CGSize statusImageSize;

/**
 @discussion Describes bubble size.
 */
@property(nonatomic) CGSize bubbleSize;

/**
 @discussion Describes cross notification icon size.
 */
@property(nonatomic) CGSize crossNotifIconSize;

/**
 @discussion Describes bubble position.
 */
@property(nonatomic) CGPoint bubblePosition;

/**
 @discussion Describes title font.
 */
@property(nonatomic, nullable) UIFont *titleFont;

/**
 @discussion Describes description's font.
 */
@property(nonatomic, nullable) UIFont *descriptionFont;

/**
 @discussion Describes votes font.
 */
@property(nonatomic, nullable) UIFont *votesFont;

/**
 @discussion Describes coin's font.
 */
@property(nonatomic, nullable) UIFont *coinFont;

/**
 @discussion Describes number of lines of description.
 */
@property(nonatomic) NSInteger descriptionNumberOfLines;

/**
 @discussion Describes line break mode of description.
 */
@property(nonatomic) NSLineBreakMode descriptionLineBreakMode;

/**
 @discussion Describes title's color.
 */
@property(nonatomic, nullable) UIColor *titleColor
UI_APPEARANCE_SELECTOR;

/**
 @discussion Describes description's color.
 */
@property(nonatomic, nullable) UIColor *descriptionColor
UI_APPEARANCE_SELECTOR;

/**
 @discussion Describes votes color
 */
@property(nonatomic, nullable) UIColor *votesColor
UI_APPEARANCE_SELECTOR;

/**
 @discussion Show or not top border.
 */
@property(nonatomic) BOOL showTopBorder;

/**
 @discussion Show or not go to app icon.
 */
@property(nonatomic) BOOL showGotoAppIcon;

/**
 @discussion Show or not rating.
 */
@property(nonatomic) BOOL showRating;

/**
 @discussion Show or not status icon.
 */
@property(nonatomic) BOOL showStatusIcon;

/**
 @discussion Show or not bubble icon.
 */
@property(nonatomic) BOOL showBubbleIcon;

/**
 @discussion Show or not  icons.
 */
@property(nonatomic) BOOL showCoins;

/**
 @discussion Show or not cross notification.
 */
@property(nonatomic) BOOL showCrossNotifIcon;

/**
 @discussion Static constructor.
 
 @return Instance of MTRGAppwallBannerAdView.
 */
+ (instancetype)create;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Sets appwall banner.
 
 @param appwallBanner Instance of MTRGNativeAppwallBanner
 */
- (void)setAppwallBanner:(MTRGNativeAppwallBanner *)appwallBanner;

/**
 @discussion Sets fixed width for banner.
 
 @param width Fixed width for banner.
 */
- (void)setFixedWidth:(CGFloat)width;

/**
 @discussion Removes notification.
 */
- (void)removeNotification;

@end

NS_ASSUME_NONNULL_END

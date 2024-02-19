//
//  MTRGNativeAppwallBanner.h
//  myTargetSDK 5.20.2
//
// Created by Timur on 4/12/18.
// Copyright (c) 2018 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>

@class MTRGImageData;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class for native appwall banner.
 */
@interface MTRGNativeAppwallBanner : NSObject

/**
 @discussion The banner's id.
 */
@property(nonatomic, readonly, copy) NSString *bannerId;

/**
 @discussion The bundle identifier.
 */
@property(nonatomic, readonly, nullable) NSString *bundleId;

/**
 @discussion The banner's status.
 */
@property(nonatomic, readonly, copy, nullable) NSString *status;

/**
 @discussion The banner's title.
 */
@property(nonatomic, readonly, copy, nullable) NSString *title;

/**
 @discussion The banner's description text.
 */
@property(nonatomic, readonly, copy, nullable) NSString *descriptionText;

/**
 @discussion The banner's paid type.
 */
@property(nonatomic, readonly, copy, nullable) NSString *paidType;

/**
 @discussion The banner's MRGS id.
 */
@property(nonatomic, readonly, copy, nullable) NSString *mrgsId;

/**
 @discussion Subitem's flag.
 */
@property(nonatomic, readonly) BOOL subitem;

/**
 @discussion Flag determines that app was installed.
 */
@property(nonatomic, readonly) BOOL isAppInstalled;

/**
 @discussion Flag of main.
 */
@property(nonatomic, readonly) BOOL main;

/**
 @discussion Flag determines that banner requiers category highlights.
 */
@property(nonatomic, readonly) BOOL requireCategoryHighlight;

/**
 @discussion Banner's flag.
 */
@property(nonatomic, readonly) BOOL banner;

/**
 @discussion Flag determines that banner requires WiFi.
 */
@property(nonatomic, readonly) BOOL requireWifi;

/**
 @discussion Flag determines that item highlights.
 */
@property(nonatomic, readonly) BOOL itemHighlight;

/**
 @discussion Banner's rating.
 */
@property(nonatomic, readonly, nullable) NSNumber *rating;

/**
 @discussion Banner's votes number.
 */
@property(nonatomic, readonly) NSUInteger votes;

/**
 @discussion Banner's coins number.
 */
@property(nonatomic, readonly) NSUInteger coins;

/**
 @discussion Coins background color.
 */
@property(nonatomic, readonly, nullable) UIColor *coinsBgColor;

/**
 @discussion Coins text color.
 */
@property(nonatomic, readonly, nullable) UIColor *coinsTextColor;

/**
 @discussion Icon's image data.
 */
@property(nonatomic, readonly, nullable) MTRGImageData *icon;

/**
 @discussion Status icon image data.
 */
@property(nonatomic, readonly, nullable) MTRGImageData *statusIcon;

/**
 @discussion Coins icon's image data.
 */
@property(nonatomic, readonly, nullable) MTRGImageData *coinsIcon;

/**
 @discussion Cross notification icon's image data.
 */
@property(nonatomic, readonly, nullable) MTRGImageData *crossNotifIcon;

/**
 @discussion Bubble icon's image data.
 */
@property(nonatomic, readonly, nullable) MTRGImageData *bubbleIcon;

/**
 @discussion 'Goto' app icon's image data.
 */
@property(nonatomic, readonly, nullable) MTRGImageData *gotoAppIcon;

/**
 @discussion Item highlight icon's image data.
 */
@property(nonatomic, readonly, nullable) MTRGImageData *itemHighlightIcon;

/**
 @discussion Notification flag.
 */
@property(nonatomic) BOOL hasNotification;

@end

NS_ASSUME_NONNULL_END

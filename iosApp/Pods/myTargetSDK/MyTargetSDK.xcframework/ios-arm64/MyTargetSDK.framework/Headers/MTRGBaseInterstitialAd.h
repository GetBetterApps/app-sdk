//
//  MTRGBaseInterstitialAd.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 31.07.2020.
//  Copyright © 2020 Mail.ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MyTargetSDK/MTRGBaseAd.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Base class for interstitial ad.
 */
@interface MTRGBaseInterstitialAd : MTRGBaseAd

/**
 @discussion Flag determines mediation is enabled or not.
 */
@property(nonatomic) BOOL mediationEnabled;

/**
 @discussion Source of the ad.
 */
@property(nonatomic, readonly, nullable) NSString *adSource;

/**
 @discussion Priority of the source.
 */
@property(nonatomic, readonly) float adSourcePriority;

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion Load the ad.
 */
- (void)load;

/**
 @discussion Load the ad from the bid.
 
 @param bidId Bid identifier.
 */
- (void)loadFromBid:(NSString *)bidId;

/**
 @discussion Shows the ad with controller.
 
 @param controller Current controller.
 */
- (void)showWithController:(UIViewController *)controller;

/**
 @discussion Closes the ad.
 */
- (void)close;

@end

NS_ASSUME_NONNULL_END

//
//  BigoNativeBannerAdLoader.h
//  BigoADS
//
//  Created by Chongtai H on 2021/7/20.
//

#import <Foundation/Foundation.h>
#import "BigoBannerAdLoader.h"
#import "BigoNativeAdLoader.h"
#import "BigoAd.h"
#import "BigoAdLoader.h"
#import "BigoNativeBannerAdRequest.h"

NS_ASSUME_NONNULL_BEGIN

@protocol BigoNativeBannerAdLoaderDelegate <BigoNativeAdLoaderDelegate, BigoBannerAdLoaderDelegate>

@optional
- (void)onNativeBannerAdLoadError:(BigoAdError *)error;

- (void)onNativeAdLoadError:(BigoAdError *)error NS_UNAVAILABLE;

- (void)onBannerAdLoadError:(BigoAdError *)error NS_UNAVAILABLE;

@end

@interface BigoNativeBannerAdLoader : BigoAdLoader<BigoAd *, BigoNativeBannerAdRequest *>

- (instancetype)initWithNativeBannerAdLoaderDelegate:(id <BigoNativeBannerAdLoaderDelegate>)delegate;
@end

NS_ASSUME_NONNULL_END

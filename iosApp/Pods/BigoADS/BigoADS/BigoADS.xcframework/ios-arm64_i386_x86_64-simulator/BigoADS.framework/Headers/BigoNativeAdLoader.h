//
//  BigoNativeAdLoader.h
//  BigoADS
//
//  Created by 蔡庆敏 on 2021/7/8.
//

#import "BigoAdLoader.h"
#import "BigoNativeAd.h"
#import "BigoNativeAdRequest.h"

NS_ASSUME_NONNULL_BEGIN

@protocol BigoNativeAdLoaderDelegate <NSObject>

- (void)onNativeAdLoaded:(BigoNativeAd *)ad;

@optional
- (void)onNativeAdLoadError:(BigoAdError *)error;

@end

@interface BigoNativeAdLoader : BigoAdLoader<BigoNativeAd *, BigoNativeAdRequest *>

- (instancetype)initWithNativeAdLoaderDelegate:(id <BigoNativeAdLoaderDelegate>)delegate;

@end

NS_ASSUME_NONNULL_END

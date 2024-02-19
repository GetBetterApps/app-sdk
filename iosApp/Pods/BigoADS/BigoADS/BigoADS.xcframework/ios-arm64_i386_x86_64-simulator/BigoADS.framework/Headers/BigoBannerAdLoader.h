//
//  BigoBannerAdLoader.h
//  BigoADS
//
//  Created by 蔡庆敏 on 2021/5/17.
//

#import <Foundation/Foundation.h>
#import "BigoBannerAd.h"
#import "BigoAdLoader.h"
#import "BigoBannerAdRequest.h"

NS_ASSUME_NONNULL_BEGIN

@protocol BigoBannerAdLoaderDelegate <NSObject>

- (void)onBannerAdLoaded:(BigoBannerAd *)ad;

@optional
- (void)onBannerAdLoadError:(BigoAdError *)error;

@end


@interface BigoBannerAdLoader : BigoAdLoader<BigoBannerAd *, BigoBannerAdRequest *>

- (instancetype)initWithBannerAdLoaderDelegate:(id <BigoBannerAdLoaderDelegate>)delegate;

@end

NS_ASSUME_NONNULL_END

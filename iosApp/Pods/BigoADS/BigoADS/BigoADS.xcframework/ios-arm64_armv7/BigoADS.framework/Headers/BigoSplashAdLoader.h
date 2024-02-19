//
//  BigoSplashAdLoader.h
//  BigoADS
//
//  Created by cai on 2022/5/10.
//

#import "BigoAdLoader.h"
#import "BigoSplashAd.h"
#import "BigoSplashAdRequest.h"

NS_ASSUME_NONNULL_BEGIN

@protocol BigoSplashAdLoaderDelegate <NSObject>

- (void)onSplashAdLoaded:(BigoSplashAd *)ad;

@optional
- (void)onSplashAdLoadError:(BigoAdError *)error;

@end

@interface BigoSplashAdLoader : BigoAdLoader <BigoSplashAd *, BigoSplashAdRequest *>

- (instancetype)initWithSplashAdLoaderDelegate:(id <BigoSplashAdLoaderDelegate>)delegate;

@end

NS_ASSUME_NONNULL_END

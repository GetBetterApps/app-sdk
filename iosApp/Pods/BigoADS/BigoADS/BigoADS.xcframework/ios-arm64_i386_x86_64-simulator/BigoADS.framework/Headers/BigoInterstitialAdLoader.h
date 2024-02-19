//
//  BigoInterstitialAdLoader.h
//  BigoADS
//
//  Created by 李剑锋 on 2021/7/13.
//

#import <Foundation/Foundation.h>
#import "BigoInterstitialAd.h"
#import "BigoAdLoader.h"
#import "BigoInterstitialAdRequest.h"

NS_ASSUME_NONNULL_BEGIN

@protocol BigoInterstitialAdLoaderDelegate <NSObject>

- (void)onInterstitialAdLoaded:(BigoInterstitialAd *)ad;

@optional
- (void)onInterstitialAdLoadError:(BigoAdError *)error;

@end

@interface BigoInterstitialAdLoader : BigoAdLoader<BigoInterstitialAd *, BigoInterstitialAdRequest *>

- (instancetype)initWithInterstitialAdLoaderDelegate:(id <BigoInterstitialAdLoaderDelegate>)delegate;

@end

NS_ASSUME_NONNULL_END

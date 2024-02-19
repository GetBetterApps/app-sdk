//
//  BigoRewardVideoAdLoader.h
//  BigoADS
//
//  Created by lijianfeng on 2021/7/27.
//

#import <Foundation/Foundation.h>
#import "BigoAdLoader.h"
#import "BigoRewardVideoAd.h"
#import "BigoRewardVideoAdRequest.h"

NS_ASSUME_NONNULL_BEGIN

@protocol BigoRewardVideoAdLoaderDelegate <NSObject>

- (void)onRewardVideoAdLoaded:(BigoRewardVideoAd *)ad;

@optional
- (void)onRewardVideoAdLoadError:(BigoAdError *)error;

@end

@interface BigoRewardVideoAdLoader : BigoAdLoader<BigoRewardVideoAd *, BigoRewardVideoAdRequest *>

- (instancetype)initWithRewardVideoAdLoaderDelegate:(id <BigoRewardVideoAdLoaderDelegate>)delegate;

@end

NS_ASSUME_NONNULL_END

//
//  BigoRewardVideoAd.h
//  BigoADS
//
//  Created by lijianfeng on 2021/7/27.
//

#import "BigoInterstitialNativeAd.h"
#import "BigoRewardVideoAdInteractionDelegate.h"

NS_ASSUME_NONNULL_BEGIN

@interface BigoRewardVideoAd : BigoInterstitialNativeAd

- (void)setRewardVideoAdInteractionDelegate:(nullable id<BigoRewardVideoAdInteractionDelegate>)delegate;

- (void)setAdInteractionDelegate:(nullable id<BigoAdInteractionDelegate>)delegate NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END

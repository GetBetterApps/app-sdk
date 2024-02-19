//
//  BigoRewardVideoAdInteractionDelegate.h
//  BigoADS
//
//  Created by cai on 2023/12/15.
//

#import <Foundation/Foundation.h>
#import "BigoAdInteractionDelegate.h"

@class BigoRewardVideoAd;

NS_ASSUME_NONNULL_BEGIN

@protocol BigoRewardVideoAdInteractionDelegate <BigoAdInteractionDelegate>

@optional
//激励视频激励回调
- (void)onAdRewarded:(BigoRewardVideoAd *)ad;

@end

NS_ASSUME_NONNULL_END

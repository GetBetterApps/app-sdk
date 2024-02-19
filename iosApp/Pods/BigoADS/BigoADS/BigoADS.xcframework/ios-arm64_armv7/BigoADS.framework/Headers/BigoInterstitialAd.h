//
//  BigoInterstitialAd.h
//  BigoADS
//
//  Created by 李剑锋 on 2021/7/13.
//


#import "BigoAd.h"


NS_ASSUME_NONNULL_BEGIN

@interface BigoInterstitialAd : BigoAd


///> 广告全屏页面展示
- (void)show:(UIViewController *)vc NS_REQUIRES_SUPER;

@end

NS_ASSUME_NONNULL_END

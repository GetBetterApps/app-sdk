//
//  BigoAdInteractionListener.h
//  广告交互接口
//
//  Created by 蔡庆敏 on 2021/5/17.
//

#import <Foundation/Foundation.h>
#import "BigoAdError.h"


NS_ASSUME_NONNULL_BEGIN

@class BigoAd;

@protocol BigoAdInteractionDelegate <NSObject>

@optional
//广告异常
- (void)onAd:(BigoAd *)ad error:(BigoAdError *)error;

//广告展示
- (void)onAdImpression:(BigoAd *)ad;

//广告点击
- (void)onAdClicked:(BigoAd *)ad;

//广告打开
- (void)onAdOpened:(BigoAd *)ad;

//广告关闭
- (void)onAdClosed:(BigoAd *)ad;

//广告已被屏蔽（由负反馈触发）
- (void)onAdMuted:(BigoAd *)ad;

@end

NS_ASSUME_NONNULL_END

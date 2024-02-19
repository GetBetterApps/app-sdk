//
//  BigoSplashAd.h
//  BigoADS
//
//  Created by cai on 2022/5/10.
//

#import "BigoAd.h"

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(int8_t, BigoSplashAdStyle) {
    BigoSplashAdStyleVerticalFullscreen = 0, //竖屏 - 全屏
    BigoSplashAdStyleVerticalHalfcreen = 1, //竖屏 - 半屏
    BigoSplashAdStyleHorizontal = 2 //横屏
};

@protocol BigoSplashAdInteractionDelegate <BigoAdInteractionDelegate>

/**
 * 广告可跳过回调，通常是由用户点击了右上角 SKIP 按钮所触发
 */
- (void)onAdSkipped:(BigoAd *)ad;

/**
 * 广告倒计时结束回调
 */
- (void)onAdFinished:(BigoAd *)ad;

@end

@interface BigoSplashAd : BigoAd

/**
 * 开屏广告展示方法
 *
 * @param view 广告视图的父视图，请注意当前父视图勿包含其他需要展示子视图，否则这些子视图可能会被移除
 */
- (void)showInAdContainer:(UIView *)view;

/**
 * @return 当前广告是否为直接跳过
 */
- (BOOL)isSkippable;

/**
 * @return 每个开屏广告均有一个制定的样式，见 {@link BigoSplashAdStyle}
 */
- (BigoSplashAdStyle)getStyle;

- (void)setSplashAdInteractionDelegate:(nullable id

<BigoSplashAdInteractionDelegate>)
delegate;

- (void)setAdInteractionDelegate:(nullable id

<BigoAdInteractionDelegate>)
delegate NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END

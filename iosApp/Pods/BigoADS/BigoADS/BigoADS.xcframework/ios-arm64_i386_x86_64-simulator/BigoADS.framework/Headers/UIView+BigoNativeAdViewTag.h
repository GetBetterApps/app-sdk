//
//  UIView+BigoNativeAdViewTag.h
//  base
//
//  Created by lijianfeng on 2020/3/6.
//  Copyright © 2020 BIGO. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(int8_t, BigoNativeAdViewTag) {
    BigoNativeAdViewTagIcon                 = 1, ///广告图标
    BigoNativeAdViewTagTitle                = 2, ///广告标题
    BigoNativeAdViewTagSponsored            = 3, ///赞助内容标签
    BigoNativeAdViewTagOption               = 4, ///adchoice
    BigoNativeAdViewTagMedia                = 5, ///mediaview
    BigoNativeAdViewTagDescription          = 6, ///广告描述
    BigoNativeAdViewTagCallToAction         = 7, ///广告按钮
    BigoNativeAdViewTagWarnings             = 8, ///warnings
    BigoNativeAdViewTagMediaBlankArea       = 9, ///mediaview空白处
    BigoNativeAdViewTagOther                = 10, ///其他元素
    BigoNativeAdViewTagNativeAdView         = 11, ///原生视图
    BigoNativeAdViewTagClickGuide           = 12,
    BigoNativeAdViewTagPlayable             = 13,
    BigoNativeAdViewTagSlide                = 14,
    BigoNativeAdViewTagAdCompanion          = 15, 
    BigoNativeAdViewTagSlideGesture         = 16, ///视频播放过程中滑动跳转落地页
    BigoNativeAdViewTagLayerCtaIcon         = 17, ///挽留页点击（icon和广告CTA点击）
    BigoNativeAdViewTagComponentBlankArea   = 18, /// 广告组件空白区域
    BigoNativeAdViewTagPlayableBlankArea    = 19, /// 试玩素材页面空白区域
    BigoNativeAdViewTagCompanionBlankArea   = 20, /// 普通companion空白区域
    BigoNativeAdViewTagSKOverlay            = 21,
    BigoNativeAdViewTagAutoClick            = 22, ///自动点击
    BigoNativeAdViewTagUpArea               = 24, /// 距离顶部区域
    BigoNativeAdViewTagBelowArea            = 25 /// 距离底部区域
};


@interface UIView (BigoNativeAdViewTag)

@property (nonatomic, assign) BigoNativeAdViewTag bigoNativeAdViewTag;

@end

NS_ASSUME_NONNULL_END

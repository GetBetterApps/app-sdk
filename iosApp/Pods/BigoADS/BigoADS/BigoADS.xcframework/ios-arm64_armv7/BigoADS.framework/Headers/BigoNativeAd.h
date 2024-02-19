//
//  BigoNativeAd.h
//  BigoADS
//
//  Created by 蔡庆敏 on 2021/7/8.
//

#import <Foundation/Foundation.h>
#import "BigoAd.h"
#import "BigoAdMediaView.h"
#import "BigoAdOptionsView.h"
#import "BigoVideoController.h"
#import "UIView+BigoNativeAdViewTag.h"

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(int32_t, BGCreativeType) {
    BGCreativeTypeImage,
            BGCreativeTypeVideo
};

@interface BigoNativeAd : BigoAd
/**
 * Native 广告视图注册，视图内容填充核心实现
 *
 * @param adView         广告主视图
 * @param mediaView      富媒体视图
 * @param adIconView     Icon 视图
 * @param adOptionsView  AdOptions 视图
 * @param clickableViews 其他可响应广告点击的 View
 */
- (void)registerViewForInteraction:(UIView *)adView
                         mediaView:(BigoAdMediaView *)mediaView
                        adIconView:(UIImageView *)adIconView
                     adOptionsView:(BigoAdOptionsView *)adOptionsView
                    clickableViews:(NSArray

<UIView *>*)
clickableViews;


/**
 * @return 获取广告标题
 */
- (NSString *)title;

/**
 * @return 获取广告描述信息
 */
- (NSString *)adDescription;

/**
 * @return 获取 CTA 按钮文案
 */
- (NSString *)callToAction;

/**
 * @return 获取警告信息
 */
- (NSString *)adWarning;

/**
 * @return 获取广告主domain信息
 */
- (NSString *)advertiser;

/**
 * @return 是否包含 Icon 图标
 */
- (BOOL)hasIcon;

/**
 * @return 获取素材类型
 */
- (BGCreativeType)getCreativeType;

/**
 * 获取视频播放控制器
 *
 * @return 若此广告不包含视频内容，返回为 nil
 */
- (BigoVideoController *)getVideoController;

@end

NS_ASSUME_NONNULL_END

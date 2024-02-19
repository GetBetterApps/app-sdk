//
//  BigoAdErrorCode.h
//  Pods
//
//  Created by 蔡庆敏 on 2021/5/17.
//

#ifndef BigoAdErrorCode_h
#define BigoAdErrorCode_h


//region 广告请求链路错误码
static NSInteger const BIGO_AD_ERROR_CODE_UNINITIALIZED = 1000; // 未初始化
static NSInteger const BIGO_AD_ERROR_CODE_INVALID_REQUEST = 1001; // 无效请求，如请求参数未正确设置等
static NSInteger const BIGO_AD_ERROR_CODE_AD_DISABLE = 1002; // 当前广告不可用，如下发的配置为该 slot 已关闭
static NSInteger const BIGO_AD_ERROR_CODE_NETWORK_ERROR = 1003; // 网络请求异常，如网络环境不可用
static NSInteger const BIGO_AD_ERROR_CODE_NO_FILL = 1004; // 广告未填充
static NSInteger const BIGO_AD_ERROR_CODE_INTERNAL_ERROR = 1005; // 广告请求内部异常，如返回的广告数据异常等
static NSInteger const BIGO_AD_ERROR_CODE_ASSETS_ERROR = 1006; // 广告素材相关异常，比如 vast 解析异常、html 素材加载失败等
static NSInteger const BIGO_AD_ERROR_CODE_APP_ID_UNMATCHED = 1007; // 初始化APP ID不匹配
//endregion

//region 广告交互过程（展示、点击等）错误码
static NSInteger const BIGO_AD_ERROR_CODE_AD_EXPIRED = 2000; // 广告已过期
static NSInteger const BIGO_AD_ERROR_CODE_NATIVE_VIEW_MISSING = 2001; // Native 广告主视图 NativeAdView 缺失
static NSInteger const BIGO_AD_ERROR_CODE_VIDEO_ERROR = 2002; // 视频播放过程相关异常
static NSInteger const BIGO_AD_ERROR_CODE_FULLSCREEN_AD_FAILED_TO_SHOW = 2003; // 全屏广告（插屏 & 激励视频）展示异常
//endregion

#endif /* BigoAdErrorCode_h */

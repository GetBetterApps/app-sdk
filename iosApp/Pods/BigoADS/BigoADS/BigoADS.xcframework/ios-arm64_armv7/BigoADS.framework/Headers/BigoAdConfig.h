//
//  BigoAdConfig.h
//  AFNetworking
//
//  Created by 蔡庆敏 on 2021/5/19.
//

#import <Foundation/Foundation.h>
#import "BigoAdGender.h"

NS_ASSUME_NONNULL_BEGIN

static NSString * const BGAD_EXTRA_KEY_HOST_RULES = @"host_rules";


@interface BigoAdConfig : NSObject

@property (nonatomic) BOOL testMode; // default is NO;
@property (nonatomic, readonly) NSString *appKey;

/**
 * age 年龄
 */
@property (nonatomic, assign) int32_t age;

/**
 * gender 性别
 */
@property (nonatomic, assign) BigoAdGender gender;

/**
 * activatedTime app激活时间
 */
@property (nonatomic, assign) int64_t activatedTime;

/**
 Initializes the @c BigoAdConfig object with the required fields.
 @param appKey Any valid ad appkey used within the app used for app initialization.
 @return A BigoAdConfig instance.
 */
- (instancetype)initWithAppId:(NSString *)appId;

- (void)addExtraWithKey:(NSString *)key extra:(NSString *)extra;

- (instancetype)initWithAppKey:(NSString *)appKey DEPRECATED_MSG_ATTRIBUTE("Please use [BigoAdConfig initWithAppId:]");
/**
 Usage of default initializer is disallowed. Use @c initWithAppKey: instead.
 */
- (instancetype)init NS_UNAVAILABLE;

/**
 Usage of @c new is disallowed. Use @c initWithAppKey: instead.
 */
+ (instancetype)new NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END

//
//  BigoRequest.h
//  Pods
//
//  Created by 蔡庆敏 on 2021/6/2.
//

#import <Foundation/Foundation.h>
#import "BigoUrl.h"

NS_ASSUME_NONNULL_BEGIN

@class BGAdNetResponse;

typedef void (^BGAdRedirectBlock)(NSInteger statusCode);

typedef BGAdNetResponse *_Nonnull(^ResponseCreator)
(void);

static NSString *const KEY_REQUEST_ID = @"BIGO-Ad-Request-Id";
static NSString *const KEY_USER_AGENT = @"User-Agent";
static NSString *const KEY_SDK_VC = @"SDK-Version-Code";

@interface BigoRequest : NSObject

@property(nonatomic) int32_t timeout;
@property(nonatomic, readonly) int32_t seqId;
@property(nonatomic) BOOL ignoringLocalCache; //default NO
///> 不要重写get方法
@property(nonatomic, strong, readonly) BGAdNetResponse *response;

- (instancetype)initWithSeqId:(int32_t)seqId
                          url:(id <BigoUrl>)url
              responseCreator:(ResponseCreator)responseCreator;

- (void)appendHeaderWithKey:(NSString *)key value:(NSString *)value;

- (void)clearHeaderKey:(NSString *)key;

- (NSDictionary *)getHeaders;

- (NSString *)getUrl;

- (id <BigoUrl>)getBigoUrl;

//可重写 后续应优化为枚举 参考AFN
- (NSString *)acceptType;

- (int64_t)contentLength;

- (void)onOpenURL;

@end

NS_ASSUME_NONNULL_END

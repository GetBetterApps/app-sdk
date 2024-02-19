//
//  BigoUrl.h
//  BigoADS
//
//  Created by cai on 2023/7/24.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol BigoUrl <NSObject>

- (NSString *)getUrl;

- (void)markReqFailure;

- (void)markReqSuccess;

- (NSString *)getCurrentHost;

- (NSString *)getPreHost;

- (BOOL)ignoreCache;

- (void)startOptTimeoutTask:(int64_t)optTimeoutMillis;

@end

NS_ASSUME_NONNULL_END

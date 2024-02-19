//
//  BigoAd.h
//
//  Created by 蔡庆敏 on 2021/5/17.
//

#import <Foundation/Foundation.h>
#import "BigoAdInteractionDelegate.h"
#import "BGAdBid.h"

NS_ASSUME_NONNULL_BEGIN

@protocol BigoAd <NSObject>
/*
 * 设置广告交互监听接口
 */
- (void)setAdInteractionDelegate:(nullable id<BigoAdInteractionDelegate>)delegate;
/*
 * 返回bidding信息
 */
- (id<BGAdBid>)getBid;
/*
 * 返回创意id
 */
- (NSString *)getCreativeId;
/*
 * 此广告是否已过期
 */
- (BOOL)isExpired;

/*
 * 广告销毁处理
 */
- (void)destroy;

@end

@interface BigoAd : NSObject <BigoAd>

@end

NS_ASSUME_NONNULL_END

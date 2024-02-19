//
//  BigoRewardVideoAdRequest.h
//  BigoADS
//
//  Created by lijianfeng on 2021/7/27.
//

#import <Foundation/Foundation.h>
#import "BigoAdRequest.h"

NS_ASSUME_NONNULL_BEGIN

@interface BigoRewardVideoAdRequest : BigoAdRequest

- (void)setServerBidPayload:(NSString *)serverBidPayload;

@end

NS_ASSUME_NONNULL_END

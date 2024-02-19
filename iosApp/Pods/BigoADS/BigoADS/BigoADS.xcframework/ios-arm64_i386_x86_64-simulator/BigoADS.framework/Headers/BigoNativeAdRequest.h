//
//  BigoNativeAdRequest.h
//  BigoADS
//
//  Created by 蔡庆敏 on 2021/7/8.
//

#import "BigoAdRequest.h"

NS_ASSUME_NONNULL_BEGIN

@interface BigoNativeAdRequest : BigoAdRequest

- (instancetype)initWithSlotId:(NSString *)slotId;

- (void)setServerBidPayload:(NSString *)serverBidPayload;

@end

NS_ASSUME_NONNULL_END

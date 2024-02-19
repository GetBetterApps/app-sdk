//
//  BigoBannerAdRequest.h
//  BigoADS
//
//  Created by 蔡庆敏 on 2021/5/18.
//

#import "BigoAdRequest.h"
#import "BigoAdSize.h"

NS_ASSUME_NONNULL_BEGIN

@interface BigoBannerAdRequest : BigoAdRequest

@property(nonatomic) NSArray<BigoAdSize *> *adSizes;

- (instancetype)initWithSlotId:(NSString *)slotId adSizes:(NSArray

<BigoAdSize *> *)
adSizes;

- (void)setServerBidPayload:(NSString *)serverBidPayload;

@end

NS_ASSUME_NONNULL_END

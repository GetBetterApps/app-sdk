//
//  BigoInterstitialAdRequest.h
//  BigoADS
//
//  Created by 李剑锋 on 2021/7/13.
//

#import <Foundation/Foundation.h>
#import "BigoAdRequest.h"

NS_ASSUME_NONNULL_BEGIN

@interface BigoInterstitialAdRequest : BigoAdRequest

- (void)setServerBidPayload:(NSString *)serverBidPayload;

@end

NS_ASSUME_NONNULL_END

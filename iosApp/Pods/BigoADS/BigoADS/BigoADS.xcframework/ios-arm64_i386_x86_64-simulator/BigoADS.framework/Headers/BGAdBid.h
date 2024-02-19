//
//  BGAdBid.h
//  BigoADS
//
//  Created by cai on 2023/3/28.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

#pragma mark - 沉浸式播放
typedef NS_ENUM(int8_t, BGAdLossReasonType) {
    BGAdLossReasonTypeInternalError = 1,
    BGAdLossReasonTypeTimeout = 2,
    BGAdLossReasonTypeLowerThanFloorPrice = 100,
    BGAdLossReasonTypeLowerThanHighestPrice = 101
};

@protocol BGAdBid <NSObject>

- (CGFloat)getPrice;

- (void)notifyWinWithSecPrice:(CGFloat)secPrice secBidder:(NSString *)secBidder;

- (void)notifyLossWithFirstPrice:(CGFloat)firstPrice firstBidder:(NSString *)firstBidder lossReason:(BGAdLossReasonType)lossReason;

@end

NS_ASSUME_NONNULL_END

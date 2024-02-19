//
//  BigoAdRequest.h
//  BigoADS
//
//  Created by 蔡庆敏 on 2021/5/18.
//

#import <Foundation/Foundation.h>
#import "BigoAdError.h"
#import "BigoAdGender.h"

NS_ASSUME_NONNULL_BEGIN

static int32_t
const REQUEST_SOURCE_NORMAL = 0;

@interface BigoAdRequest : NSObject

@property(nonatomic, readonly) NSString *slotId;
@property(nonatomic) int32_t adType;
@property(nonatomic, copy) NSString *serverBidPayload;
@property(nonatomic, copy) NSString *loadExt;
@property(nonatomic) int32_t age;
@property(nonatomic) BigoAdGender gender;
@property(nonatomic) int64_t activatedTime;

- (instancetype)initWithSlotId:(NSString *)slotId;


@end

NS_ASSUME_NONNULL_END

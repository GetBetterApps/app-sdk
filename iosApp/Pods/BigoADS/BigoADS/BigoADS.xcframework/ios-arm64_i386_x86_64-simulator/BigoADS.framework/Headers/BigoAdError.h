//
//  BigoAdError.h
//  BigoADS
//
//  Created by 蔡庆敏 on 2021/5/17.
//

#import <Foundation/Foundation.h>
#import "BigoAdErrorCode.h"
#import "BigoAdInnerErrorCode.h"

NS_ASSUME_NONNULL_BEGIN

@interface BigoAdError : NSObject

@property(nonatomic, readonly) int32_t errorCode;
@property(nonatomic, readonly) int32_t subCode;
@property(nonatomic, readonly) NSString *errorMsg;

- (instancetype)initWithErrorCode:(int32_t)errorCode subErrorCode:(int32_t)subCode errorMsg:(NSString *)errorMsg;

@end

NS_ASSUME_NONNULL_END

//
//  BGAdLogDelegate.h
//  base
//
//  Created by lijianfeng on 2020/4/27.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol BGAdLogDelegate <NSObject>

- (void)bigoAdLogD:(NSString *)tag message:(NSString *)message;

- (void)bigoAdLogI:(NSString *)tag message:(NSString *)message;

- (void)bigoAdLogW:(NSString *)tag message:(NSString *)message;

- (void)bigoAdLogE:(NSString *)tag message:(NSString *)message;

@end

NS_ASSUME_NONNULL_END

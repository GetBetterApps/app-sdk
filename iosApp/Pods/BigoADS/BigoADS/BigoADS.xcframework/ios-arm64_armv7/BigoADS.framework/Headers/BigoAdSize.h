//
//  BigoAdSize.h
//  BigoADS
//
//  Created by 蔡庆敏 on 2021/5/17.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface BigoAdSize : NSObject

@property(nonatomic, readonly) CGFloat width;
@property(nonatomic, readonly) CGFloat height;
@property(nonatomic, readonly) NSString *desc;

+ (BigoAdSize *)BANNER; //320*50
+ (BigoAdSize *)LARGE_BANNER; //320*100
+ (BigoAdSize *)MEDIUM_RECTANGLE; //300*250
+ (BigoAdSize *)LARGE_RECTANGLE; //336*280

@end

NS_ASSUME_NONNULL_END

//
//  BigoAdLoader.h
//  BigoADS
//
//  Created by 蔡庆敏 on 2021/5/18.
//

#import <Foundation/Foundation.h>
#import "BigoAdRequest.h"
#import "BigoAd.h"

NS_ASSUME_NONNULL_BEGIN

@interface BigoAdLoader<__covariant BigoAdType : BigoAd *, __covariant BigoAdRequestType : BigoAdRequest *> : NSObject

@property(nonatomic, copy) NSString *ext;

- (void)loadAd:(BigoAdRequestType)request;

@end


NS_ASSUME_NONNULL_END

//
//  MTRGAdNetworkConfigProtocol.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 07.04.2022.
//  Copyright © 2022 VK. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@protocol MTRGAdNetworkDataProtocol <NSObject>

@end

@protocol MTRGAdNetworkLoaderProtocol;

@protocol MTRGAdNetworkLoaderDelegate <NSObject>

- (void)onLoad:(id <MTRGAdNetworkLoaderProtocol>)loader
        params:(NSDictionary

<NSString *, NSString *> *)
params
        error
:(
nullable NSString
*)
error;

@end

@protocol MTRGAdNetworkLoaderProtocol <NSObject>

@property(atomic, weak, nullable) id <MTRGAdNetworkLoaderDelegate> delegate;

- (void)loadParamsWithAdFormat:(NSString *)adFormat;

@end

@protocol MTRGAdNetworkConfigProtocol <NSObject>

@property(nonatomic, readonly) NSString *adNetwork;
@property(nonatomic, readonly, nullable) id <MTRGAdNetworkDataProtocol> data;
@property(nonatomic, readonly, nullable) id <MTRGAdNetworkLoaderProtocol> loader;

@end

NS_ASSUME_NONNULL_END

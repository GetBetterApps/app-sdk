//
//  MTRGMediationAdapter.h
//  myTargetSDK 5.20.2
//
// Copyright (c) 2019 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Base class for mediation adapters
 */
@protocol MTRGMediationAdapter <NSObject>

/**
 @discussion Method for destroying adapter
 */
- (void)destroy;

@end

NS_ASSUME_NONNULL_END

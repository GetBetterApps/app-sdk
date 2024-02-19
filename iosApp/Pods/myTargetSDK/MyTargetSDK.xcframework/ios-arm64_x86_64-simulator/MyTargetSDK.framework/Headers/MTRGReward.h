//
//  MTRGReward.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 31.07.2020.
//  Copyright © 2020 Mail.ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Wrapper for reward.
 */
@interface MTRGReward : NSObject

/**
 @discussion Type of the reward.
 */
@property(nonatomic, readonly) NSString *type;

/**
 @discussion Static constructor. Creates instance of the class.
 
 @return Instance of the class.
 */
+ (instancetype)create;

@end

NS_ASSUME_NONNULL_END

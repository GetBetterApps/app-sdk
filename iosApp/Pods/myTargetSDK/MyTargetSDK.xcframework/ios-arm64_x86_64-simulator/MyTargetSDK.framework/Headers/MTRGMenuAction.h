//
//  MTRGMenuAction.h
//  myTargetSDK 5.20.2
//
//  Created by igor.sorokin on 31.08.2022.
//  Copyright © 2022 VK. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef NS_ENUM(NSUInteger, MTRGMenuActionStyle
)
{
MTRGMenuActionStyleDefault,
MTRGMenuActionStyleCancel
};

@interface MTRGMenuAction : NSObject

- (nonnull instancetype)initWithTitle:(nonnull NSString

*)
title style
:(MTRGMenuActionStyle)
style handler
:(

void (^ _Nullable)(void)

)
clickHandler;

- (nonnull instancetype)init

NS_UNAVAILABLE;

+ (nonnull instancetype)new

NS_UNAVAILABLE;

@property(nonatomic, readonly, nonnull) NSString *title;
@property(nonatomic, readonly, assign) MTRGMenuActionStyle style;

- (void)handleClick;

@end

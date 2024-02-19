//
//  MTRGMenuFactory.h
//  myTargetSDK 5.20.2
//
//  Created by igor.sorokin on 31.08.2022.
//  Copyright © 2022 VK. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol MTRGMenu;

/**
 @discussion Factory for MTRGMenu.
 */
@protocol MTRGMenuFactory <NSObject>

- (nonnull id

<MTRGMenu>)
menu;

@end

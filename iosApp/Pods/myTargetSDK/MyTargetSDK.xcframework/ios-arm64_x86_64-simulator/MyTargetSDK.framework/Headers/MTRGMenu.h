//
//  MTRGMenu.h
//  myTargetSDK 5.20.2
//
//  Created by igor.sorokin on 31.08.2022.
//  Copyright © 2022 VK. All rights reserved.
//

#import <UIKit/UIKit.h>

@class MTRGMenuAction;

/**
 @discussion Protocol for presenting adChoices menu.
 */
@protocol MTRGMenu <NSObject>

/**
 @discussion Add menu item model to your storage.

 @param action MTRGMenuAction object
 */
- (void)addMenuAction:(nonnull MTRGMenuAction

*)
action NS_SWIFT_NAME(add(menuAction
:));

/**
 @discussion Present menu using passed items.

 @param sourceView UIView for iPad popover.
 @param viewController UIViewController that should show the menu.
 */
- (void)presentInViewController:(nonnull UIViewController

*)
viewController sourceView
:(
nullable UIView
*)
sourceView;

@end

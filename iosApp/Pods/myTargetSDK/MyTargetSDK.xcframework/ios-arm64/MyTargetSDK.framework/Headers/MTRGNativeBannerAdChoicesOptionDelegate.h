//
//  MTRGNativeBannerAdChoicesOptionDelegate.h
//  MyTargetSDK
//
//  Created by igor.sorokin on 07.10.2022.
//  Copyright © 2022 VK. All rights reserved.
//

@class MTRGNativeBannerAd;

/**
 @discussion Used when the user clicks on hide ad option in the AdChoices menu.
 In delegate you can recieve notification when SDK hide ad or you can disable it and hide manually.
 */
@protocol MTRGNativeBannerAdChoicesOptionDelegate <NSObject>

/**
 @discussion Called when SDK hides ad. Only when `shouldCloseAutomatically` returns YES.
 */
- (void)onCloseAutomatically:(MTRGNativeBannerAd *)nativeBannerAd;

/**
 @discussion Called only when `shouldCloseAutomatically` returns NO. Developers should close ad manually in this method.
 */
- (void)closeIfAutomaticallyDisabled:(MTRGNativeBannerAd *)nativeBannerAd;

@optional

/**
 @discussion Return YES if SDK should automatically close ad or NO for manual.
 Default: YES
 */
- (BOOL)shouldCloseAutomatically;

@end

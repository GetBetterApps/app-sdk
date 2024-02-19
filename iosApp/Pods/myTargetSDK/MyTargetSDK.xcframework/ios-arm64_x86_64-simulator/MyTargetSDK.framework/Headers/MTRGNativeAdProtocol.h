//
//  MTRGNativeAdProtocol.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 10/02/2020.
//  Copyright © 2020 Mail.Ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MyTargetSDK/MTRGCachePolicy.h>
#import <MyTargetSDK/MTRGAdChoicesPlacement.h>

@protocol MTRGMenuFactory;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Protocol for native ad.
 */
@protocol MTRGNativeAdProtocol <NSObject>

/**
 @discussion Cache policy for the native ad.
 */
@property(nonatomic) MTRGCachePolicy cachePolicy;

/**
 @discussion Placement for ad choices.
 */
@property(nonatomic) MTRGAdChoicesPlacement adChoicesPlacement;

/**
 @discussion Flag determines mediation enabling status.
 */
@property(nonatomic) BOOL mediationEnabled;

/**
 @discussion Ad source for the ad.
 */
@property(nonatomic, readonly, nullable) NSString *adSource;

/**
 @discussion Priority for the ad source.
 */
@property(nonatomic, readonly) float adSourcePriority;

/**
 @discussion Creates instance of the class with slot identifier.
 
 @param slotId Slot identifier.
 
 @return Instance of the class.
 */
- (instancetype)initWithSlotId:(NSUInteger)slotId;

/**
 @discussion Create instance of the class with slot identifier.

 @param slotId Slot identifier.
 @param adChoicesMenuFactory AdChoices menu factory

 @return Instance of the class.
 */
- (instancetype)initWithSlotId:(NSUInteger)slotId adChoicesMenuFactory:(id <MTRGMenuFactory>)adChoicesMenuFactory;

/**
 @discussion Handles data.
 
 @param data Data as a string.
 */
- (void)handleData:(NSString *)data;

/**
 @discussion Loads the ad.
 */
- (void)load;

/**
 @discussion Loads the ad for the bid identifier.
 
 @param bidId Bid identifier.
 */
- (void)loadFromBid:(NSString *)bidId;

/**
 @discussion Registers view for the ad.
 
 @param containerView View for the ad.
 @param controller Controller for the ad.
 */
- (void)registerView:(UIView *)containerView withController:(UIViewController *)controller;

/**
 @discussion Registers view for the ad.
 
 @param containerView View for the ad.
 @param controller Controller for the ad.
 @param clickableViews Array of clickable views.
 */
- (void)registerView:(UIView *)containerView
      withController:(UIViewController *)controller
  withClickableViews:(nullable NSArray

<UIView *> *)
clickableViews;

/**
 @discussion Unregister view for the ad.
 */
- (void)unregisterView;

/**
 @discussion Method to handle adChoices click.

 @param viewController Used UIViewController.
 @param sourceView UIView for iPad popover.
 */
- (void)handleAdChoicesClickWithController:(UIViewController *)viewController sourceView:(nullable UIView

*)
sourceView NS_SWIFT_NAME(handleAdChoicesClick(controller
:sourceView:));

@end

NS_ASSUME_NONNULL_END

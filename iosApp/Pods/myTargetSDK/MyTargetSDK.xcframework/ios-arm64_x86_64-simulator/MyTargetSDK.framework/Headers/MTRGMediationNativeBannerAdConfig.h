//
//  MTRGMediationNativeBannerAdConfig.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 11/06/2020.
//  Copyright © 2020 Mail.ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTargetSDK/MTRGMediationAdConfig.h>
#import <MyTargetSDK/MTRGCachePolicy.h>
#import <MyTargetSDK/MTRGAdChoicesPlacement.h>

@class MTRGPrivacy;
@protocol MTRGMenuFactory;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class describes configuration for native banner ad.
 */
@interface MTRGMediationNativeBannerAdConfig : MTRGMediationAdConfig

/**
 @discussion Current cache policy for the banner ad.
 */
@property(nonatomic, readonly) MTRGCachePolicy cachePolicy;

/**
 @discussion Placement for ad choices.
 */
@property(nonatomic, readonly) MTRGAdChoicesPlacement adChoicesPlacement;

/**
 @discussion Menu factory for drawing adChoices menu manually. See MTRGMenuFactory protocol.
 */
@property(nonatomic, readonly) id <MTRGMenuFactory> adChoicesMenuFactory;

/**
 @discussion Constructor
 
 @param placementId Placement ID
 @param payload Payload
 @param serverParams Server parameters
 @param age User's age
 @param gender User's gender
 @param privacy Privacy object
 @param cachePolicy Current cache policy for the banner ad.
 @param adChoicesPlacement Placement for ad choices.
 @param adChoicesMenuFactory Menu factory.
 
 @return Instance of MTRGMediationNativeBannerAdConfig
 */
+ (instancetype)configWithPlacementId:(NSString *)placementId
                              payload:(nullable NSString

*)
payload
        serverParams
:(NSDictionary<NSString *, NSString *> *)
serverParams
        age
:(
nullable NSNumber
*)
age
        gender
:(MTRGGender)
gender
        privacy
:(MTRGPrivacy *)
privacy
        cachePolicy
:(MTRGCachePolicy)
cachePolicy
        adChoicesPlacement
:(MTRGAdChoicesPlacement)
adChoicesPlacement
        adNetworkConfig
:(
nullable id
<MTRGAdNetworkConfigProtocol>)
adNetworkConfig
        adChoicesMenuFactory
:(id <MTRGMenuFactory>)
adChoicesMenuFactory;

@end

NS_ASSUME_NONNULL_END

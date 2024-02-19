//
//  MTRGMediationNativeAdConfig.h
//  myTargetSDK 5.20.2
//
// Copyright (c) 2019 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTargetSDK/MTRGMediationAdConfig.h>
#import <MyTargetSDK/MTRGNativeAd.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Configuration of native ad.
 */
@interface MTRGMediationNativeAdConfig : MTRGMediationAdConfig

/**
 @discussion Cache policy for the native ad.
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
 @discussion Static constructor. Creates instance of the class.
 
 @param placementId Placement identifier.
 @param payload String with payload.
 @param serverParams Dictionary with parameters from the server.
 @param age User's age.
 @param gender User's gender.
 @param privacy Configuration of privacy for the user.
 @param cachePolicy Cache policy for the native ad.
 @param adChoicesPlacement Placement for ad choices.
 @param adChoicesMenuFactory Menu factory.
 
 @return Instance of the class.
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

@interface MTRGMediationNativeAdConfig (MTRGDeprecated)

@property(nonatomic, readonly) BOOL autoLoadImages;
@property(nonatomic, readonly) BOOL autoLoadVideo;

@end

NS_ASSUME_NONNULL_END

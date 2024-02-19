//
//  MTRGMediationAdConfig.h
//  myTargetSDK 5.20.2
//
// Copyright (c) 2019 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTargetSDK/MTRGCustomParams.h>

@class MTRGPrivacy;
@protocol MTRGAdNetworkConfigProtocol;

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class describes configuration of mediation
 */
@interface MTRGMediationAdConfig : NSObject

/**
 @discussion Placement ID
 */
@property(nonatomic, readonly, copy) NSString *placementId;

/**
 @discussion Payload
 */
@property(nonatomic, readonly, copy, nullable) NSString *payload;

/**
 @discussion Server parameters
 */
@property(nonatomic, readonly) NSDictionary<NSString *, NSString *> *serverParams;

/**
 @discussion User's age
 */
@property(nonatomic, readonly, nullable) NSNumber *age;

/**
 @discussion User's gender
 Available values:
 MTRGGenderUnspecified
 MTRGGenderUnknown
 MTRGGenderMale
 MTRGGenderFemale
 */
@property(nonatomic, readonly) MTRGGender gender;

/**
 @discussion Instance of object MTRGPrivacy. Describes privacy settings
 */
@property(nonatomic, readonly) MTRGPrivacy *privacy;

/**
 @discussion Additional data for mediation
 */
@property(nonatomic, readonly, nullable) id <MTRGAdNetworkConfigProtocol> adNetworkConfig;

/**
 @discussion Constructor
 
 @param placementId Placement ID
 @param payload Payload
 @param serverParams Server parameters
 @param age User's age
 @param gender User's gender
 @param privacy Privacy object
 
 @return Instance of MTRGMediationAdConfig
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
        adNetworkConfig
:(
nullable id
<MTRGAdNetworkConfigProtocol>)
adNetworkConfig;

- (instancetype)init

NS_UNAVAILABLE;

@end

@interface MTRGMediationAdConfig (MTRGDeprecated)

/**
 @discussion Defines that consent already specified
 */
@property(nonatomic, readonly) BOOL userConsentSpecified;

/**
 @discussion User consent
 */
@property(nonatomic, readonly) BOOL userConsent;

/**
 @discussion Defines user's age is restricted or not
 */
@property(nonatomic, readonly) BOOL userAgeRestricted;

@end

NS_ASSUME_NONNULL_END

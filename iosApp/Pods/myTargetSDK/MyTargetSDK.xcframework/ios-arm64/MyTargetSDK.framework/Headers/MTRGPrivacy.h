//
//  MTRGPrivacy.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 28.05.2018.
//  Copyright © 2018 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class manages all privacy options
 */
@interface MTRGPrivacy : NSObject

/**
 @discussion Flag of user's consent
 */
@property(nonatomic, readonly) BOOL isConsent;

/**
 @discussion Age of restriction
 */
@property(nonatomic, readonly) BOOL userAgeRestricted;

/**
 @discussion User's consent
 */
@property(nonatomic, readonly, nullable) NSNumber *userConsent;

/**
 @discussion User's ccpa consent
 */
@property(nonatomic, readonly, nullable) NSNumber *ccpaUserConsent;

/**
 @discussion User's AB consent
 */
@property(nonatomic, readonly, nullable) NSNumber *iABUserConsent;

/**
 @discussion Method to get instance of MTRGPrivacy
 */
+ (instancetype)currentPrivacy;

/**
 @discussion Method to set user's consent
 
 @param isConsent Flag of consent
 */
+ (void)setUserConsent:(BOOL)isConsent;

/**
 @discussion Method to set user's CCPA consent
 
 @param isConsent Flag of CCPA consent
 */
+ (void)setCcpaUserConsent:(BOOL)isConsent;

/**
 @discussion Method to set user's AB consent
 
 @param isConsent Flag of AB consent
 */
+ (void)setIABUserConsent:(BOOL)isConsent;

/**
 @discussion Method to set restriction by age
 
 @param isAgeRestricted Age of restriction
 */
+ (void)setUserAgeRestricted:(BOOL)isAgeRestricted;

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END

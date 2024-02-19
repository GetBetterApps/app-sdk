//
//  MTRGCustomParams.h
//  myTargetSDK 5.20.2
//
//  Created by Anton Bulankin on 22.12.14.
//  Copyright (c) 2014 Mail.ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

extern NSString
*
const kMTRGCustomParamsMediationKey;
extern NSString *const kMTRGCustomParamsMediationAdmob;
extern NSString *const kMTRGCustomParamsMediationMopub;

typedef enum {
    MTRGGenderUnspecified = -1,
    MTRGGenderUnknown,
    MTRGGenderMale,
    MTRGGenderFemale
} MTRGGender;

/**
 @discussion Class of custom parameters
 */
@interface MTRGCustomParams : NSObject

/**
 @discussion User's age
 */
@property(nullable) NSNumber *age;

/**
 @discussion User's gender
 */
@property(nonatomic) MTRGGender gender;

/**
 @discussion The flag defines option for CarPlay
 */
@property(nonatomic) BOOL isCarPlay;

/**
 @discussion Current language
 */
@property(copy, nullable) NSString *language;

/**
 @discussion User's email
 */
@property(copy, nullable) NSString *email;

/**
 @discussion User's phone number
 */
@property(copy, nullable) NSString *phone;

/**
 @discussion User's ICQ id
 */
@property(copy, nullable) NSString *icqId;

/**
 @discussion User's OK id
 */
@property(copy, nullable) NSString *okId;

/**
 @discussion User's VK id
 */
@property(copy, nullable) NSString *vkId;

/**
 @discussion Custom identifier of user
 */
@property(copy, nullable) NSString *customUserId;

/**
 @discussion MRGS app's identifier
 */

@property(copy, nullable) NSString *mrgsAppId;

/**
 @discussion MRGS user's identifier
 */
@property(copy, nullable) NSString *mrgsUserId;

/**
 @discussion MRGS device's identifier
 */
@property(copy, nullable) NSString *mrgsDeviceId;

/**
 @discussion Static constructor of MTRGCustomParams
 
 @return Instance of MTRGCustomParams
 */
+ (instancetype)create;

/**
 @discussion Method to convert custom parameters to NSDictionary
 
 @return Parameters as NSDictionary
 */
- (NSDictionary

<NSString *, NSString *> *)
asDictionary;

/**
 @discussion Method to get custom parameters as NSDictionary
 
 @return Custom parameters as NSDictionary
 */
- (NSDictionary

<NSString *, NSString *> *)
asCustomDataDictionary;

/**
 @discussion Method ot set custom parameters as key-value
 
 @param param Parameter value
 @param key Key for parameter value
 */
- (void)setCustomParam:(nullable NSString

*)
param forKey
:(NSString *)
key;

/**
 @discussion Method to get custom parameter by a key
 
 @param key Key to get parameter value
 
 @return Parameter value as a NSString
 */
- (nullable NSString

*)customParamForKey:(NSString *)
key;

@end

NS_ASSUME_NONNULL_END

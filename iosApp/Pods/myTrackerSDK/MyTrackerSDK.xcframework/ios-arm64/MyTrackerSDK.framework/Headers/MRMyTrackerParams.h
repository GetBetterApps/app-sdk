//
//  MRMyTrackerParams.h
//  myTrackerSDK
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

typedef NS_ENUM(NSInteger, MRGender) {
    MRGenderUnspecified = -1,
            MRGenderUnknown,
            MRGenderMale,
            MRGenderFemale
};

@interface MRMyTrackerParams : NSObject

/**
 @discussion Current user's gender.
 Available values:
 MRGenderUnknown
 MRGenderMake
 MRGenderFemale
 */
@property(nonatomic) MRGender gender
NS_SWIFT_NAME(gender);

/**
 @discussion Current user's age. Optional.
 */
@property(nullable) NSNumber *age
NS_SWIFT_NAME(age);

/**
 @discussion Current user's language. Optional.
 */
@property(copy, nullable) NSString *language
NS_SWIFT_NAME(language);

/**
 @discussion MRGS application identifier. Optional.
 */
@property(copy, nullable) NSString *mrgsAppId
NS_SWIFT_NAME(mrgsAppId);

/**
 @discussion MRGS user identifier. Optional.
 */
@property(copy, nullable) NSString *mrgsUserId
NS_SWIFT_NAME(mrgsUserId);

/**
 @discussion MRGS device identifier. Optional.
 */
@property(copy, nullable) NSString *mrgsDeviceId
NS_SWIFT_NAME(mrgsDeviceId);

/**
 @discussion ICQ identifier. Optional.
 */
@property(copy, nullable) NSString *icqId
NS_SWIFT_NAME(icqId);

/**
 @discussion OK identifier. Optional.
 */
@property(copy, nullable) NSString *okId
NS_SWIFT_NAME(okId);

/**
 @discussion VK identifier. Optional.
 */
@property(copy, nullable) NSString *vkId
NS_SWIFT_NAME(vkId);

/**
 @discussion VKConnect identifier. Optional.
 */
@property(copy, nullable) NSString *vkConnectId
NS_SWIFT_NAME(vkConnectId);

/**
 @discussion User's email. Optional.
 */
@property(copy, nullable) NSString *email
NS_SWIFT_NAME(email);

/**
 @discussion User's phone number. Optional.
 */
@property(copy, nullable) NSString *phone
NS_SWIFT_NAME(phone);

/**
 @discussion User's custom identifier. Optional.
 */
@property(copy, nullable) NSString *customUserId
NS_SWIFT_NAME(customUserId);

/**
 @discussion NSArray of ICQ identifiers. Optional.
 */
@property(nullable) NSArray<NSString *> *icqIds
NS_SWIFT_NAME(icqIds);

/**
 @discussion NSArray of OK identifiers. Optional.
 */
@property(nullable) NSArray<NSString *> *okIds
NS_SWIFT_NAME(okIds);

/**
 @discussion NSArray of VK identifiers. Optional.
 */
@property(nullable) NSArray<NSString *> *vkIds
NS_SWIFT_NAME(vkIds);

/**
 @discussion NSArray of VKConnect identifiers. Optional.
 */
@property(nullable) NSArray<NSString *> *vkConnectIds
NS_SWIFT_NAME(vkConnectIds);

/**
 @discussion NSArray of emails. Optional.
 */
@property(nullable) NSArray<NSString *> *emails
NS_SWIFT_NAME(emails);

/**
 @discussion NSArray of phone numbers. Optional.
 */
@property(nullable) NSArray<NSString *> *phones
NS_SWIFT_NAME(phones);

/**
 @discussion NSArray of custom user's identifiers. Optional.
 */
@property(nullable) NSArray<NSString *> *customUserIds
NS_SWIFT_NAME(customUserIds);

@end

NS_ASSUME_NONNULL_END

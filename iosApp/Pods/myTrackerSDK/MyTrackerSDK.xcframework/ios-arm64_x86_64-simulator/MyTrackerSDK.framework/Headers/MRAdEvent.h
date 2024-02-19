//
//  MRAdEvent.h
//  myTrackerSDK
//

#import <MyTrackerSDK/MRAdNetwork.h>
#import <MyTrackerSDK/MRFinal.h>
#import <MyTrackerSDK/MRMyTrackerEvent.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class describes an advertising event
*/
MR_FINAL

@interface MRAdEvent : MRMyTrackerEvent

/**
 @discussion Stores advertising network
*/
@property(nonatomic, readonly) MRAdNetwork network
NS_SWIFT_NAME(network);

/**
 @discussion Stores revenue
 */
@property(nonatomic, readonly) double revenue
NS_SWIFT_NAME(revenue);

/**
 @discussion Stores currency code  in ISO 4217 format
 */
@property(nullable, nonatomic, readonly) NSString *currency
NS_SWIFT_NAME(currency);

/**
 @discussion Stores initial source
 */
@property(nullable, nonatomic, readonly) NSString *source
NS_SWIFT_NAME(source);

/**
 @discussion Stores placement identifier
 */
@property(nullable, nonatomic, readonly) NSString *placementId
NS_SWIFT_NAME(placementId);

/**
 @discussion Stores advertising identifier
 */
@property(nullable, nonatomic, readonly) NSString *adId
NS_SWIFT_NAME(adId);

/**
 @discussion Stores advertising format
 */
@property(nullable, nonatomic, readonly) NSString *format
NS_SWIFT_NAME(format);

@end

NS_ASSUME_NONNULL_END

//
//  MREventBuilder.h
//  myTrackerSDK
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRAdEvent.h>
#import <MyTrackerSDK/MRFinal.h>

NS_ASSUME_NONNULL_BEGIN

MR_FINAL

@interface MRAdEventBuilder : NSObject

/**
@discussion Create new ad click event builder

@param network Advertising network
 
@return MRAdEventBuilder instance
*/
+ (MRAdEventBuilder *)newClickBuilder:(MRAdNetwork)network NS_SWIFT_NAME

(
newClickBuilder(network
:));

/**
@discussion Create new ad impression event builder

@param network Advertising network
 
@return MRAdEventBuilder instance
*/
+ (MRAdEventBuilder *)newImpressionBuilder:(MRAdNetwork)network NS_SWIFT_NAME

(
newImpressionBuilder(network
:));

/**
@discussion Create new ad revenue event builder

@param network Advertising network
@param revenue Revenue value
@param currency Currency code in ISO 4217 format
 
@return MRAdEventBuilder instance
*/
+ (MRAdEventBuilder *)newRevenueBuilder:(MRAdNetwork)network
                                revenue:(double)revenue
                               currency:(NSString *)currency NS_SWIFT_NAME

(
newRevenueBuilder(network
:revenue:currency:));

/**
@discussion Set initial source

@param source Source value
 
@return MRAdEventBuilder instance
*/
- (MRAdEventBuilder *)withSource:(nullable NSString

*)
source NS_SWIFT_NAME(withSource(_
:));

/**
@discussion Set placement identifier

@param placementId Placement identifier value
 
@return MRAdEventBuilder instance
*/
- (MRAdEventBuilder *)withPlacementId:(nullable NSString

*)
placementId NS_SWIFT_NAME(withPlacementId(_
:));

/**
@discussion Set advertising identifier

@param adId Advertising identifier value
 
@return MRAdEventBuilder instance
*/
- (MRAdEventBuilder *)withAdId:(nullable NSString

*)
adId NS_SWIFT_NAME(withAdId(_
:));

/**
@discussion Set advertising format

@param format Advertising format. The value could be defined customly or chosen from MRAdFormat

@return MRAdEventBuilder instance
*/
- (MRAdEventBuilder *)withAdFormat:(nullable NSString

*)
format NS_SWIFT_NAME(withAdFormat(_
:));

/**
@discussion Create new advertising event instance with previously specified values.
 
@return MRAdEvent instance
*/
- (MRAdEvent *)build

NS_SWIFT_NAME (build());

@end

NS_ASSUME_NONNULL_END

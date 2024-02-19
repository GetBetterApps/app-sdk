//
//  MRMyTrackerAttribution.h
//  myTrackerSDK
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@class MRMyTrackerAttribution;

@protocol MRMyTrackerAttributionDelegate <NSObject>

/**
 @discussion Implementation of this method will get MRMyTrackerAttribution instance as a parameter, use it to make correct behaviour of your application.
 
 @param attribution MRMyTrackerAttribution instance with deeplink.
 */
- (void)didReceiveAttribution:(MRMyTrackerAttribution *)attribution NS_SWIFT_NAME

(
didReceive(attribution
:));

@end

@interface MRMyTrackerAttribution : NSObject

/**
 Contains deeplink as NSString. 
 */
@property(nonatomic, readonly, copy, nullable) NSString *deeplink
NS_SWIFT_NAME(deeplink);

+ (instancetype)attributionWithDeeplink:(NSString *)deeplink NS_SWIFT_NAME

(
attribution(deeplink
:));

- (instancetype)init

NS_UNAVAILABLE;

@end

NS_ASSUME_NONNULL_END

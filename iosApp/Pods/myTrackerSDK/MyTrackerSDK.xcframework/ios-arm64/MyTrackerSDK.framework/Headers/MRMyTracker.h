//
//  MRMyTracker.h
//  myTrackerSDK
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRMyTrackerParams.h>
#import <MyTrackerSDK/MRMyTrackerConfig.h>
#import <MyTrackerSDK/MRMyTrackerAttribution.h>
#import <MyTrackerSDK/MRMiniAppEvent.h>

@class MRMyTrackerParams;
@class MRMyTrackerConfig;
@class SKProduct;
@class SKPaymentTransaction;
@class MRAdEvent;
@protocol MRLogger;

NS_ASSUME_NONNULL_BEGIN

@interface MRMyTracker : NSObject

/**
 @discussion Set a delegate to receive the deferred deeplink. Delegate must implement MRMyTrackerAttributionDelegate protocol.
 
 @param delegate Sets delegate for attribution.
 */
+ (void)setAttributionDelegate:(nullable id

<MRMyTrackerAttributionDelegate>)
delegate NS_SWIFT_NAME(setAttribution(delegate
:));

/**
 @discussion Set up a queue for delegate methods execution. If queue is not set up delegate's methods would be called on the main queue.

 @param delegate Sets delegate for attribution.
 @param queue Queue on which delegate's methods wil invoke.
 */
+ (void)setAttributionDelegate:(nullable id

<MRMyTrackerAttributionDelegate>)
delegate
        delegateQueue
:(
nullable NSOperationQueue
*)
queue NS_SWIFT_NAME(setAttribution(delegate
:queue:));

/**
 @discussion Debug on/off. NO by default.
 
 @param enabled Set YES to enable debug mode or NO to disable.
 */
+ (void)setDebugMode:(BOOL)enabled NS_SWIFT_NAME

(
setDebugMode(enabled
:));

/**
 @discussion Get current status of debug mode.
 
 @return Current debug mode status.
 */
+ (BOOL)isDebugMode

NS_SWIFT_NAME (isDebugMode());

/**
 @discussion Get current version of MyTracker.
 
 @return Current version.
*/
+ (NSString *)trackerVersion

NS_SWIFT_NAME (trackerVersion());

/**
 @discussion Tracker parameters can be set up in MRMyTrackerParams class instance available through this property. It is important to set the parameter before tracking events to pass user identifier with every event.
 
 @return MRMyTrackerParams instance.
 */
+ (MRMyTrackerParams *)trackerParams

NS_SWIFT_NAME (trackerParams());

/**
 @discussion Configuration can be set up in MRMyTrackerConfig class instance available through this property.
 
 @return MRMyTrackerConfig instance.
 */
+ (MRMyTrackerConfig *)trackerConfig

NS_SWIFT_NAME (trackerConfig());

/**
 @discussion Use to get current instance identifier. Don't use this method on the main queue.
 
 @return Current instance identifier.
 */
+ (NSString *)instanceId

NS_SWIFT_NAME (instanceId());

/**
 @discussion Get a customizable logger for receiving messages from MyTrackerSDK.
 
 @return Current custom logger.
 */
+ (nullable id

<MRLogger>)
customLogger;

/**
 @discussion Set a customizable logger for receiving messages from MyTrackerSDK.
 
 @param customLogger Sets the custom logger.
 */
+ (void)setCustomLogger:(nullable id

<MRLogger>)
customLogger;

/**
 @discussion Setup MyTracker with SDK_KEY.
 
 @param trackerId NSString with tracker identifier.
 */
+ (void)setupTracker:(NSString *)trackerId NS_SWIFT_NAME

(
setupTracker(_
:));

/**
 @discussion By default, data is sent every 15 minutes. The interval can be changed to anywhere from 1 second to 1 day through the bufferingPeriod property. If the user has quit the app, the events will be sent during next launch. It is extremely important to analyse certain events as soon as possible, especially in the first sessions since installing the app. This method will force sends all stored data.
 */
+ (void)flush

NS_SWIFT_NAME (flush());

+ (void)flushWithCompletionBlock:(void (^)(BOOL success))completionBlock NS_SWIFT_NAME

(
flush(completion
:));

/**
 @discussion Any user defined event with a custom name.
 
 @param name Event name. Max name length — 255 chars.
 */
+ (void)trackEventWithName:(NSString *)name NS_SWIFT_NAME

(
trackEvent(name
:));

/**
 @discussion Any user defined event with a custom name. Any optional parameters can be passed with event as «key-value» by optional parameter eventParams. Max name, key or value length — 255 chars.
 
 @param name Event name.
 @param eventParams Event parameters presented as NSDictionary<NSString *, NSString *>*. Max key or value length — 255 chars.
 */
+ (void)trackEventWithName:(NSString *)name
               eventParams:(nullable NSDictionary

<NSString *, NSString *> *)
eventParams NS_SWIFT_NAME(trackEvent(name
:eventParams:));

/**
 @discussion Call the method right after user successfully authorized in the app and got a unique identifier.
 
 @param userId Unique user identifier.
 */
+ (void)trackLoginEvent:(NSString *)userId
        withVkConnectId:(NSString * _Nullable)vkcId NS_SWIFT_NAME

(
trackLoginEvent(userId
:vkConnectId:));

/**
 @discussion Call the method right after user successfully authorized in the app and got a unique identifier. This parameter allows you to pass user identifier with tracked event and get reliable user statistics.
 
 @param userId Unique user identifier.
 @param eventParams Login event parameters. Max key or value length — 255 chars.
 */
+ (void)trackLoginEvent:(NSString *)userId
        withVkConnectId:(NSString * _Nullable)vkcId
                 params:(nullable NSDictionary

<NSString *, NSString *> *)
eventParams NS_SWIFT_NAME(trackLoginEvent(userId
:vkConnectId:eventParams:));

/**
 @discussion Call the method if current user has invited other user.
 */
+ (void)trackInviteEvent

NS_SWIFT_NAME (trackInviteEvent());

/**
 Call the method if current user was invited by other user.
 
 @param eventParams Any optional parameters can be passed with event as «key-value» by optional parameter eventParams. Max key or value length — 255 chars.
*/
+ (void)trackInviteEventWithParams:(nullable NSDictionary

<NSString *, NSString *> *)
eventParams NS_SWIFT_NAME(trackInviteEvent(eventParams
:));

/**
 @discussion Call the method right after user registration completed.
 
 @param userId Unique user identifier.
*/
+ (void)trackRegistrationEvent:(NSString *)userId withVkConnectId:(NSString * _Nullable)vkcId NS_SWIFT_NAME

(
trackRegistrationEvent(userId
:vkConnectId:));

/**
 @discussion Call the method right after user registration completed.
 
 @param userId Unique user identifier.
 @param eventParams Any optional parameters can be passed with event as «key-value» by optional parameter eventParams. Max key or value length — 255 chars.
*/
+ (void)trackRegistrationEvent:(NSString *)userId
               withVkConnectId:(NSString * _Nullable)vkcId
                        params:(nullable NSDictionary

<NSString *, NSString *> *)
eventParams NS_SWIFT_NAME(trackRegistrationEvent(userId
:vkConnectId:eventParams:));

/**
 @discussion All in app purchases will be tracked automatically, unless you manually change this behavior by setting autotrackPurchase property to «NO» (autotrackPurchase = NO). In this case, to track in app purchases you could use this method
 
 @param product Instance of SKProduct.
 @param transaction instance of SKPaymentTransaction.
*/
+ (void)trackPurchaseWithProduct:(SKProduct *)product
                     transaction:(SKPaymentTransaction *)transaction NS_SWIFT_NAME

(
trackPurchase(product
:transaction:));

/**
 @discussion All in app purchases will be tracked automatically, unless you manually change this behavior by setting autotrackPurchase property to «NO» (autotrackPurchase = NO). In this case, to track in app purchases you could use this method
 
 @param product Instance of SKProduct.
 @param transaction instance of SKPaymentTransaction.
 @param eventParams Any optional parameters can be passed with event as «key-value» by optional parameter eventParams. Max key or value length — 255 chars.
*/
+ (void)trackPurchaseWithProduct:(SKProduct *)product
                     transaction:(SKPaymentTransaction *)transaction
                     eventParams:(nullable NSDictionary

<NSString *, NSString *> *)
eventParams NS_SWIFT_NAME(trackPurchase(product
:transaction:eventParams:));

/**
 @discussion Call the method when user achieve new level.
*/
+ (void)trackLevelAchieved

NS_SWIFT_NAME (trackLevelAchieved());

/**
 @discussion Call the method when user achieve new level.
 
 @param level Achieved level, optional.
*/
+ (void)trackLevelAchievedWithLevel:(nullable NSNumber

*)
level NS_SWIFT_NAME(trackLevelAchieved(level
:));

/**
 @discussion Call the method when user achieve new level.
 
 @param level Achieved level, optional.
 @param eventParams  Any optional parameters can be passed with event as «key-value» by optional parameter eventParams. Max key or value length — 255 chars.
*/
+ (void)trackLevelAchievedWithLevel:(nullable NSNumber

*)
level
        eventParams
:(
nullable NSDictionary<NSString *, NSString *>
*)
eventParams NS_SWIFT_NAME(trackLevelAchieved(level
:eventParams:));

/**
 @discussion Track advertising event. Call this method when an advertising event has occurred.
 
 @param event MRAdEvent instance.
*/
+ (void)trackAdEvent:(MRAdEvent *)event NS_SWIFT_NAME

(
trackAdEvent(_
:))
NS_SWIFT_NAME(trackAdEvent(_
:));

/**
 @discussion Track mini-app event. Call this method when an event in mini-app has occurred.
 
 @param event MRMiniAppEvent instance.
 */
+ (void)trackMiniAppEvent:(MRMiniAppEvent *)event NS_SWIFT_NAME

(
trackMiniAppEvent(_
:));

/**
 @discussion Call the method from the -application:continueUserActivity:restorationHandler: method of UIApplicationDelegate to get deeplink if it exists and track appication open by deeplink. If deeplink exists and valid attributionDelegate would be called with MRMyTrackerAttribution instance which will contain correct deeplink.
 
 @param userActivity The activity object containing the data associated with the task the user was performing. Use the data to continue the user's activity in your iOS app.
 @param restorationHandler Block/Closure, use parameter from method of UIApplicationDelegate, optional.
 
 @return BOOL value.
*/
+ (BOOL)continueUserActivity:(NSUserActivity *)userActivity
          restorationHandler:(nullable void (^)(NSArray *_Nullable
                                                restorableObjects))restorationHandler NS_SWIFT_NAME

(continue(userActivity:restorationHandler:));

/**
 @discussion Call the method from the -application:openURL:options: method of UIApplicationDelegate to get deeplink if it exists and track appication open by deeplink. If deeplink exists and valid attributionDelegate would be called with MRMyTrackerAttribution instance which will contain correct deeplink.
 
 @param url The URL resource to open. This resource can be a network resource or a file. For information about the Apple-registered URL schemes, see Apple URL Scheme Reference.
 @param sourceApplication The bundle ID of the app that is requesting your app to open the URL.
 @param annotation A property list supplied by the source app to communicate information to the receiving app.
 
 @return BOOL value.
*/
+ (BOOL)handleOpenURL:(NSURL *)url
    sourceApplication:(nullable NSString

*)
sourceApplication
        annotation
:(
nullable id
)
annotation NS_SWIFT_NAME(handleOpen(url
:sourceApplication:annotation:));

/**
 @discussion Call the method form the -application:openURL:options: method of UIApplicationDelegate to get deeplink if it exists and track appication open by deeplink. If deeplink exists and valid attributionDelegate would be called with MRMyTrackerAttribution instance which will contain correct deeplink.
 
 @param url The URL resource to open. This resource can be a network resource or a file. For information about the Apple-registered URL schemes, see Apple URL Scheme Reference.
 @param options A dictionary of URL handling options. For information about the possible keys in this dictionary and how to handle them, see UIApplicationOpenURLOptionsKey. By default, the value of this parameter is an empty dictionary.
 
 @return BOOL value.
*/
+ (BOOL)handleOpenURL:(NSURL *)url options:(nullable NSDictionary

*)
options NS_SWIFT_NAME(handleOpen(url
:options:));

@end

NS_ASSUME_NONNULL_END

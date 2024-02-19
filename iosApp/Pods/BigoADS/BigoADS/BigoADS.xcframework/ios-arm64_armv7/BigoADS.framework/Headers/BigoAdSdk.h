//
//  BigoAdSdk.h
//  AFNetworking
//
//  Created by 蔡庆敏 on 2021/5/19.
//

#import <Foundation/Foundation.h>
#import "BigoAdConfig.h"
#import "BigoConsentOptions.h"

NS_ASSUME_NONNULL_BEGIN

@interface BigoAdSdk : NSObject

+ (instancetype)sharedInstance;

- (NSString *)getSDKVersion;

- (NSString *)getSDKVersionName;

- (BOOL)isInitialized;

- (NSString *)getBidderToken;

/**
 * Initializes the BigoAdSdk SDK asynchronously on a background thread.
 * @remark This should be called from the app's `application:didFinishLaunchingWithOptions:` method.
 * @param adConfig Required SDK configuration options.
 * @param completionBlock Optional completion block that will be called when initialization has completed.
 */
- (void)initializeSdkWithAdConfig:(BigoAdConfig * _Nonnull)adConfig
                       completion:(void (^_Nullable)

(void))
completionBlock;


+ (void)setUserConsentWithOption:(BigoConsentOptions)option consent:(BOOL)consent;

+ (void)addExtraHostWithCountry:(NSString *)country host:(NSString *)host;

@end

NS_ASSUME_NONNULL_END

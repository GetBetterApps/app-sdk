#import <Foundation/Foundation.h>
#import "SSESKAdNetworkServiceEditor.h"
#import "SSESKAdNetworkServiceProvider.h"

NS_ASSUME_NONNULL_BEGIN

static NSString
*
const kSKAdNetworkItemsPlistKey = @"SKAdNetworkItems";
static NSString *const kSKAdNetworkIdPlistKey = @"SKAdNetworkIdentifier";

@interface SSESKAdNetworkService
        : NSObject <SSESKAdNetworkServiceEditor, SSESKAdNetworkServiceProvider>

@property(nonatomic, assign) BOOL shouldUseIdfv;

+ (NSSet *)collectNetworkIdsFromPlist;

@end

NS_ASSUME_NONNULL_END

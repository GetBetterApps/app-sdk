//
//  MTRGUtils.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 29/05/2020.
//  Copyright © 2020 Mail.ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreGraphics/CGBase.h>

NS_ASSUME_NONNULL_BEGIN

typedef enum : NSUInteger {
    MTRGCompareResultLess,
    MTRGCompareResultEquals,
    MTRGCompareResultGreater
} MTRGCompareResult;

extern MTRGCompareResult mtrgCompareFloat(float lhs, float rhs);

extern MTRGCompareResult mtrgCompareDouble(double lhs, double rhs);

extern MTRGCompareResult mtrgCompareCGFloat(CGFloat lhs, CGFloat rhs);

/**
 @discussion Utils class.
 */
@interface MTRGUtils : NSObject

/**
 @discussion Method to get fingerprint parameters
 
 @return Fingerprint parameters as NSDictionary
 */
+ (NSDictionary

<NSString *, NSString *> *)
getFingerprintParams; // this method should be called on background thread

/**
 @discussion Method to set track URL
 
 @param url URL as NSString
 */
+ (void)trackUrl:(NSString *)url;

@end

NS_ASSUME_NONNULL_END

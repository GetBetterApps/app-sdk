//
//  MRPersonalizeResponse.h
//  MyTrackerSDK 3.1.5
//
//  Created by Andrey Seredkin on 25.10.2022.
//  Copyright © 2022 VK. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRFinal.h>

@class MRPersonalizePlacement;

NS_ASSUME_NONNULL_BEGIN

MR_FINAL

@interface MRPersonalizeResponse : NSObject

/**
 @discussion An array of MRPersonalizePlacement objects, optional.
*/
@property(nonatomic, nullable, readonly) NSArray<MRPersonalizePlacement *> *placements;

/**
 @discussion Error message, optional.
*/
@property(nonatomic, nullable, readonly) NSString *error;

/**
 @discussion Raw data from server, optional.
*/
@property(nonatomic, nullable, readonly) NSData *rawData;

+ (instancetype)new

NS_UNAVAILABLE;

- (instancetype)init

NS_UNAVAILABLE;

/**
 @discussion: Creates new instance of class.

 @param placements An array of MRPersonalizePlacement objects, optional.
 @param error Error message, optional.
 @param rawData Raw data from server, optional.

 @return An instance of MRPersonalizeResponse class.
*/
- (instancetype)initWithPlacements:(nullable NSArray

<MRPersonalizePlacement *> *)
placements
        error
:(
nullable NSString
*)
error
        rawData
:(
nullable NSData
*)
rawData NS_SWIFT_NAME(init(placements
:error:rawData:));

@end

NS_ASSUME_NONNULL_END

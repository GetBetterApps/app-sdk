//
//  MROfferRequest.h
//  MyTrackerSDK 3.1.5
//
//  Created by Alexander Zakatnov on 27.04.2021.
//  Copyright © 2021 Mail.Ru Group. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MyTrackerSDK/MRFinal.h>

@class MROffer;
@class MROfferRequest;

typedef NS_ENUM(
int, MROfferRequestReset)
{
MROfferRequestResetUndefined = -1,
        MROfferRequestResetFalse,
        MROfferRequestResetTrue
};

NS_ASSUME_NONNULL_BEGIN

@protocol MROfferRequestOnCompleteDelegate <NSObject>

/**
 @discussion Will be called on response
 
 @param request Completed request
 @param offers Array of MROffer instances
 @param error NSError instance
 */
- (void)onComplete:(MROfferRequest *)request
            offers:(nullable NSArray

<MROffer *> *)
offers
        error
:(
nullable NSString
*)
error NS_SWIFT_NAME(onComplete(request
:offers:error:));

@end

MR_FINAL
@interface MROfferRequest : NSObject

@property(nonatomic, readonly, nullable) NSArray<NSString *> *placementIds
NS_SWIFT_NAME(placementIds);

@property(nonatomic, readonly, nullable) NSString *userId
NS_SWIFT_NAME(userId);

@property(nonatomic, readonly) MROfferRequestReset reset
NS_SWIFT_NAME(reset);

@property(nonatomic, readonly, nullable) NSString *data
NS_SWIFT_NAME(data);

@property(nonatomic, readonly, weak) id <MROfferRequestOnCompleteDelegate> delegate
NS_SWIFT_NAME(delegate);

@property(nonatomic, readonly) NSOperationQueue *queue
NS_SWIFT_NAME(queue);

/**
 @discussion Sends a request for offers
 */
- (void)send

NS_SWIFT_NAME (send());

@end

NS_ASSUME_NONNULL_END

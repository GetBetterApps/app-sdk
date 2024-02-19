//
//  MTRGPromoCardCollectionView.h
//  myTargetSDK 5.20.2
//
//  Created by Andrey Seredkin on 02.11.16.
//  Copyright © 2016 Mail.ru Group. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MyTargetSDK/MTRGNativePromoCard.h>
#import <MyTargetSDK/MTRGPromoCardSliderProtocol.h>

NS_ASSUME_NONNULL_BEGIN

/**
 @discussion Class for promo card collection view.
 */
@interface MTRGPromoCardCollectionView : UICollectionView <MTRGPromoCardSliderProtocol, UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout>

/**
 @discussion Card slider's delegate. Must conforms MTRGPromoCardSliderDelegate protocol.
 */
@property (nonatomic, weak, nullable) id <MTRGPromoCardSliderDelegate> cardSliderDelegate;

/**
 @discussion Flag determines it is clickable or not.
 */
@property (nonatomic) BOOL isCardsClickable;
/**
 @discussion Scale for cards (relation of card's width to collection width).
 */
@property (nonatomic, readonly) CGFloat cardScaleFactor;
/**
 @discussion Spacing between cards.
 */
@property (nonatomic, readonly) CGFloat cardSpacing;

/**
 @discussion Static constructor. Creates instance of the class.
 
 @return Instance of the class.
 */
+ (instancetype)create;

/**
 @discussion Static constructor. Creates instance of the class with additional options.
 
 @param cardScaleFactor Scale for cards (relation of card's width to collection width).
 @param cardSpacing Spacing between cards.

 @return Instance of the class.
 */
+ (instancetype)createWithCardScaleFactor:(CGFloat)cardScaleFactor cardSpacing:(CGFloat)cardSpacing;

/**
 @discussion Sets cards for the collection.
 
 @param cards Array of promo cards.
 */
- (void)setCards:(NSArray<MTRGNativePromoCard *> *)cards;

@end

NS_ASSUME_NONNULL_END

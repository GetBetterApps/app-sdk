//
//  OffersSheet.swift
//  iosApp
//
//  Created by velkonost on 10.02.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct OffersSheet: View {
    
    private let items: [SubscriptionType]
    private let selectedItem: SubscriptionType
    private let itemClick: (SubscriptionType) -> Void
    
    @Binding var sheetHeight: CGFloat
    
    init(
        items: [SubscriptionType],
        selectedItem: SubscriptionType,
        sheetHeight: Binding<CGFloat>,
        itemClick: @escaping (SubscriptionType) -> Void
    ) {
        self.items = items
        self.selectedItem = selectedItem
        self.itemClick = itemClick
        self._sheetHeight = sheetHeight
    }
    
    
    var body: some View {
        ZStack {
            Color.backgroundItem.edgesIgnoringSafeArea(.all)
            
            VStack(spacing: 0) {
                HStack {
                    Spacer()
                    Image(uiImage: SharedR.images().ic_grabber.toUIImage()!)
                        .resizable()
                        .renderingMode(.template)
                        .foregroundColor(.onboardingBackgroundGradientStart)
                        .frame(width: 36, height: 5)
                    Spacer()
                }
                
                Spacer().frame(height: 16)
                
                ForEach(items, id: \.self) { item in
                    OfferView(
                        item: item,
                        selected: item == selectedItem
                    )
                    .contentShape(Rectangle())
                    .onTapGesture {
                        itemClick(item)
                    }
                }
                
                HStack {
                    Spacer()
                    AppButton(
                        labelText: SharedR.strings().subscription_confirm.desc().localized(),
                        isLoading: false,
                        onClick: {
                            
                        }
                    )
                    Spacer()
                }
                .padding(.top, 32)
               
            }
            .padding(.horizontal, 16)
            .padding(.bottom, 48)
            .padding(.top, 16)
            .overlay {
                GeometryReader { geometry in
                    Color.clear.preference(key: InnerHeightPreferenceKey.self, value: geometry.size.height)
                }
            }
            .onPreferenceChange(InnerHeightPreferenceKey.self) { newHeight in
                if newHeight < UIScreen.screenHeight - 10 {
                    sheetHeight = newHeight
                }
            }
            .presentationDetents([.height(sheetHeight)])
        }
    }
}

struct OfferView: View {
    
    private let item: SubscriptionType
    private let selected: Bool

    init(item: SubscriptionType, selected: Bool) {
        self.item = item
        self.selected = selected
    }
    
    var body: some View {
        HStack(spacing: 0) {
            Image(uiImage: selected ? SharedR.images().emoji_59.toUIImage()! : SharedR.images().emoji_11.toUIImage()!)
                .resizable()
                .frame(width: 32, height: 32)
            
            VStack {
                
                HStack {
                    Text(item.title.localized())
                        .style(.titleSmall)
                        .foregroundColor(.textTitle)
                    Spacer()
                }
                
                HStack {
                    Text(item.text.localized())
                        .style(.bodySmall)
                        .foregroundColor(.textPrimary)
                    Spacer()
                }
            }
            .padding(.leading, 12)
            
            Spacer()
            
            Text(item.price.localized())
                .style(.titleMedium)
                .foregroundColor(.textTitle)
        }
        .padding(16)
        .background(Color.backgroundItem)
        .cornerRadius(12)
        .overlay{
            RoundedRectangle(cornerRadius: 12)
                .stroke(selected ? Color.onboardingBackgroundGradientStart : Color.textPrimary, lineWidth: 1)
        
        }
        .shadow(radius: selected ? 8 : 0)
        .padding(.top, 16)
        .animation(.easeInOut, value: selected)
    }
}

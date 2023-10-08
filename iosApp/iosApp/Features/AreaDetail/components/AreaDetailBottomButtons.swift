//
//  AreaDetailBottomButtons.swift
//  iosApp
//
//  Created by velkonost on 08.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import SharedSDK

struct AreaDetailBottomButtons: View {
    
    let isEditing: Bool
    let onEditClick: () -> Void
    let onLeaveClick: () -> Void
    let onDeleteClick: () -> Void
    let onSaveClick: () -> Void
    
    init(
        isEditing: Bool,
        onEditClick: @escaping () -> Void,
        onLeaveClick: @escaping () -> Void,
        onDeleteClick: @escaping () -> Void,
        onSaveClick: @escaping () -> Void
    ) {
        self.isEditing = isEditing
        self.onEditClick = onEditClick
        self.onLeaveClick = onLeaveClick
        self.onDeleteClick = onDeleteClick
        self.onSaveClick = onSaveClick
    }
    
    var body: some View {
        ZStack {
            VStack(spacing: 0) {
                Spacer()
                
                if !isEditing {
                    Text(SharedR.strings().add_area_edit_button.desc().localized())
                        .style(.titleMedium)
                        .foregroundColor(.textTitle)
                        .padding(.top, 24)
                        .padding(.bottom, 24)
                        .frame(minWidth: 0, maxWidth: .infinity)
                        .background(Color.textFieldBackground)
                        .onTapGesture {
                            let impactMed = UIImpactFeedbackGenerator(style: .medium)
                            impactMed.impactOccurred()
                            onEditClick()
                        }
                    
                    HStack(spacing: 0) {
                        Text(SharedR.strings().add_area_leave_button.desc().localized())
                            .style(.titleMedium)
                            .foregroundColor(.textLight)
                            .padding(.top, 24)
                            .padding(.bottom, 36)
                            .frame(width: UIScreen.screenWidth * 0.5)
                            .background(Color.onboardingBackgroundGradientStart)
                            .onTapGesture {
                                let impactMed = UIImpactFeedbackGenerator(style: .medium)
                                impactMed.impactOccurred()
                                onLeaveClick()
                            }
                        
                        Text(SharedR.strings().add_area_delete_button.desc().localized())
                            .style(.titleMedium)
                            .foregroundColor(.textTitle)
                            .padding(.top, 24)
                            .padding(.bottom, 36)
                            .frame(width: UIScreen.screenWidth * 0.5)
                            .background(Color.backgroundItem)
                            .onTapGesture {
                                let impactMed = UIImpactFeedbackGenerator(style: .medium)
                                impactMed.impactOccurred()
                                onDeleteClick()
                            }
                    }
                }
            }
            
            if isEditing {
                VStack {
                    Spacer()
                    Text(SharedR.strings().add_area_save_button.desc().localized())
                        .style(.titleMedium)
                        .foregroundColor(.textLight)
                        .padding(.top, 24)
                        .padding(.bottom, 36)
                        .frame(minWidth: 0, maxWidth: .infinity)
                        .background(Color.onboardingBackgroundGradientStart)
                        .onTapGesture {
                            let impactMed = UIImpactFeedbackGenerator(style: .medium)
                            impactMed.impactOccurred()
                            onSaveClick()
                        }
                }
            }
        }
        .animation(.easeInOut(duration: 1), value: isEditing)
    }
}

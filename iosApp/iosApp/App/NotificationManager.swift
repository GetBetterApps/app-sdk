////
////  NotificationManager.swift
////  iosApp
////
////  Created by velkonost on 24.11.2023.
////  Copyright © 2023 orgName. All rights reserved.
////
//
//import Foundation
//import UserNotifications
//
//@MainActor
//class NotificationManager: ObservableObject{
//    @Published private(set) var hasPermission = false
//    
//    init() {
//        Task{
//            await getAuthStatus()
//        }
//    }
//    
//    func request() async{
//        do {
//            try await UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .badge, .sound])
//             await getAuthStatus()
//        } catch{
//            print(error)
//        }
//    }
//    
//    func getAuthStatus() async {
//        let status = await UNUserNotificationCenter.current().notificationSettings()
//        switch status.authorizationStatus {
//        case .authorized, .ephemeral, .provisional:
//            hasPermission = true
//        default:
//            hasPermission = false
//        }
//    }
//}

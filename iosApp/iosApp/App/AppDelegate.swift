//
//  AppDelegate.swift
//  iosApp
//
//  Created by velkonost on 09.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import FirebaseCore
import FirebaseMessaging

class AppDelegate: NSObject, UIApplicationDelegate
//                    MessagingDelegate, UNUserNotificationCenterDelegate
{
    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
//        application.registerForRemoteNotifications()
        FirebaseApp.configure()
//        Messaging.messaging().delegate = self
//        UNUserNotificationCenter.current().delegate = self
        
        return true
    }
    
    func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
//        Messaging.messaging().apnsToken = deviceToken
    }
    
    
//    func messaging(_ messaging: Messaging, didReceiveRegistrationToken fcmToken: String?) {
//        if let fcm = Messaging.messaging().fcmToken {
//            print("fcm", fcm)
//        }
//    }
    
}

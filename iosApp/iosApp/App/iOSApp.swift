import SwiftUI
import SharedSDK

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    
    init() {
        KoinApplication.start()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
        }
    }
}

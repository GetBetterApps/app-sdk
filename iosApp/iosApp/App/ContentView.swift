import SwiftUI
import SharedSDK
import KMPNativeCoroutinesAsync

struct ContentView: View {
    
    let navigationScreens: [any NavRoute] = [
        HomeRoute(),
        DetailRoute(),
        ProfileRoute()
    ]
    
    
    @StateObject var pilot = UIPilot(initial: NavigationScreenKt.AUTH_DESTINATION)
    
    @State private var resourceMessageText: String?
    @State private var snackBar: MessageType.SnackBar?
    @State private var showSnackBar: Bool = false
    @State private var showToast: Bool = false
    @State private var messageDequeObserver: Task<(), Error>? = nil
    
    
    var body: some View {
        
        UIPilotHost(pilot) { route in
            var isBottomBarVisible = navigationScreens.map({ screen in
                screen.route
            }).contains(route)
            
            ZStack {
                
                Color.mainBackground.edgesIgnoringSafeArea(.all)
                
                switch route {
                case _ where route.starts(with: NavigationScreenKt.HOME_DESTINATION) :
                    HomeRoute().view(pilot: pilot, route: route)
                    
                case _ where route.starts(with: NavigationScreenKt.DETAIL_DESTINATION) :
                    DetailRoute().view(pilot: pilot, route: route)
                    
                case _ where route.starts(with: NavigationScreenKt.AUTH_DESTINATION) :
                    AuthRoute().view(pilot: pilot, route: route)
                    
                case _ where route.starts(with: NavigationScreenKt.PROFILE_DESTINATION) :
                    ProfileRoute().view(pilot: pilot, route: route)
                    
                default : EmptyView()
                }
                
                if (isBottomBarVisible == true) {
                    VStack {
                        Spacer()
                        BottomBar(screens: navigationScreens, pilot: pilot, currentRoute: route)
                            .padding(.bottom, -5)
                    }
                    .edgesIgnoringSafeArea(.bottom)
                }
            }
        }
        .edgesIgnoringSafeArea(.all)
        //        .toast(
        //            isPresenting: $showToast,
        //            message: String(resourceMessageText?.prefix(120) ?? ""),
        //            icon: nil,
        //            backgroundColor: Color.onSurface.opacity(0.9),
        //            textColor: Color.blue,
        //            autoDismiss: .after(5),
        //            onDisappear: { resourceMessageText = nil }
        //        )
        .snackBar(
            isShowing: $showSnackBar,
            text: resourceMessageText ?? "",
            snackBar: snackBar
        )
        .onAppear {
            if messageDequeObserver == nil {
                messageDequeObserver = Task {
                    for try await message in asyncSequence(for: MessageDeque.shared.invoke()) {
                        handle(resource: message)
                    }
                }
            }
        }
        .onDisappear {
            messageDequeObserver?.cancel()
            messageDequeObserver = nil
        }
    }
    
    private func handle(resource message: Message) {
        switch message.messageType {
            
        case let snackBar as MessageType.SnackBar : do {
            if showSnackBar == false {
                resourceMessageText = message.text != nil ? message.text : message.textResource?.localized()
                self.snackBar = snackBar
                withAnimation {
                    showSnackBar.toggle()
                }
                DispatchQueue.main.asyncAfter(deadline: .now() + 5) {
                    Task { try await MessageDeque.shared.dequeue() }
                }
            }
        }
            
        case _ as MessageType.Toast : do {
            resourceMessageText = message.text
            showToast = true
            DispatchQueue.main.asyncAfter(deadline: .now()) {
                Task { try await MessageDeque.shared.dequeue() }
            }
        }
            
        default: break
        }
    }
}

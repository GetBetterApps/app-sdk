import SwiftUI
import SharedSDK
import KMPNativeCoroutinesAsync

struct ContentView: View {
    
    let navigationTransition = AnyTransition.opacity.animation(.easeOut(duration: 2))

    
    @StateObject var pilot = UIPilot(initial: NavigationScreenKt.AUTH_DESTINATION)
    
    @State private var resourceMessageText: String?
    @State private var snackBar: MessageType.SnackBar?
    @State private var showSnackBar: Bool = false
    @State private var showToast: Bool = false
    @State private var messageDequeObserver: Task<(), Error>? = nil
    
    var body: some View {
            UIPilotHost(pilot) { route in
                switch route {
                case _ where route.starts(with: NavigationScreenKt.HOME_DESTINATION) :
                    HomeRoute().view(pilot: pilot, route: route)
                    
                case _ where route.starts(with: NavigationScreenKt.DETAIL_DESTINATION) :
                    DetailRoute().view(pilot: pilot, route: route)
                    
                case _ where route.starts(with: NavigationScreenKt.AUTH_DESTINATION) :
                    AuthRoute().view(pilot: pilot, route: route)
                    
                default : EmptyView()
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
                    resourceMessageText = message.text
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

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

import SwiftUI
import Combine
import UIKit

class DebugLog: Logger {
    func log(_ value: String) {
        print(value)
    }
}


public class UIPilot<T: Equatable>: ObservableObject {
    
    private let logger: Logger
        
    private var _routes: [T] = []
    
    public var routes: [T] {
        return _routes
    }
    
    var onPush: ((T) -> Void)?
    var onSetRoot: ((T, Int?) -> Void)?
    var onPopLast: ((Int, Bool) -> Void)?

    public init(initial: T? = nil, debug: Bool = false) {
        logger = DebugLog() 
        logger.log("UIPilot - Pilot Initialized.")

        
        if let initial = initial {
            push(initial)
        }
    }

    public func push(_ route: T) {
        logger.log("UIPilot - Pushing \(route) route.")
        self._routes.append(route)
        self.onPush?(route)
    }
    
    public func setRoot(_ route: T) {
        logger.log("UIPilot - Set root \(route) route.")
//        self._routes.removeAll()
        
        let lastIndex = self._routes.firstIndex(of: route)
        if lastIndex != nil {
            self._routes.remove(at: lastIndex!)
        }
        
        self._routes.append(route)
        self.onSetRoot?(route, lastIndex)
    }

    public func pop(animated: Bool = true) {
        if !self._routes.isEmpty {
            let popped = self._routes.removeLast()
            logger.log("UIPilot - \(popped) route popped.")
            onPopLast?(1, animated)
        }
    }

    public func popTo(_ route: T, inclusive: Bool = false, animated: Bool = true) -> Bool {
        logger.log("UIPilot: Popping route \(route).")

        if _routes.isEmpty {
            logger.log("UIPilot - Path is empty.")
            return false
        }

        guard var found = _routes.lastIndex(where: { $0 == route }) else {
            logger.log("UIPilot - Route not found.")
            return false
        }

        if !inclusive {
            found += 1
        }

        let numToPop = (found..<_routes.endIndex).count
        logger.log("UIPilot - Popping \(numToPop) routes")
        _routes.removeLast(numToPop)
        onPopLast?(numToPop, animated)
        
        if numToPop == 0 {
            return false
        }
        
        return true
    }
    
    public func onSystemPop() {
        if !self._routes.isEmpty {
            let popped = self._routes.removeLast()
            logger.log("UIPilot - \(popped) route popped by system")
        }
    }
}

public struct UIPilotHost<T: Equatable, Screen: View>: View {
            
    @StateObject
    var navigationStyle = NavigationStyle()
        
    let pilot: UIPilot<T>
    @ViewBuilder
    let routeMap: (T) -> Screen
    
    public init(_ pilot: UIPilot<T>, @ViewBuilder _ routeMap: @escaping (T) -> Screen) {
        self.pilot = pilot
        self.routeMap = routeMap
    }

    public var body: some View {
        NavigationControllerHost(
            navTitle: navigationStyle.title,
            navHidden: navigationStyle.isHidden,
            uipilot: pilot,
            routeMap: routeMap
        )
        .environmentObject(pilot)
        .environment(\.uipNavigationStyle, navigationStyle)
    }
}

struct NavigationControllerHost<T: Equatable, Screen: View>: UIViewControllerRepresentable {
    
    let navTitle: String
    let navHidden: Bool

    let uipilot: UIPilot<T>
    
    @ViewBuilder
    var routeMap: (T) -> Screen
    
    private func addTransition(nav: UINavigationController, transitionType type: CATransitionType = .fade, duration: CFTimeInterval = 0.6) {
            let transition = CATransition()
            transition.duration = duration
            transition.timingFunction = CAMediaTimingFunction(name: .easeInEaseOut)
            transition.type = type
            nav.view.layer.add(transition, forKey: nil)
        }

    func makeUIViewController(context: Context) -> UINavigationController {
        let navigation = PopAwareUINavigationController<T>()
        navigation.uipilot = uipilot
        
        navigation.popHandler = {
            uipilot.onSystemPop()
        }
        navigation.stackSizeProvider = {
            uipilot.routes.count
        }
        
        for path in uipilot.routes {
            navigation.pushViewController(
                UIHostingController(rootView: routeMap(path)), animated: true
            )
        }
        
        uipilot.onSetRoot = { route, lastIndex in
            addTransition(nav: navigation)
            
            if lastIndex != nil {
                // используется для экранов меню, сохранение состояния
                var vc = navigation.viewControllers
                let prevC = vc[lastIndex!]
                vc.remove(at: lastIndex!)
                vc.append(prevC)
                navigation.setViewControllers(vc, animated: false)
            } else {
                navigation.pushViewController(
                    UIHostingController(rootView: routeMap(route)), animated: false
                )
            }
        }
        
        uipilot.onPush = { route in
            addTransition(nav: navigation)
            
            navigation.pushViewController(
                UIHostingController(rootView: routeMap(route)), animated: true
            )
        }
        
        uipilot.onPopLast = { numToPop, animated in
            addTransition(nav: navigation)
            
            if numToPop == navigation.viewControllers.count {
                navigation.viewControllers = []
            } else {
                let popTo = navigation.viewControllers[navigation.viewControllers.count - numToPop - 1]
                navigation.popToViewController(popTo, animated: animated)
            }
        }
                        
        return navigation
    }
    
    func updateUIViewController(_ navigation: UINavigationController, context: Context) {
        navigation.topViewController?.navigationItem.title = navTitle
        navigation.navigationBar.isHidden = true//navHidden
    }
    
    static func dismantleUIViewController(_ navigation: UINavigationController, coordinator: ()) {
        navigation.viewControllers = []
        (navigation as! PopAwareUINavigationController<T>).popHandler = nil
    }
        
    typealias UIViewControllerType = UINavigationController
}

class PopAwareUINavigationController<E: Equatable>: UINavigationController, UINavigationControllerDelegate, UIGestureRecognizerDelegate
{
    
    var uipilot: UIPilot<E>?
    
    var popHandler: (() -> Void)?
    var stackSizeProvider: (() -> Int)?
    
    var popGestureBeganController: UIViewController?

    override func viewDidLoad() {
        super.viewDidLoad()
        self.delegate = self
        interactivePopGestureRecognizer?.delegate = self
    }
    
    public func gestureRecognizerShouldBegin(_ gestureRecognizer: UIGestureRecognizer) -> Bool {
        guard let route = uipilot?.routes.last as? String else { return false }
        
        let availableRoutes = [
            AddAreaRoute().route,
            FeedbackRoute().route,
            String(NoteDetailRoute().route.prefix(while: { value in
                value != "?"
            }))
        ]
        
        if availableRoutes.contains(route) || !availableRoutes.filter({ route.hasPrefix($0) }).isEmpty {
            return true
        }
        
        return false
    }
    
    func navigationController(_ navigationController: UINavigationController, didShow viewController: UIViewController, animated: Bool) {
        if let stackSizeProvider = stackSizeProvider, stackSizeProvider() > navigationController.viewControllers.count {
            self.popHandler?()
        }
    }
}

extension View {
    public func uipNavigationBarHidden(_ hidden: Bool) -> some View {
        return modifier(NavHiddenModifier(isHidden: hidden))
    }
    
    public func uipNavigationTitle(_ title: String) -> some View {
        return modifier(NavTitleModifier(title: title))
    }

}

private struct NavigationTitleKey: EnvironmentKey {
    static let defaultValue: Binding<String> = .constant("")
}

private struct NavigationHiddenKey: EnvironmentKey {
    static let defaultValue: Binding<Bool> = .constant(false)
}

private struct NavigationStyleKey: EnvironmentKey {
    static let defaultValue: NavigationStyle = NavigationStyle()
}


extension EnvironmentValues {
    
    var uipNavigationStyle: NavigationStyle {
        get { self[NavigationStyleKey.self] }
        set {
            self[NavigationStyleKey.self] = newValue
        }
    }

    var upNavigationHidden: Binding<Bool> {
        get { self[NavigationHiddenKey.self] }
        set {
            self[NavigationHiddenKey.self] = newValue
        }
    }
    
    var upNavigationTitle: Binding<String> {
        get { self[NavigationTitleKey.self] }
        set {
            self[NavigationTitleKey.self] = newValue
        }
    }
}

class NavigationStyle: ObservableObject {
    @Published
    var isHidden = false
    var isHiddenOwner: String = ""

    @Published
    var title = ""
    var titleOwner: String = ""
}

struct NavTitleModifier: ViewModifier {
    let title: String
    
    @State var id = UUID().uuidString
    @State var initialValue: String = ""
    
    @Environment(\.uipNavigationStyle) var navStyle
    
    init(title: String) {
        self.title = title
    }

    func body(content: Content) -> some View {
        
        // In case where title change after onAppear
        if navStyle.titleOwner == id && navStyle.title != title {
            DispatchQueue.main.async {
                navStyle.title = title
            }
        }

        return content
            .onAppear {
                initialValue = navStyle.title
                
                navStyle.title = title
                navStyle.titleOwner = id
            }
            .onDisappear {
                if navStyle.titleOwner == id {
                    navStyle.title = initialValue
                    navStyle.titleOwner = ""
                }
            }
    }
}

struct NavHiddenModifier: ViewModifier {
    let isHidden: Bool
    
    @State var id = UUID().uuidString
    @State var initialValue: Bool = false

    @Environment(\.uipNavigationStyle) var navStyle
    
    func body(content: Content) -> some View {
        
        // In case where isHidden change after onAppear
        if navStyle.isHiddenOwner == id && navStyle.isHidden != isHidden {
            DispatchQueue.main.async {
                navStyle.isHidden = isHidden
            }
        }

        return content
            .onAppear {
                initialValue = navStyle.isHidden
                
                navStyle.isHidden = isHidden
                navStyle.isHiddenOwner = id
            }
            .onDisappear {
                if navStyle.isHiddenOwner == id {
                    navStyle.isHidden = initialValue
                    navStyle.isHiddenOwner = ""
                }
            }
    }
}

protocol Logger {
    func log(_ value: String)
}

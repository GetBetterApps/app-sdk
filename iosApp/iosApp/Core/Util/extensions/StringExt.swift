//
//  StringExt.swift
//  iosApp
//
//  Created by velkonost on 06.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

public extension String {
    func valueOf(param queryParam: String) -> String? {
        guard let url = URLComponents(string: self) else { return nil }
        if let parameters = url.queryItems {
            return parameters.first(where: { $0.name == queryParam })?.value
            
        } else if let paramPairs = url.fragment?.components(separatedBy: "?").last?.components(separatedBy: "&") {
            for pair in paramPairs where pair.contains(queryParam) {
                return pair.components(separatedBy: "=").last
            }
            
            return nil
        } else {
            return nil
        }
    }
}

extension String {
    mutating func replace(_ originalString: String, with newString: String) {
        self = self.replacingOccurrences(of: originalString, with: newString)
    }
}

extension String {
    public var queryParameters: [String: String]? {
        guard
            let components = URLComponents(string: self),
            let queryItems = components.queryItems else { return nil }
        
        return queryItems.reduce(into: [String: String]()) { (result, item) in
            result[item.name] = item.value
        }
    }
}

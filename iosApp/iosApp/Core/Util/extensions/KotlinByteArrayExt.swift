//
//  KotlinByteArrayExt.swift
//  iosApp
//
//  Created by velkonost on 23.10.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import SwiftUI

extension KotlinByteArray {
    static func from(data: Data) -> KotlinByteArray {
        let swiftByteArray = [UInt8](data)
        return swiftByteArray
            .map(Int8.init(bitPattern:))
            .enumerated()
            .reduce(into: KotlinByteArray(size: Int32(swiftByteArray.count))) { result, row in
                result.set(index: Int32(row.offset), value: row.element)
            }
    }
    
    func toData() -> Data? {
        let data = Data(base64Encoded: self.encodeBase64())
        
        return data
    }
}

// Generated code. Do not modify.

import CommonCorePublic
import Foundation
import Serialization

public final class DivCurrencyInputMask: DivInputMaskBase {
  public static let type: String = "currency"
  public let locale: Expression<String>? // at least 1 char
  public let rawTextVariable: String // at least 1 char

  public func resolveLocale(_ resolver: ExpressionResolver) -> String? {
    resolver.resolveStringBasedValue(expression: locale, initializer: { $0 })
  }

  static let localeValidator: AnyValueValidator<String> =
    makeStringValidator(minLength: 1)

  static let rawTextVariableValidator: AnyValueValidator<String> =
    makeStringValidator(minLength: 1)

  init(
    locale: Expression<String>? = nil,
    rawTextVariable: String
  ) {
    self.locale = locale
    self.rawTextVariable = rawTextVariable
  }
}

#if DEBUG
extension DivCurrencyInputMask: Equatable {
  public static func ==(lhs: DivCurrencyInputMask, rhs: DivCurrencyInputMask) -> Bool {
    guard
      lhs.locale == rhs.locale,
      lhs.rawTextVariable == rhs.rawTextVariable
    else {
      return false
    }
    return true
  }
}
#endif

extension DivCurrencyInputMask: Serializable {
  public func toDictionary() -> [String: ValidSerializationValue] {
    var result: [String: ValidSerializationValue] = [:]
    result["type"] = Self.type
    result["locale"] = locale?.toValidSerializationValue()
    result["raw_text_variable"] = rawTextVariable
    return result
  }
}

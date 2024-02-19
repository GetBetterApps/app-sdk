// Copyright 2020 Yandex LLC. All rights reserved.

// radix must be in the range 2...36
public enum Radix: Int {
  case decimal = 10
  case hex = 16
}

// TODO(dmt021): @_spi(Extensions)
extension FixedWidthInteger {
  @inlinable
  public init?<S>(_ text: S, safeRadix radix: Radix) where S: StringProtocol {
    // swiftlint:disable init_radix
    self.init(text, radix: radix.rawValue)
    // swiftlint:enable init_radix
  }
}

// TODO(dmt021): @_spi(Extensions)
extension String {
  @inlinable
  public init<T>(_ value: T, safeRadix radix: Radix, uppercase: Bool = false)
    where T: BinaryInteger {
    // swiftlint:disable init_radix
    self.init(value, radix: radix.rawValue, uppercase: uppercase)
    // swiftlint:enable init_radix
  }
}

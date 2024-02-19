import Foundation

final class ArrayInsertValueActionHandler {
  func handle(_ action: DivActionArrayInsertValue, context: DivActionHandlingContext) {
    let expressionResolver = context.expressionResolver
    guard let variableName = action.resolveVariableName(expressionResolver),
          let value = action.value.resolve(expressionResolver),
          var array: [AnyHashable] = context.variablesStorage.getVariableValue(
            cardId: context.cardId,
            name: DivVariableName(rawValue: variableName)
          ) else {
      return
    }

    if let index = action.resolveIndex(expressionResolver) {
      guard index >= 0, index <= array.count else {
        DivKitLogger
          .error(
            "Index out of bound \(index) for inserting value in \(variableName) with length \(array.count)"
          )
        return
      }
      array.insert(value, at: index)
    } else {
      array.append(value)
    }

    context.variablesStorage.update(
      cardId: context.cardId,
      name: DivVariableName(rawValue: variableName),
      value: .array(array)
    )
  }
}

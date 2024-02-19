// Generated code. Do not modify.

import CommonCorePublic
import Foundation
import Serialization

public final class DivActionArrayRemoveValueTemplate: TemplateValue {
  public static let type: String = "array_remove_value"
  public let parent: String? // at least 1 char
  public let index: Field<Expression<Int>>?
  public let variableName: Field<Expression<String>>? // at least 1 char

  static let parentValidator: AnyValueValidator<String> =
    makeStringValidator(minLength: 1)

  public convenience init(dictionary: [String: Any], templateToType: [TemplateName: String]) throws {
    do {
      self.init(
        parent: try dictionary.getOptionalField("type", validator: Self.parentValidator),
        index: try dictionary.getOptionalExpressionField("index"),
        variableName: try dictionary.getOptionalExpressionField("variable_name")
      )
    } catch let DeserializationError.invalidFieldRepresentation(field: field, representation: representation) {
      throw DeserializationError.invalidFieldRepresentation(field: "div-action-array-remove-value_template." + field, representation: representation)
    }
  }

  init(
    parent: String?,
    index: Field<Expression<Int>>? = nil,
    variableName: Field<Expression<String>>? = nil
  ) {
    self.parent = parent
    self.index = index
    self.variableName = variableName
  }

  private static func resolveOnlyLinks(context: TemplatesContext, parent: DivActionArrayRemoveValueTemplate?) -> DeserializationResult<DivActionArrayRemoveValue> {
    let indexValue = parent?.index?.resolveValue(context: context) ?? .noValue
    let variableNameValue = parent?.variableName?.resolveValue(context: context, validator: ResolvedValue.variableNameValidator) ?? .noValue
    var errors = mergeErrors(
      indexValue.errorsOrWarnings?.map { .nestedObjectError(field: "index", error: $0) },
      variableNameValue.errorsOrWarnings?.map { .nestedObjectError(field: "variable_name", error: $0) }
    )
    if case .noValue = indexValue {
      errors.append(.requiredFieldIsMissing(field: "index"))
    }
    if case .noValue = variableNameValue {
      errors.append(.requiredFieldIsMissing(field: "variable_name"))
    }
    guard
      let indexNonNil = indexValue.value,
      let variableNameNonNil = variableNameValue.value
    else {
      return .failure(NonEmptyArray(errors)!)
    }
    let result = DivActionArrayRemoveValue(
      index: indexNonNil,
      variableName: variableNameNonNil
    )
    return errors.isEmpty ? .success(result) : .partialSuccess(result, warnings: NonEmptyArray(errors)!)
  }

  public static func resolveValue(context: TemplatesContext, parent: DivActionArrayRemoveValueTemplate?, useOnlyLinks: Bool) -> DeserializationResult<DivActionArrayRemoveValue> {
    if useOnlyLinks {
      return resolveOnlyLinks(context: context, parent: parent)
    }
    var indexValue: DeserializationResult<Expression<Int>> = parent?.index?.value() ?? .noValue
    var variableNameValue: DeserializationResult<Expression<String>> = parent?.variableName?.value() ?? .noValue
    context.templateData.forEach { key, __dictValue in
      switch key {
      case "index":
        indexValue = deserialize(__dictValue).merged(with: indexValue)
      case "variable_name":
        variableNameValue = deserialize(__dictValue, validator: ResolvedValue.variableNameValidator).merged(with: variableNameValue)
      case parent?.index?.link:
        indexValue = indexValue.merged(with: deserialize(__dictValue))
      case parent?.variableName?.link:
        variableNameValue = variableNameValue.merged(with: deserialize(__dictValue, validator: ResolvedValue.variableNameValidator))
      default: break
      }
    }
    var errors = mergeErrors(
      indexValue.errorsOrWarnings?.map { .nestedObjectError(field: "index", error: $0) },
      variableNameValue.errorsOrWarnings?.map { .nestedObjectError(field: "variable_name", error: $0) }
    )
    if case .noValue = indexValue {
      errors.append(.requiredFieldIsMissing(field: "index"))
    }
    if case .noValue = variableNameValue {
      errors.append(.requiredFieldIsMissing(field: "variable_name"))
    }
    guard
      let indexNonNil = indexValue.value,
      let variableNameNonNil = variableNameValue.value
    else {
      return .failure(NonEmptyArray(errors)!)
    }
    let result = DivActionArrayRemoveValue(
      index: indexNonNil,
      variableName: variableNameNonNil
    )
    return errors.isEmpty ? .success(result) : .partialSuccess(result, warnings: NonEmptyArray(errors)!)
  }

  private func mergedWithParent(templates: [TemplateName: Any]) throws -> DivActionArrayRemoveValueTemplate {
    guard let parent = parent, parent != Self.type else { return self }
    guard let parentTemplate = templates[parent] as? DivActionArrayRemoveValueTemplate else {
      throw DeserializationError.unknownType(type: parent)
    }
    let mergedParent = try parentTemplate.mergedWithParent(templates: templates)

    return DivActionArrayRemoveValueTemplate(
      parent: nil,
      index: index ?? mergedParent.index,
      variableName: variableName ?? mergedParent.variableName
    )
  }

  public func resolveParent(templates: [TemplateName: Any]) throws -> DivActionArrayRemoveValueTemplate {
    return try mergedWithParent(templates: templates)
  }
}

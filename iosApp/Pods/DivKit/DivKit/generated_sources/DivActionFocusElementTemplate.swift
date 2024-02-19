// Generated code. Do not modify.

import CommonCorePublic
import Foundation
import Serialization

public final class DivActionFocusElementTemplate: TemplateValue {
  public static let type: String = "focus_element"
  public let parent: String? // at least 1 char
  public let elementId: Field<Expression<String>>? // at least 1 char

  static let parentValidator: AnyValueValidator<String> =
    makeStringValidator(minLength: 1)

  public convenience init(dictionary: [String: Any], templateToType: [TemplateName: String]) throws {
    do {
      self.init(
        parent: try dictionary.getOptionalField("type", validator: Self.parentValidator),
        elementId: try dictionary.getOptionalExpressionField("element_id")
      )
    } catch let DeserializationError.invalidFieldRepresentation(field: field, representation: representation) {
      throw DeserializationError.invalidFieldRepresentation(field: "div-action-focus-element_template." + field, representation: representation)
    }
  }

  init(
    parent: String?,
    elementId: Field<Expression<String>>? = nil
  ) {
    self.parent = parent
    self.elementId = elementId
  }

  private static func resolveOnlyLinks(context: TemplatesContext, parent: DivActionFocusElementTemplate?) -> DeserializationResult<DivActionFocusElement> {
    let elementIdValue = parent?.elementId?.resolveValue(context: context, validator: ResolvedValue.elementIdValidator) ?? .noValue
    var errors = mergeErrors(
      elementIdValue.errorsOrWarnings?.map { .nestedObjectError(field: "element_id", error: $0) }
    )
    if case .noValue = elementIdValue {
      errors.append(.requiredFieldIsMissing(field: "element_id"))
    }
    guard
      let elementIdNonNil = elementIdValue.value
    else {
      return .failure(NonEmptyArray(errors)!)
    }
    let result = DivActionFocusElement(
      elementId: elementIdNonNil
    )
    return errors.isEmpty ? .success(result) : .partialSuccess(result, warnings: NonEmptyArray(errors)!)
  }

  public static func resolveValue(context: TemplatesContext, parent: DivActionFocusElementTemplate?, useOnlyLinks: Bool) -> DeserializationResult<DivActionFocusElement> {
    if useOnlyLinks {
      return resolveOnlyLinks(context: context, parent: parent)
    }
    var elementIdValue: DeserializationResult<Expression<String>> = parent?.elementId?.value() ?? .noValue
    context.templateData.forEach { key, __dictValue in
      switch key {
      case "element_id":
        elementIdValue = deserialize(__dictValue, validator: ResolvedValue.elementIdValidator).merged(with: elementIdValue)
      case parent?.elementId?.link:
        elementIdValue = elementIdValue.merged(with: deserialize(__dictValue, validator: ResolvedValue.elementIdValidator))
      default: break
      }
    }
    var errors = mergeErrors(
      elementIdValue.errorsOrWarnings?.map { .nestedObjectError(field: "element_id", error: $0) }
    )
    if case .noValue = elementIdValue {
      errors.append(.requiredFieldIsMissing(field: "element_id"))
    }
    guard
      let elementIdNonNil = elementIdValue.value
    else {
      return .failure(NonEmptyArray(errors)!)
    }
    let result = DivActionFocusElement(
      elementId: elementIdNonNil
    )
    return errors.isEmpty ? .success(result) : .partialSuccess(result, warnings: NonEmptyArray(errors)!)
  }

  private func mergedWithParent(templates: [TemplateName: Any]) throws -> DivActionFocusElementTemplate {
    guard let parent = parent, parent != Self.type else { return self }
    guard let parentTemplate = templates[parent] as? DivActionFocusElementTemplate else {
      throw DeserializationError.unknownType(type: parent)
    }
    let mergedParent = try parentTemplate.mergedWithParent(templates: templates)

    return DivActionFocusElementTemplate(
      parent: nil,
      elementId: elementId ?? mergedParent.elementId
    )
  }

  public func resolveParent(templates: [TemplateName: Any]) throws -> DivActionFocusElementTemplate {
    return try mergedWithParent(templates: templates)
  }
}

import CoreGraphics

import BaseUIPublic
import CommonCorePublic
import LayoutKit

extension DivGallery: DivBlockModeling, DivGalleryProtocol {
  public func makeBlock(context: DivBlockModelingContext) throws -> Block {
    try applyBaseProperties(
      to: { try makeBaseBlock(context: context) },
      context: context,
      actionsHolder: nil,
      options: .noPaddings
    )
  }

  private func makeBaseBlock(context: DivBlockModelingContext) throws -> Block {
    let galleryPath = context.parentPath + (id ?? DivGallery.type)
    let expressionResolver = context.expressionResolver
    let galleryContext = modified(context) {
      $0.parentPath = galleryPath
    }
    let defaultAlignment = resolveCrossContentAlignment(expressionResolver)
      .blockAlignment
    let itemSpacing = resolveItemSpacing(expressionResolver)

    return try modifyError({ DivBlockModelingError($0.message, path: galleryPath) }) {
      let model = try makeGalleryModel(
        context: galleryContext,
        direction: resolveOrientation(expressionResolver).direction,
        spacing: CGFloat(itemSpacing),
        crossSpacing: CGFloat(resolveCrossSpacing(expressionResolver) ?? itemSpacing),
        defaultAlignment: defaultAlignment,
        scrollMode: resolveScrollMode(expressionResolver).blockScrollMode,
        columnCount: resolveColumnCount(expressionResolver)
      )
      return try GalleryBlock(
        model: model,
        state: getState(context: galleryContext, itemsCount: model.items.count),
        widthTrait: resolveWidthTrait(context),
        heightTrait: resolveHeightTrait(context)
      )
    }
  }

  private func getState(
    context: DivBlockModelingContext,
    itemsCount: Int
  ) -> GalleryViewState {
    let path = context.parentPath
    let index: CGFloat
    if let state: GalleryViewState = context.blockStateStorage.getState(path) {
      switch state.contentPosition {
      case .offset:
        return state
      case let .paging(savedIndex):
        index = savedIndex
      }
    } else {
      index = CGFloat(resolveDefaultItem(context.expressionResolver))
      if index == 0 {
        let newState = GalleryViewState(contentOffset: 0, itemsCount: itemsCount)
        context.blockStateStorage.setState(path: path, state: newState)
        return newState
      }
    }

    let newState = GalleryViewState(
      contentPageIndex: index.clamp(0.0...CGFloat(itemsCount - 1)),
      itemsCount: itemsCount
    )
    context.blockStateStorage.setState(path: path, state: newState)
    return newState
  }
}

extension DivGallery.Orientation {
  fileprivate var direction: GalleryViewModel.Direction {
    switch self {
    case .horizontal:
      return .horizontal
    case .vertical:
      return .vertical
    }
  }
}

extension DivGallery.ScrollMode {
  fileprivate var blockScrollMode: GalleryViewModel.ScrollMode {
    switch self {
    case .default:
      return .default
    case .paging:
      return .autoPaging
    }
  }
}

extension DivGallery.CrossContentAlignment {
  fileprivate var blockAlignment: Alignment {
    switch self {
    case .start: return .leading
    case .center: return .center
    case .end: return .trailing
    }
  }
}

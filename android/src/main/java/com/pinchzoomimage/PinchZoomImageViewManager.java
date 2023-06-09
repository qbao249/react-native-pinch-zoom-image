package com.pinchzoomimage;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author alwx (https://github.com/alwx)
 * @version 1.0
 */
public class PinchZoomImageViewManager extends SimpleViewManager<PhotoView> {
  private static final String REACT_CLASS = "PinchZoomImageView";

  private ResourceDrawableIdHelper mResourceDrawableIdHelper;

  PinchZoomImageViewManager(ReactApplicationContext context) {
    mResourceDrawableIdHelper = new ResourceDrawableIdHelper();
  }

  @Override
  @Nonnull
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  @Nonnull
  protected PhotoView createViewInstance(@Nonnull ThemedReactContext reactContext) {
    return new PhotoView(reactContext);
  }

  @ReactProp(name = "source")
  public void setSource(PhotoView view, @Nullable ReadableMap source) {
    view.setSource(source, mResourceDrawableIdHelper);
  }

  @ReactProp(name = "loadingIndicatorSource")
  public void setLoadingIndicatorSource(PhotoView view, @Nullable String source) {
    view.setLoadingIndicatorSource(source, mResourceDrawableIdHelper);
  }

  @ReactProp(name = "fadeDuration")
  public void setFadeDuration(PhotoView view, int durationMs) {
    view.setFadeDuration(durationMs);
  }

  @ReactProp(name = "shouldNotifyLoadEvents")
  public void setLoadHandlersRegistered(PhotoView view, boolean shouldNotifyLoadEvents) {
    view.setShouldNotifyLoadEvents(shouldNotifyLoadEvents);
  }

  @ReactProp(name = "minimumZoomScale")
  public void setMinimumZoomScale(PhotoView view, float minimumZoomScale) {
    view.setMinimumScale(minimumZoomScale);
  }

  @ReactProp(name = "maximumZoomScale")
  public void setMaximumZoomScale(PhotoView view, float maximumZoomScale) {
    view.setMaximumScale(maximumZoomScale);
  }

  @ReactProp(name = "scale")
  public void setScale(PhotoView view, float scale) {
    view.setScale(scale, true);
  }

  @ReactProp(name = "zoomTransitionDuration")
  public void setZoomTransitionDuration(PhotoView view, int durationMs) {
    view.setZoomTransitionDuration(durationMs);
  }

  @ReactProp(name = "resizeMode")
  public void setScaleType(PhotoView view, @Nullable String scaleType) {
    ScalingUtils.ScaleType value;

    if (scaleType == null) {
      value = ScalingUtils.ScaleType.CENTER_CROP;
    } else {
      switch (scaleType) {
        case "center":
          value = ScalingUtils.ScaleType.CENTER_INSIDE;
          break;
        case "contain":
          value = ScalingUtils.ScaleType.FIT_CENTER;
          break;
        case "cover":
          value = ScalingUtils.ScaleType.CENTER_CROP;
          break;
        case "fitStart":
          value = ScalingUtils.ScaleType.FIT_START;
          break;
        case "fitEnd":
          value = ScalingUtils.ScaleType.FIT_END;
          break;
        case "stretch":
          value = ScalingUtils.ScaleType.FIT_XY;
          break;
        default:
          throw new JSApplicationIllegalArgumentException(
              "Invalid resize mode: '" + scaleType + "'");
      }
    }

    GenericDraweeHierarchy hierarchy = view.getHierarchy();
    hierarchy.setActualImageScaleType(value);
  }

  @Override
  public @Nullable Map<String, Object> getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.of(ImageEvent.eventNameForType(ImageEvent.ON_ERROR),
        MapBuilder.of("registrationName", "onPhotoViewerError"),
        ImageEvent.eventNameForType(ImageEvent.ON_LOAD_START),
        MapBuilder.of("registrationName", "onPhotoViewerLoadStart"),
        ImageEvent.eventNameForType(ImageEvent.ON_LOAD),
        MapBuilder.of("registrationName", "onPhotoViewerLoad"),
        ImageEvent.eventNameForType(ImageEvent.ON_LOAD_END),
        MapBuilder.of("registrationName", "onPhotoViewerLoadEnd"),
        ImageEvent.eventNameForType(ImageEvent.ON_TAP),
        MapBuilder.of("registrationName", "onPhotoViewerTap"),
        ImageEvent.eventNameForType(ImageEvent.ON_VIEW_TAP),
        MapBuilder.of("registrationName", "onPhotoViewerViewTap"),
        ImageEvent.eventNameForType(ImageEvent.ON_SCALE),
        MapBuilder.of("registrationName", "onPhotoViewerScale"));
  }

  @Override
  protected void onAfterUpdateTransaction(@Nonnull PhotoView view) {
    super.onAfterUpdateTransaction(view);
    view.maybeUpdateView(Fresco.newDraweeControllerBuilder());
  }
}

// public class PinchZoomImageViewManager extends SimpleViewManager<View> {
// public static final String REACT_CLASS = "PinchZoomImageView";

// @Override
// @NonNull
// public String getName() {
// return REACT_CLASS;
// }

// @Override
// @NonNull
// public View createViewInstance(ThemedReactContext reactContext) {
// return new View(reactContext);
// }

// @ReactProp(name = "color")
// public void setColor(View view, String color) {
// view.setBackgroundColor(Color.parseColor(color));3
// }
// }

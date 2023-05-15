import React from 'react';
import {
  Platform,
  UIManager,
  ViewProps,
  requireNativeComponent,
} from 'react-native';
// @ts-ignore
import resolveAssetSource from 'react-native/Libraries/Image/resolveAssetSource';
const LINKING_ERROR =
  `The package 'react-native-pinch-zoom-image' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';
type PinchZoomImageProps = ViewProps & {
  source: any;
  loadingIndicatorSource?: any;
  fadeDuration?: number;
  minimumZoomScale?: number;
  maximumZoomScale?: number;
  scale?: number;
  onLoadStart?: (event: any) => void;
  onLoad?: (event: any) => void;
  onLoadEnd?: (event: any) => void;
  onProgress?: (event: any) => void;
  onTap?: (event: any) => void;
  onViewTap?: (event: any) => void;
  onScale?: (event: any) => void;
  showsHorizontalScrollIndicator?: boolean;
  showsVerticalScrollIndicator?: boolean;
  /**
   * @Platform android only
   */
  shouldNotifyLoadEvents?: boolean;
};

const ComponentName = 'PinchZoomImageView';

export const PinchZoomImageView =
  UIManager.getViewManagerConfig(ComponentName) != null
    ? requireNativeComponent<any>(ComponentName)
    : () => {
        throw new Error(LINKING_ERROR);
      };

/**
 * an implementation component of PinchZoomImageView
 * @description
 * an image that can be zoomed in and out
 * base on RNPhotoView and RNFastImage
 */
export const PinchZoomImage: React.FC<PinchZoomImageProps> = ({
  source,
  fadeDuration,
  minimumZoomScale,
  maximumZoomScale,
  scale,
  onLoadStart,
  onLoad,
  onLoadEnd,
  onProgress,
  onTap,
  onViewTap,
  onScale,
  showsHorizontalScrollIndicator,
  showsVerticalScrollIndicator,
  loadingIndicatorSource,
  shouldNotifyLoadEvents,
  ...viewProps
}) => {
  const _source = resolveAssetSource(source);
  const _loadingIndicatorSource = resolveAssetSource(loadingIndicatorSource);
  if (!_source?.uri) console.warn('PinchZoomImage source is missing uri');
  return (
    <PinchZoomImageView
      {...viewProps}
      source={_source}
      onPhotoViewerLoadStart={onLoadStart}
      onPhotoViewerLoad={onLoad}
      onPhotoViewerLoadEnd={onLoadEnd}
      onPhotoViewerProgress={onProgress}
      onPhotoViewerTap={onTap}
      onPhotoViewerViewTap={onViewTap}
      onPhotoViewerScale={onScale}
      showsHorizontalScrollIndicator={showsHorizontalScrollIndicator}
      showsVerticalScrollIndicator={showsVerticalScrollIndicator}
      loadingIndicatorSource={_loadingIndicatorSource?.uri ?? null}
      scale={scale}
      fadeDuration={fadeDuration}
      minimumZoomScale={minimumZoomScale}
      maximumZoomScale={maximumZoomScale}
      shouldNotifyLoadEvents={shouldNotifyLoadEvents}
    />
  );
};

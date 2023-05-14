#import <React/RCTViewManager.h>
#import "RNPhotoView.h"
#import "FFFastImageSource.h"

@interface PinchZoomImageViewManager : RCTViewManager
@end

@implementation PinchZoomImageViewManager

RCT_EXPORT_MODULE(PinchZoomImageView)

- (UIView *)view {
    return [[RNPhotoView alloc] initWithBridge:self.bridge];
}

RCT_EXPORT_VIEW_PROPERTY(source, FFFastImageSource)
RCT_EXPORT_VIEW_PROPERTY(loadingIndicatorSrc, NSString)

RCT_REMAP_VIEW_PROPERTY(maximumZoomScale, maxZoomScale, CGFloat)
RCT_REMAP_VIEW_PROPERTY(minimumZoomScale, minZoomScale, CGFloat)

RCT_EXPORT_VIEW_PROPERTY(showsHorizontalScrollIndicator, BOOL)
RCT_EXPORT_VIEW_PROPERTY(showsVerticalScrollIndicator, BOOL)

RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerScale, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerViewTap, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerTap, RCTBubblingEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerLoadStart, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerLoad, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerLoadEnd, RCTDirectEventBlock)
RCT_EXPORT_VIEW_PROPERTY(onPhotoViewerProgress, RCTDirectEventBlock);

// - (UIView *)view
// {
//   return [[UIView alloc] init];
// }

// RCT_CUSTOM_VIEW_PROPERTY(color, NSString, UIView)
// {
//   [view setBackgroundColor:[self hexStringToColor:json]];
// }

// - hexStringToColor:(NSString *)stringToConvert
// {
//   NSString *noHashString = [stringToConvert stringByReplacingOccurrencesOfString:@"#" withString:@""];
//   NSScanner *stringScanner = [NSScanner scannerWithString:noHashString];

//   unsigned hex;
//   if (![stringScanner scanHexInt:&hex]) return nil;
//   int r = (hex >> 16) & 0xFF;
//   int g = (hex >> 8) & 0xFF;
//   int b = (hex) & 0xFF;

//   return [UIColor colorWithRed:r / 255.0f green:g / 255.0f blue:b / 255.0f alpha:1.0f];
// }

@end

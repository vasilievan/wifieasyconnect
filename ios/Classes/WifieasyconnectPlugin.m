#import "WifieasyconnectPlugin.h"
#if __has_include(<wifieasyconnect/wifieasyconnect-Swift.h>)
#import <wifieasyconnect/wifieasyconnect-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "wifieasyconnect-Swift.h"
#endif

@implementation WifieasyconnectPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftWifieasyconnectPlugin registerWithRegistrar:registrar];
}
@end

#import "MoveActivityToFrontPlugin.h"
#if __has_include(<move_activity_to_front/move_activity_to_front-Swift.h>)
#import <move_activity_to_front/move_activity_to_front-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "move_activity_to_front-Swift.h"
#endif

@implementation MoveActivityToFrontPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftMoveActivityToFrontPlugin registerWithRegistrar:registrar];
}
@end

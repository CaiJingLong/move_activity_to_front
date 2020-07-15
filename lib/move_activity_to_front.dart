import 'dart:async';

import 'package:flutter/services.dart';

class MoveActivityToFront {
  static const MethodChannel _channel =
      const MethodChannel('move_activity_to_front');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static void moveToFront() {
    _channel.invokeMethod('moveToFront');
  }
}

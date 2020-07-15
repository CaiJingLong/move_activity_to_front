import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:move_activity_to_front/move_activity_to_front.dart';

void main() {
  const MethodChannel channel = MethodChannel('move_activity_to_front');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await MoveActivityToFront.platformVersion, '42');
  });
}

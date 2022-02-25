import 'dart:developer';
import 'package:flutter/services.dart';

class Wifieasyconnect {
  static const MethodChannel _channel = MethodChannel('wifieasyconnect');

  void connect () {
    try {
      _channel.invokeMethod('connect');
    } on PlatformException catch (e) {
      log(e.toString());
    }
  }
}



import 'dart:developer';
import 'package:flutter/services.dart';

class Wifieasyconnect {
  static const MethodChannel _channel = MethodChannel('wifieasyconnect');

  void connect (String uriString) {
    try {
      _channel.invokeMethod('connect', {"uriString": uriString});
    } on PlatformException catch (e) {
      log(e.toString());
    }
  }
}



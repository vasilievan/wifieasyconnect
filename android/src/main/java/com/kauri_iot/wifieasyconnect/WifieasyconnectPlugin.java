package com.kauri_iot.wifieasyconnect;

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import android.os.Build;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import androidx.annotation.RequiresApi;

/** WifieasyconnectPlugin */
public class WifieasyconnectPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private Context context;
  private  Activity activity;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "wifieasyconnect");
    channel.setMethodCallHandler(this);
    context = flutterPluginBinding.getApplicationContext();
  }

  @Override
  public void onDetachedFromActivity() {}

  @Override
  public void onAttachedToActivity(ActivityPluginBinding binding) {
    activity = binding.getActivity();
  }

  @Override
  public void onReattachedToActivityForConfigChanges(ActivityPluginBinding binding) { }

  @Override
  public void onDetachedFromActivityForConfigChanges() { }

  @RequiresApi(Build.VERSION_CODES.Q)
  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("connect")) {
      final String uriString = call.argument("uriString");
      connect(context, activity, uriString);
    } else {
      result.notImplemented();
    }
  }


  private void connect(Context context, Activity activity, String uriString) {
    final String INTENT_ACTION_WIFI_QR_SCANNER = "android.settings.WIFI_DPP_ENROLLEE_QR_CODE_SCANNER";
    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    if(wifiManager.isEasyConnectSupported())
    {
      final Intent intent = new Intent("android.settings.PROCESS_WIFI_EASY_CONNECT_URI");
      intent.setData(Uri.parse(uriString));
      activity.startActivityForResult(intent, 3141);
    }
  }


  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}

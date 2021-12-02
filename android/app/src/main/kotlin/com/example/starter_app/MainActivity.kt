package com.example.starter_app

import android.os.Build
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity : FlutterActivity() {
  private val CHANNEL = "platformchannel.companyname.com/deviceinfo"

  override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
    super.configureFlutterEngine(flutterEngine)
    MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
        call,
        result ->
      if (call.method == "getDeviceInfo") {
        val deviceInfo = getDeviceInfo()
        result.success(deviceInfo)
      } else {
        result.notImplemented()
      }
    }
  }

  private fun getDeviceInfo(): String {
    return ("\nDevice: " +
        Build.DEVICE +
        "\nManufacturer: " +
        Build.MANUFACTURER +
        "\nModel: " +
        Build.MODEL +
        "\nProduct: " +
        Build.PRODUCT +
        "\nVersion Release: " +
        Build.VERSION.RELEASE +
        "\nVersion SDK: " +
        Build.VERSION.SDK_INT +
        "\nFingerprint: " +
        Build.FINGERPRINT)
  }
}

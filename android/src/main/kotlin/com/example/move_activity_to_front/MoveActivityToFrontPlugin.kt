package com.example.move_activity_to_front

import android.app.ActivityManager
import android.app.ActivityManager.MOVE_TASK_NO_USER_ACTION
import android.content.Context
import android.os.Build
import androidx.annotation.ContentView
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat.getSystemService

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** MoveActivityToFrontPlugin */
public class MoveActivityToFrontPlugin : FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel: MethodChannel

  private lateinit var context: Context

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    context = flutterPluginBinding.applicationContext
    channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "move_activity_to_front")
    channel.setMethodCallHandler(this);
  }

  // This static function is optional and equivalent to onAttachedToEngine. It supports the old
  // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
  // plugin registration via this function while apps migrate to use the new Android APIs
  // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
  // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
  // depending on the user's project. onAttachedToEngine or registerWith must both be defined
  // in the same class.
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "move_activity_to_front")
      val moveActivityToFrontPlugin = MoveActivityToFrontPlugin()
      moveActivityToFrontPlugin.context = registrar.context().applicationContext
      channel.setMethodCallHandler(moveActivityToFrontPlugin)
    }
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else if (call.method == "moveToFront") {
      moveToFront()
    } else {
      result.notImplemented()
    }
  }

  private fun moveToFront() {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

    val runningTasks = activityManager.getRunningTasks(100)

    for (appTask in runningTasks) {
      if (appTask.baseActivity.packageName == context.packageName) {
        activityManager.moveTaskToFront(appTask.id, MOVE_TASK_NO_USER_ACTION)
      }
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}

package com.example.passwordvaultapp_mvvm_compose.common.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.core.content.ContextCompat

object FilePicker {

    fun filePicker(
        launcher: ManagedActivityResultLauncher<String, List<@JvmSuppressWildcards Uri>>,
        permissionLauncher: ManagedActivityResultLauncher<String, Boolean>,
        context: Context,
        fileType:String
    ){
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) -> {
                // Some works that require permission
                Log.d("ExampleScreen", "Code requires permission")
                launcher.launch(fileType)
            }
            else -> {
                // Asking for permission
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }
}
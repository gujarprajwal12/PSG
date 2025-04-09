package com.psg.Presentation.Util

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionHelper {
    fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(activity, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionsToRequest, requestCode)
        }
    }

    fun handlePermissionsResult(permissions: Array<String>, grantResults: IntArray, onResult: (Boolean) -> Unit) {
        val allGranted = grantResults.all { it == PackageManager.PERMISSION_GRANTED }
        onResult(allGranted)
    }
}


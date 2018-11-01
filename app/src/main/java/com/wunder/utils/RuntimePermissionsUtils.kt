package com.wunder.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

class RuntimePermissionUtils {

    companion object {
        fun checkPermission(context: Context, permission: String): Boolean {
            val result = ContextCompat.checkSelfPermission(context, permission)
            return result == PackageManager.PERMISSION_GRANTED
        }

        private fun requestPermission(mActivity: Activity, permission: String, requestCode: Int) {
            ActivityCompat.requestPermissions(mActivity, arrayOf(permission), requestCode)
        }

        fun checkPermisionForActivity(mActivity: Activity, requestCode: Int, permission: String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkPermission(mActivity.applicationContext, permission)) {
                requestPermission(mActivity, permission, requestCode)
            }
        }
    }
}

package com.wunder.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class RuntimePermissionUtils {

    public static boolean checkPermission(Context context, String permission) {
        int result = ContextCompat.checkSelfPermission(context, permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private static void requestPermission(Activity mActivity, String permission, int requestCode) {
        ActivityCompat.requestPermissions(mActivity, new String[]{permission}, requestCode);
    }

    public static void checkPermisionForActivity(Activity mActivity, int requestCode, String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !checkPermission(mActivity.getApplicationContext(), permission)) {
            requestPermission(mActivity, permission, requestCode);
        }
    }
}

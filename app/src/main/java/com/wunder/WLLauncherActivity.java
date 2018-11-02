package com.wunder;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.wunder.controller.WLLauncherController;
import com.wunder.utils.RuntimePermissionUtils;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class WLLauncherActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 200;
    private WLLauncherController mWlLauncherController = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWlLauncherController = new WLLauncherController();
        if (RuntimePermissionUtils.Companion.checkPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE))
            mWlLauncherController.init(this);
        else
            RuntimePermissionUtils.Companion.checkPermisionForActivity(this, PERMISSION_REQUEST_CODE, WRITE_EXTERNAL_STORAGE);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWlLauncherController.onDestroy();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            boolean writeAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if (writeAccepted) {
                mWlLauncherController.init(this);
            } else {
                finish();
            }
        }
    }
}

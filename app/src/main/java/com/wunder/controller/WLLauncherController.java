package com.wunder.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wunder.R;
import com.wunder.WLListActivity;
import com.wunder.utils.DownloadTask;

import java.io.File;


public class WLLauncherController extends WLController {

    private final static String url = "https://s3-us-west-2.amazonaws.com/wunderbucket/locations.json";
    private final static String FILENAME = "locations.json";
    private Activity mActivity = null;
    private Context mContext = null;
    private DownloadTask mDownloadTask = null;
    private String filePath;
    private ProgressBar mProgressBar = null;
    private TextView mErrorText = null;


    public WLLauncherController(Activity activity) {
        mActivity = activity;
        mContext = activity.getApplicationContext();
    }

    public void init() {
        mActivity.setContentView(R.layout.activity_launcher);
        mProgressBar = (ProgressBar) mActivity.findViewById(R.id.progress);
        mErrorText = (TextView) mActivity.findViewById(R.id.error_text);
        filePath = mActivity.getFilesDir().getAbsolutePath() + File.separator + FILENAME;
        mDownloadTask = new DownloadTask(this, filePath);
        mDownloadTask.execute(url);
    }

    /**
     * TODO:
     * If internet is not switched on then, check for networkinfo available if user
     * swtiches on.
     * Intent-filter can be registered in @onStart() of activity and deregister in
     * onDestroy() or onStop() and handle network connectivity status using
     * intent-filter of ConnectivityManager.
     * <p>
     * This gives essence of refresh to user when network is available.
     */
    public boolean updateViewsAfterTask(String result) {
        if (result != null) {
            Toast.makeText(mContext, "Download error: " + result, Toast.LENGTH_LONG).show();
            mErrorText.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
            mErrorText.setText(mContext.getResources().getString(R.string.error_text));
            return false;
        } else {
            launchListActivity();
            return true;
        }
    }

    public void updateViewonProgress(Integer... progress) {
        mProgressBar.setIndeterminate(false);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(progress[0]);
    }

    private void launchListActivity() {
        Intent launchIntent = new Intent(mActivity, WLListActivity.class);
        launchIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        launchIntent.putExtra("filepath", filePath);
        mContext.startActivity(launchIntent);
        mActivity.finish();
    }

    public void onDestroy(){
        mActivity = null;
        mContext = null;
    }

}

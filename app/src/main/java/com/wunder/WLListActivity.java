package com.wunder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.wunder.controller.WLListController;

public class WLListActivity extends AppCompatActivity {

    private WLListController mWLListController = null;
    private String jsonFilepath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jsonFilepath = getIntent().getExtras().getString("filepath");
        mWLListController = new WLListController(this);
        mWLListController.init(jsonFilepath);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mWLListController.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWLListController.onDestroy();
    }
}

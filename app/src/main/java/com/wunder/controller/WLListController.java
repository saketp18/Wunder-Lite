package com.wunder.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wunder.R;
import com.wunder.WLMapsActivity;
import com.wunder.utils.JSONReader;
import com.wunder.utils.WLCarModel;
import com.wunder.view.WLRecyclerViewAdapter;

import java.util.ArrayList;

public class WLListController extends WLController {

    private Activity mActivity;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFaButton;
    private WLRecyclerViewAdapter mAdapter;
    private String mFilePath;
    private ArrayList<WLCarModel> carModelArrayList = new ArrayList<>();
    private View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            launchMapsActivity(-1);
        }
    };

    public WLListController(Activity activity) {
        mActivity = activity;
    }

    public void init(String jsonFilepath) {
        mContext = mActivity.getApplicationContext();
        mActivity.setContentView(R.layout.activity_list);
        mFilePath = jsonFilepath;
        carModelArrayList = JSONReader.readJson(mFilePath);
        mRecyclerView = (RecyclerView) mActivity.findViewById(R.id.recycler_view);
        mFaButton = (FloatingActionButton) mActivity.findViewById(R.id.fab_maps);
        mAdapter = new WLRecyclerViewAdapter(mActivity, this, carModelArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mActivity.getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity.getApplicationContext(), DividerItemDecoration.VERTICAL));
        mFaButton.setOnClickListener(mClickListener);
    }

    public void onBackPressed() {
        mActivity.finish();
    }

    public void launchMapsActivity(int position) {
        Intent launchIntent = new Intent(mActivity, WLMapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("jsonData", carModelArrayList);
        bundle.putInt("position", position);
        launchIntent.putExtras(bundle);
        mContext.startActivity(launchIntent);
    }

    public void onDestroy(){
        carModelArrayList.clear();
        mRecyclerView = null;
        mContext = null;
        mActivity = null;
    }
}

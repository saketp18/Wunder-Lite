package com.wunder.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wunder.R;
import com.wunder.utils.WLCarModel;
import com.wunder.view.WLCarInfoViewAdapter;

import java.util.ArrayList;

public class WLMapController extends WLController{

    private Activity mActivity;
    private Context mContext;
    private ArrayList<WLCarModel> mCarModelList = new ArrayList<>();
    private int mPosition;
    private GoogleMap mMap;


    public WLMapController(Activity activity){
        mActivity = activity;
        mContext = activity.getApplicationContext();
    }


    public void setUpData(ArrayList<WLCarModel> carModels, int position){
        mCarModelList = carModels;
        mPosition = position;
    }
    public void setUpMap(GoogleMap map, int size){
        mMap = map;
        mMap.clear();
        mMap.setMinZoomPreference(5.0f);
        mMap.setMaxZoomPreference(18.0f);
        int height = 80;
        int width = 40;
        BitmapDrawable bitmapdraw=(BitmapDrawable)mContext.getResources().getDrawable(R.drawable.ic_booking_share_map_topview);
        Bitmap b=bitmapdraw.getBitmap();
        final Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        int position = 0;
        LatLng latLng;
        while (position < size) {
            if(mCarModelList.get(position).getCoordinates()!= null) {
                latLng = new LatLng(mCarModelList.get(position).getCoordinates()[1], mCarModelList.get(position).getCoordinates()[0]);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(mCarModelList.get(position).getAddress());
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
                markerOptions.getPosition();
                Marker marker = mMap.addMarker(markerOptions);
                WLCarInfoViewAdapter customInfoWindow = new WLCarInfoViewAdapter(mActivity);
                mMap.setInfoWindowAdapter(customInfoWindow);
                marker.setTag(mCarModelList.get(position));
                if(mPosition == position) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                    marker.showInfoWindow();
                    mPosition = -2;
                }
                else if (mPosition == -1) {
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
                    mPosition = -2;
                }
            }
            position++;
        }
    }


    /**
     * Following snippet will get current location and once we have defined regions in map where cars
     * are available then we can direct user that these cars are available  for riding.
     */
    /*public void addMarkerCurrentLocation(Location location){
        int height = 108;
        int width = 60;
        BitmapDrawable bitmapdraw=(BitmapDrawable)mContext.getResources().getDrawable(R.drawable.pickup_location);
        Bitmap b=bitmapdraw.getBitmap();
        final Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Location");
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        markerOptions.getPosition();
        mMap.addMarker(markerOptions);
    }*/
}

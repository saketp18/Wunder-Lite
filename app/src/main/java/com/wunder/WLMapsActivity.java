package com.wunder;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.wunder.controller.WLMapController;
import com.wunder.utils.RuntimePermissionUtils;
import com.wunder.utils.WLCarModel;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

import java.util.ArrayList;

public class WLMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private WLMapController mWLMapController;
    private ArrayList<WLCarModel> carModelArrayList = new ArrayList<>();
    private int mPosition = 0;
    private int mSize = 0;
    private float previousZoomLevel = 0;
    private final static int PERMISSION_REQUEST_CODE = 201;

    private GoogleMap.OnCameraChangeListener mCameraChangeListener = new GoogleMap.OnCameraChangeListener() {
        @Override
        public void onCameraChange(CameraPosition cameraPosition) {
            if (cameraPosition.zoom != previousZoomLevel) {
                mSize = (int) ((carModelArrayList.size() * cameraPosition.zoom) / mMap.getMaxZoomLevel());
                mWLMapController.setUpMap(mMap, mSize);
                previousZoomLevel = cameraPosition.zoom;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0) {
            boolean writeAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            if(writeAccepted){
                init();
            }else
                finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWLMapController = new WLMapController(this);
        if (RuntimePermissionUtils.checkPermission(getApplicationContext(), ACCESS_FINE_LOCATION) && RuntimePermissionUtils.checkPermission(getApplicationContext(),ACCESS_COARSE_LOCATION)) {
            init();
        }
        else {
            RuntimePermissionUtils.checkPermisionForActivity(this, PERMISSION_REQUEST_CODE, ACCESS_FINE_LOCATION);
            RuntimePermissionUtils.checkPermisionForActivity(this, PERMISSION_REQUEST_CODE, ACCESS_COARSE_LOCATION);
        }
    }

    private void init(){
        setContentView(R.layout.activity_maps);
        carModelArrayList = getIntent().getExtras().getParcelableArrayList("jsonData");
        mPosition = getIntent().getExtras().getInt("position");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mWLMapController.setUpData(carModelArrayList, mPosition);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mWLMapController.setUpMap(mMap, carModelArrayList.size());
        previousZoomLevel = mPosition == -1 ? 14 : 17;
        mMap.setOnCameraChangeListener(mCameraChangeListener);
    }


    /**
     * TODO
     *  This snippet gets current location and then add marker and relatively check whether the car
     *  is close by or not with location apis distanceTo and dynamically load cars based on distance.
     *
     *  An approach to show cars is to create boundaries in map and define regions and segements if
     *  user falls under that radius then we show the cars lying in that region which will be easier
     *  and operation cost will be less.
     */
    /*void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mWLMapController.addMarkerCurrentLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }*/
}

package com.wunder.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.wunder.R;
import com.wunder.utils.WLCarModel;

public class WLCarInfoViewAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public WLCarInfoViewAdapter(Context ctx) {
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.car_item, null);

        TextView name = view.findViewById(R.id.name);
        TextView address = view.findViewById(R.id.address);
        TextView exterior = view.findViewById(R.id.exterior);
        TextView interior = view.findViewById(R.id.interior);
        TextView engineType = view.findViewById(R.id.engineType);
        TextView fuel = view.findViewById(R.id.fuel);
        TextView vin = view.findViewById(R.id.vin);

        WLCarModel carModel = (WLCarModel) marker.getTag();
        name.setText(carModel.getName());
        address.setText(carModel.getAddress());
        exterior.setText(carModel.getExterior());
        interior.setText(carModel.getInterior());
        engineType.setText(carModel.getEngine());
        fuel.setText(carModel.getFuel());
        vin.setText(carModel.getVin());

        return view;
    }
}

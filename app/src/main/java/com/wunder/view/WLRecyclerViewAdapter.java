package com.wunder.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wunder.R;
import com.wunder.controller.WLListController;
import com.wunder.utils.WLCarModel;

import java.util.List;

public class WLRecyclerViewAdapter extends RecyclerView.Adapter<WLRecyclerViewAdapter.CarViewHolder> {

    private List<WLCarModel> carModelList;
    private Context mContext;
    private WLListController mWlListController;

    public WLRecyclerViewAdapter(Context context, WLListController wlListController, List<WLCarModel> carModels) {
        this.carModelList = carModels;
        mContext = context;
        mWlListController = wlListController;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_item, parent, false);

        return new CarViewHolder(itemView);
    }

    /**
     * TODO
     * <p>
     * Baised on current location and coordinates available for car developer can define the distance,
     * and time taken to reach rider. This will be relevant if rider is under geo-fence of car.
     * <p>
     * We can add a view which will denote time and km from rider.
     * <p>
     * <p>
     * Also Filter option can be provided if rider wants something different type of comfort.
     */
    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        WLCarModel wlCarModel = carModelList.get(position);
        holder.address.setText(wlCarModel.getAddress());
        holder.engineType.setText(wlCarModel.getEngine());
        holder.exterior.setText(wlCarModel.getExterior());
        holder.interior.setText(wlCarModel.getInterior());
        holder.name.setText(wlCarModel.getName());
        holder.fuel.setText(wlCarModel.getFuel());
        holder.vin.setText(wlCarModel.getVin());


        holder.address_text.setText(mContext.getResources().getString(R.string.address));
        holder.engineType_text.setText(mContext.getResources().getString(R.string.engineType));
        holder.exterior_text.setText(mContext.getResources().getString(R.string.exterior));
        holder.interior_text.setText(mContext.getResources().getString(R.string.interior));
        holder.name_text.setText(mContext.getResources().getString(R.string.name));
        holder.fuel_text.setText(mContext.getResources().getString(R.string.fuel));
        holder.vin_text.setText(mContext.getResources().getString(R.string.vin));
    }

    @Override
    public int getItemCount() {
        return carModelList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        public TextView address, engineType, exterior, interior, name, vin, fuel;
        public TextView address_text, engineType_text, exterior_text, interior_text, name_text, vin_text, fuel_text;

        public CarViewHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mWlListController.launchMapsActivity(getAdapterPosition());
                }
            });
            address = (TextView) view.findViewById(R.id.address);
            engineType = (TextView) view.findViewById(R.id.engineType);
            exterior = (TextView) view.findViewById(R.id.exterior);
            interior = (TextView) view.findViewById(R.id.interior);
            name = (TextView) view.findViewById(R.id.name);
            fuel = (TextView) view.findViewById(R.id.fuel);
            vin = (TextView) view.findViewById(R.id.vin);

            address_text = (TextView) view.findViewById(R.id.address_text);
            engineType_text = (TextView) view.findViewById(R.id.engineType_text);
            exterior_text = (TextView) view.findViewById(R.id.exterior_text);
            interior_text = (TextView) view.findViewById(R.id.interior_text);
            name_text = (TextView) view.findViewById(R.id.name_text);
            fuel_text = (TextView) view.findViewById(R.id.fuel_text);
            vin_text = (TextView) view.findViewById(R.id.vin_text);
        }
    }
}

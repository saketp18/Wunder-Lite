package com.wunder.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class WLCarModel implements Parcelable {


    public static final Creator<WLCarModel> CREATOR = new Creator<WLCarModel>() {
        @Override
        public WLCarModel createFromParcel(Parcel source) {
            return new WLCarModel(source);
        }

        @Override
        public WLCarModel[] newArray(int size) {
            return new WLCarModel[size];
        }
    };
    private String address;
    private String engine;
    private String exterior;
    private String interior;
    private String name;
    private String vin;
    private int fuel;
    private double[] coordinates;

    public WLCarModel(String address, String engine, String exterior, String interior, String name, String vin, int fuel, double[] coordinates) {

        this.address = address;
        this.engine = engine;
        this.exterior = exterior;
        this.interior = interior;
        this.name = name;
        this.vin = vin;
        this.fuel = fuel;
        this.coordinates = coordinates;
    }

    protected WLCarModel(Parcel in) {
        this.address = in.readString();
        this.engine = in.readString();
        this.exterior = in.readString();
        this.interior = in.readString();
        this.name = in.readString();
        this.vin = in.readString();
        this.fuel = in.readInt();
        this.coordinates = in.createDoubleArray();
    }

    @Override
    public String toString() {
        return "WLCars{" +
                "address='" + address + '\'' +
                ", engine='" + engine + '\'' +
                ", exterior='" + exterior + '\'' +
                ", interior='" + interior + '\'' +
                ", name='" + name + '\'' +
                ", vin='" + vin + '\'' +
                ", fuel=" + fuel +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getFuel() {
        return Integer.toString(fuel);
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.address);
        dest.writeString(this.engine);
        dest.writeString(this.exterior);
        dest.writeString(this.interior);
        dest.writeString(this.name);
        dest.writeString(this.vin);
        dest.writeInt(this.fuel);
        dest.writeDoubleArray(this.coordinates);
    }
}


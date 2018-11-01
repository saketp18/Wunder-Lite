package com.wunder.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONReader {

    private static ArrayList<Double> latitudeslist = new ArrayList<>();
    private static ArrayList<Double> longitudelist = new ArrayList<>();

    private static String readFile(String filePath) {
        if(filePath == null)
            return null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            return null;
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<WLCarModel> readJson(String filepath) {
        if (filepath == null)
            return null;
        latitudeslist.clear();
        longitudelist.clear();
        String jsonData = readFile(filepath);
        WLCarModel wlCarModel;
        int index = 0;
        try {
            JSONObject jsonMainObject = new JSONObject(jsonData);
            JSONArray placemarks = jsonMainObject.getJSONArray("placemarks");
            ArrayList<WLCarModel> jsonArrayList = new ArrayList<>();
            while (index < placemarks.length()) {
                JSONObject jsonObject = placemarks.getJSONObject(index);
                String address = jsonObject.getString("address");
                String engineType = jsonObject.getString("engineType");
                String exterior = jsonObject.getString("exterior");
                String interior = jsonObject.getString("interior");
                String name = jsonObject.getString("name");
                String vin = jsonObject.getString("vin");
                int fuel = jsonObject.getInt("fuel");
                double longitutde = jsonObject.getJSONArray("coordinates").getDouble(0);
                double latitude = jsonObject.getJSONArray("coordinates").getDouble(1);
                double zorder = jsonObject.getJSONArray("coordinates").getDouble(2);
                double[] coordinates = {longitutde, latitude, zorder};
                wlCarModel = new WLCarModel(address, engineType, exterior, interior, name, vin, fuel, coordinates);
                longitudelist.add(longitutde);
                latitudeslist.add(latitude);
                jsonArrayList.add(wlCarModel);
                index++;
            }
            return jsonArrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList getLatitudeArray() {
        return latitudeslist;
    }

    public static ArrayList getLongitudeArray() {
        return longitudelist;
    }
}

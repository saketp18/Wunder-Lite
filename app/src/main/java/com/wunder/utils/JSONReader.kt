package com.wunder.utils

import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.ArrayList


class JSONReader {
    companion object {

        private fun readFile(filePath: String?): String? {
            if (filePath == null)
                return null
            var br: BufferedReader? = null
            try {
                br = BufferedReader(FileReader(filePath))
                val sb = StringBuilder()
                var line: String? = br.readLine()

                while (line != null) {
                    sb.append(line)
                    sb.append("\n")
                    line = br.readLine()
                }
                return sb.toString()
            } catch (e: IOException) {
                return null
            } finally {
                try {
                    br!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }

        fun readJson(filepath: String?): ArrayList<WLCarModel>? {
            if (filepath == null)
                return null
            val jsonData = readFile(filepath)
            var wlCarModel: WLCarModel
            var index = 0
            try {
                val jsonMainObject = JSONObject(jsonData)
                val placemarks = jsonMainObject.getJSONArray("placemarks")
                val jsonArrayList = ArrayList<WLCarModel>()
                while (index < placemarks.length()) {
                    val jsonObject = placemarks.getJSONObject(index)
                    val address = jsonObject.getString("address")
                    val engineType = jsonObject.getString("engineType")
                    val exterior = jsonObject.getString("exterior")
                    val interior = jsonObject.getString("interior")
                    val name = jsonObject.getString("name")
                    val vin = jsonObject.getString("vin")
                    val fuel = jsonObject.getInt("fuel")
                    val longitutde = jsonObject.getJSONArray("coordinates").getDouble(0)
                    val latitude = jsonObject.getJSONArray("coordinates").getDouble(1)
                    val zorder = jsonObject.getJSONArray("coordinates").getDouble(2)
                    val coordinates = doubleArrayOf(longitutde, latitude, zorder)
                    wlCarModel = WLCarModel(address, engineType, exterior, interior, name, vin, fuel, coordinates)
                    jsonArrayList.add(wlCarModel)
                    index++
                }
                return jsonArrayList
            } catch (e: JSONException) {
                e.printStackTrace()
                return null
            }

        }
    }
}

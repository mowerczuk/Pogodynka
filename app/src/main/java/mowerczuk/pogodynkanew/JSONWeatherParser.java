package mowerczuk.pogodynkanew;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by macie on 26.01.2017.
 */

public class JSONWeatherParser {
    public static WeatherModel getWeather(String data) throws JSONException {
        WeatherModel weather = new WeatherModel();

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);

        // We start extracting the info
        LocationModel loc = new LocationModel();

        JSONObject coordObj = getObject("coord", jObj);
        loc.setLatitude(getFloat("lat", coordObj));
        loc.setLongitude(getFloat("lon", coordObj));

        JSONObject sysObj = getObject("sys", jObj);
        loc.setCountry(getString("country", sysObj));
        loc.setSunrise(getInt("sunrise", sysObj));
        loc.setSunset(getInt("sunset", sysObj));
        loc.setCity(getString("name", jObj));
        weather.location = loc;

        // We get weather info (This is an array)
        JSONArray jArr = jObj.getJSONArray("weather");

        // We use only the first value
        JSONObject JSONWeather = jArr.getJSONObject(0);
        weather.currentCondition.setWeatherId(getInt("id", JSONWeather));

        // description
        try{
            weather.currentCondition.setDescr(getString("description", JSONWeather));
        }
        catch(Exception ex){
            weather.currentCondition.setDescr("N/A");
        }
        //conditions
        try{
            weather.currentCondition.setCondition(getString("main", JSONWeather));
        }
        catch(Exception ex){
            weather.currentCondition.setCondition("N/A");
        }

        //icon - obsłużyć
        try{
            weather.currentCondition.setIcon(getString("icon", JSONWeather));
        }
        catch(Exception ex){
            weather.currentCondition.setIcon("N/A");
        }

        JSONObject mainObject = getObject("main", jObj);
        //humadity
        try{
            weather.currentCondition.setHumidity(Integer.toString(getInt("humidity", mainObject)));
        }
        catch(Exception ex){
            weather.currentCondition.setHumidity("N/A");
        }
        //pressure
        try{
            weather.currentCondition.setPressure(Integer.toString(getInt("pressure", mainObject)));
        }
        catch(Exception ex){
            weather.currentCondition.setPressure("N/A");
        }
        //maxTemp
        try{
            weather.temperature.setMaxTemp(Float.toString(getFloat("temp_max", mainObject)));
        }
        catch(Exception ex){
            weather.temperature.setMaxTemp("N/A");
        }
        //minTemp
        try{
            weather.temperature.setMinTemp(Float.toString(getFloat("temp_min", mainObject)));
        }
        catch(Exception ex){
            weather.temperature.setMinTemp("N/A");
        }
        //temp
        try{
            weather.temperature.setTemp(Float.toString(Math.round(getFloat("temp", mainObject) - 273.15)));
        }
        catch(Exception ex){
            weather.temperature.setTemp("N/A");
        }

        // Wind
        JSONObject windObject = getObject("wind", jObj);
        try{
            weather.wind.setSpeed(Float.toString(getFloat("speed", windObject)));
        }
        catch(Exception ex){
            weather.wind.setSpeed("N/A");
        }
        try{
            weather.wind.setDeg(Float.toString(getFloat("deg", windObject)));
        }
        catch(Exception ex){
            weather.wind.setDeg("N/A");
        }

        // Clouds
        JSONObject cloudsObject = getObject("clouds", jObj);
        try{
            weather.clouds.setPerc(Integer.toString(getInt("all", cloudsObject)));
        }
        catch(Exception ex){
            weather.clouds.setPerc("N/A");
        }

        // We download the icon to show


        return weather;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
}

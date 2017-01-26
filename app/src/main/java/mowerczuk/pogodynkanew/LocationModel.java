package mowerczuk.pogodynkanew;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by macie on 26.01.2017.
 */

public class LocationModel implements Serializable {
    private float _longitude;
    private float _latitude;
    private long _sunset;
    private long _sunrise;
    private String _country;
    private String _city;

    public float getLongitude() {
        return _longitude;
    }
    public void setLongitude(float longitude) {
        this._longitude = longitude;
    }
    public float getLatitude() {
        return _latitude;
    }
    public void setLatitude(float latitude) {
        this._latitude = latitude;
    }
    public long getSunset() {
        return _sunset;
    }
    public void setSunset(long sunset) {
        this._sunset = sunset;
    }
    public long getSunrise() {
        return _sunrise;
    }
    public void setSunrise(long sunrise) {
        this._sunrise = sunrise;
    }
    public String getCountry() {
        Locale loc = new Locale("",_country);
        return loc.getDisplayCountry();
    }
    public void setCountry(String country) {
        this._country = country;
    }
    public String getCity() {
        return _city;
    }
    public void setCity(String city) {
        this._city = city;
    }
}

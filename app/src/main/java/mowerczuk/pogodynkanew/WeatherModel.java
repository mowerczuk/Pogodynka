package mowerczuk.pogodynkanew;

/**
 * Created by macie on 26.01.2017.
 */

public class WeatherModel {
    public LocationModel location;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Rain rain = new Rain();
    public Snow snow = new Snow()	;
    public Clouds clouds = new Clouds();

    public byte[] iconData;

    public  class CurrentCondition {
        private int _weatherId;
        private String _condition;
        private String _descr;
        private String _icon;
        private String _pressure;
        private String _humidity;

        public int getWeatherId() {
            return _weatherId;
        }
        public void setWeatherId(int weatherId) {
            this._weatherId = weatherId;
        }
        public String getCondition() {
            return _condition;
        }
        public void setCondition(String condition) {
            this._condition = condition;
        }
        public String getDescr() {
            return _descr;
        }
        public void setDescr(String descr) {
            this._descr = descr;
        }
        public String getIcon() {
            return _icon;
        }
        public void setIcon(String icon) {
            this._icon = icon;
        }
        public String getPressure() {
            return _pressure;
        }
        public void setPressure(String pressure) {
            this._pressure = pressure;
        }
        public String getHumidity() {
            return _humidity;
        }
        public void setHumidity(String humidity) {
            this._humidity = humidity;
        }
    }

    public  class Temperature {
        private String _temp;
        private String _minTemp;
        private String _maxTemp;

        public String getTemp() {
            return _temp;
        }
        public void setTemp(String temp) {
            this._temp = temp;
        }
        public String getMinTemp() {
            return _minTemp;
        }
        public void setMinTemp(String minTemp) {
            this._minTemp = minTemp;
        }
        public String getMaxTemp() {
            return _maxTemp;
        }
        public void setMaxTemp(String maxTemp) {
            this._maxTemp = maxTemp;
        }
    }

    public  class Wind {
        private String _speed;
        private String _deg;

        public String getSpeed() {
            return _speed;
        }
        public void setSpeed(String speed) {
            this._speed = speed;
        }
        public String getDeg() {
            return _deg;
        }
        public void setDeg(String deg) {
            this._deg = deg;
        }
    }

    public  class Rain {
        private String _time;
        private String _ammount;

        public String getTime() {
            return _time;
        }
        public void setTime(String time) {
            this._time = time;
        }
        public String getAmmount() {
            return _ammount;
        }
        public void setAmmount(String ammount) {
            this._ammount = ammount;
        }
    }

    public  class Snow {
        private String _time;
        private String _ammount;

        public String getTime() {
            return _time;
        }
        public void setTime(String time) {
            this._time = time;
        }
        public String getAmmount() {
            return _ammount;
        }
        public void setAmmount(String ammount) {
            this._ammount = ammount;
        }
    }

    public  class Clouds {
        private String _perc;

        public String getPerc() {
            return _perc;
        }
        public void setPerc(String perc) {
            this._perc = perc;
        }
    }
}

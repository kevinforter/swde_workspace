package ch.hslu.swde.wda.reader;

import java.util.Map;
import java.util.Objects;

public class weatherDataProvider {

    private int zip;
    private int year;
    private String name;
    private Map<String, Object> city;
    private String data;
    private String lastUpdateTime;


    public weatherDataProvider() {

    }

    public Map<String, Object> getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getZip() {
        return zip;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(Map<String, Object> city) {
        this.city = city;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof weatherDataProvider)) {
            return false;
        }
        weatherDataProvider other = (weatherDataProvider) obj;
        return Objects.equals(name, other.name) && year == other.year;
    }

    @Override
    public String toString() {
        return "Weather [name=" + name + ", year=" + year +"]";
    }
}

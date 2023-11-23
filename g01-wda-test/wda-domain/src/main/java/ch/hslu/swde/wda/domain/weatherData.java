package ch.hslu.swde.wda.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Map;
import java.util.Objects;

@Entity
public class weatherData {

    @Id
    @GeneratedValue
    private int id;
    private int zip;
    private int year;
    private String name;
    private int city;
    private String data;
    private String lastUpdateTime;


    public weatherData() {

    }

    public int getCity() {
        return city;
    }

    public void setCity() {this.city = city;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(obj instanceof weatherData)) {
            return false;
        }
        weatherData other = (weatherData) obj;
        return Objects.equals(name, other.name) && year == other.year;
    }

    @Override
    public String toString() {
        return "Weather [name=" + name + ", year=" + year +"]";
    }
}

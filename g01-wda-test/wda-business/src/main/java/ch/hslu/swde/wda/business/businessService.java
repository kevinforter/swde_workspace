package ch.hslu.swde.wda.business;

import ch.hslu.swde.wda.domain.weatherData;

import java.util.List;

public interface businessService {

    weatherData weatherCityYear(String name, int year);
    weatherData findByName(String name);
    List<weatherData> allWeatherData();
}

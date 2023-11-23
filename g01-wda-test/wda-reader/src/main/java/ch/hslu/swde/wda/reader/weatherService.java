package ch.hslu.swde.wda.reader;

import java.util.List;

public interface weatherService {

    List<weatherDataProvider> weatherCityYear(String name, int year);
    weatherDataProvider findByName(String name);
    List<weatherDataProvider> allWeatherData();
}

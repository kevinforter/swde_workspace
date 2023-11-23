package ch.hslu.swde.wda.reader;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class weatherRWSProxyIT {

    @Test
    void weatherCityYear() {

        weatherService service = new weatherRWSProxy();

        List<weatherDataProvider> wtp = service.weatherCityYear("Davos", 2024);
        for (weatherDataProvider w: wtp) {
            assertNotNull(wtp);
        }

    }

    @Test
    void findByName() {

        weatherService service = new weatherRWSProxy();

        weatherDataProvider wtp = service.findByName("Davos");
        assertNotNull(wtp);
    }

    @Test
    void allWeatherData() {

        weatherService service = new weatherRWSProxy();

        List<weatherDataProvider> wtp = service.allWeatherData();
        for (weatherDataProvider w: wtp) {
            assertNotNull(wtp);
        }
    }
}
package ch.hslu.swde.wda.business;

import ch.hslu.swde.wda.domain.weatherData;
import ch.hslu.swde.wda.persister.GenericDAO;
import ch.hslu.swde.wda.persister.GenericDAOImpl;
import ch.hslu.swde.wda.reader.weatherRWSProxy;
import ch.hslu.swde.wda.reader.weatherService;

import java.util.List;

public class businessServiceImpl implements businessService{

    private GenericDAO<weatherData> persister = new GenericDAOImpl<>(weatherData.class);
    private weatherService service = new weatherRWSProxy();
    @Override
    public weatherData weatherCityYear(String name, int year) {
        return persister.findById(id);
    }

    @Override
    public weatherData findByName(String name) {
        return persister.findById(id);
    }

    @Override
    public List<weatherData> allWeatherData() {
        return persister.alle();
    }
}

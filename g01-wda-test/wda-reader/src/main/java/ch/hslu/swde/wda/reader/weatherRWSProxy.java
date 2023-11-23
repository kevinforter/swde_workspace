package ch.hslu.swde.wda.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class weatherRWSProxy implements weatherService{

    private HttpClient client = HttpClient.newHttpClient();
    private static final String BASE_URI = "http://eee-03318.simple.eee.intern:8080/";
    private ObjectMapper mapper = new ObjectMapper();
    private String format = "application/json";

    @Override
    public List<weatherDataProvider> weatherCityYear(String name, int year) {

        try {

            URI uri = URI.create(BASE_URI + "weatherdata-provider/rest/weatherdata/cityandyear?city=" + name + "&year=" + year);
            HttpRequest req = HttpRequest.newBuilder(uri).GET().header("Accept", format).build();
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {

                List<weatherDataProvider> msgListe = mapper.readValue(res.body(), new TypeReference<List<weatherDataProvider>>() {
                });

                return msgListe;

            } else {
                // Log-Eintrag machen
                return new ArrayList<weatherDataProvider>();
            }

        } catch (Exception e) {
            // Log-Eintrag machen
            // return new ArrayList<Message>();
            throw new RuntimeException(e);
        }
    }

    @Override
    public weatherDataProvider findByName(String name) {

        try {

            URI uri = URI.create(BASE_URI + "weatherdata-provider/rest/weatherdata?city=" + name);
            HttpRequest req = HttpRequest.newBuilder(uri).GET().header("Accept", format).build();
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {

                return mapper.readValue(res.body(), weatherDataProvider.class);

            } else {
                return null;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<weatherDataProvider> allWeatherData() {
        try {
            URI uri = URI.create(BASE_URI + "weatherdata-provider/rest/weatherdata/cities/");
            HttpRequest req = HttpRequest.newBuilder(uri).header("Accept", format).build();
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

            if (res.statusCode() == 200) {

                List<weatherDataProvider> msgListe = mapper.readValue(res.body(), new TypeReference<List<weatherDataProvider>>() {
                });

                return msgListe;

            } else {
                // Log-Eintrag machen
                return new ArrayList<weatherDataProvider>();
            }

        } catch (Exception e) {
            // Log-Eintrag machen
            // return new ArrayList<Message>();
            throw new RuntimeException(e);
        }
    }
}

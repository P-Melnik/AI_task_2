package internship.AI_task_2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import internship.AI_task_2.entity.WeatherData;
import internship.AI_task_2.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.math.BigDecimal;
import java.net.URI;

@Service
public class WeatherService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/" +
            "weather?q={city}" +
            "&APPID={yourAPPID}&units=metric";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherService(RestTemplateBuilder restTemplateBuilder,
                          ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public WeatherData fetchDataFromApi(String city) {
        URI uri = new UriTemplate(WEATHER_URL).expand(city);
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

        return convert(response);
    }

    private WeatherData convert(ResponseEntity<String> response) {
        try {
            JsonNode root = objectMapper.readTree(response.getBody());

            String city = root.path("name").asText();
            String weather = root.path("weather").get(0).path("main").asText();
            BigDecimal temperature = root.path("main").path("temp").decimalValue();
            BigDecimal windSpeed = root.path("wind").path("speed").decimalValue();

            return new WeatherData(city, weather, temperature, windSpeed);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON", e);
        }
    }
}

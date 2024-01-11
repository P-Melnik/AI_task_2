package internship.AI_task_2.controller;

import internship.AI_task_2.entity.WeatherData;
import internship.AI_task_2.repository.WeatherDataRepository;
import internship.AI_task_2.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @GetMapping("/search")
    public ResponseEntity<?> searchWeatherData(@RequestParam String city) {
        Optional<WeatherData> existingWeatherData =
                weatherDataRepository.findByCity(city);

        if (existingWeatherData.isPresent()) {
            return ResponseEntity.ok(existingWeatherData.get());
        } else {
            WeatherData newWeatherData =
                    weatherService.fetchDataFromApi(city);
            weatherDataRepository.save(newWeatherData);
            return ResponseEntity.ok(newWeatherData);
        }
    }
}

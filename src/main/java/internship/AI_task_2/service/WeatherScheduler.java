package internship.AI_task_2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class WeatherScheduler {

    @Autowired
    private WeatherService weatherService;

    @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
    public void refreshWeatherData() {
        // checks DB and refreshes data for each city in DB
        // fetched via weatherService.fetchDataFromApi(String city)
    }


}

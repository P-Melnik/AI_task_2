package internship.AI_task_2;

import internship.AI_task_2.controller.WeatherController;
import internship.AI_task_2.entity.WeatherData;
import internship.AI_task_2.repository.WeatherDataRepository;
import internship.AI_task_2.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WeatherService weatherService;
    @MockBean
    private WeatherDataRepository weatherDataRepository;

    @Test
    public void testSearchWeatherDataCityInDatabase() throws Exception {
        String cityName = "Tbilisi";
        WeatherData mockWeatherData = new WeatherData(cityName, "Sunny",
                BigDecimal.valueOf(25.5), BigDecimal.valueOf(5.0));

        when(weatherDataRepository.findByCity(cityName))
                .thenReturn(Optional.of(mockWeatherData));

        mockMvc.perform(get("/weather/search").param("city", cityName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value(cityName))
                .andExpect(jsonPath("$.weather").value("Sunny"));

        verify(weatherService, never()).fetchDataFromApi(anyString());
    }

    @Test
    public void testSearchWeatherDataCityNotInDatabase() throws Exception {
        String cityName = "NewCity";
        WeatherData mockWeatherData = new WeatherData(cityName, "Cloudy", BigDecimal.valueOf(20.0), BigDecimal.valueOf(3.5));

        when(weatherDataRepository.findByCity(cityName)).thenReturn(Optional.empty());
        when(weatherService.fetchDataFromApi(cityName)).thenReturn(mockWeatherData);

        mockMvc.perform(get("/weather/search").param("city", cityName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value(cityName))
                .andExpect(jsonPath("$.weather").value("Cloudy"));

        verify(weatherService, times(1)).fetchDataFromApi(cityName);
        verify(weatherDataRepository, times(1)).save(mockWeatherData);
    }
}

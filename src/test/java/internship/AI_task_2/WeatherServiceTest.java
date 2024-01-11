//package internship.AI_task_2;
//
//import internship.AI_task_2.entity.WeatherData;
//import internship.AI_task_2.service.WeatherService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.RestTemplate;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class WeatherServiceTest {
//
//    @MockBean
//    private WeatherService weatherService;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Test
//    public void testFetchWeatherDataFromApi() {
//        String cityName = "Tbilisi";
//        String apiUrl = "https://open-meteo.com/weather?city=" + cityName;
//
//        String jsonResponse = "{    \"coord\": {\n" +
//                "        \"lon\": 44.8337,\n" +
//                "        \"lat\": 41.6941\n" +
//                "    },\n" +
//                "    \"weather\": [\n" +
//                "        {\n" +
//                "            \"id\": 804,\n" +
//                "            \"main\": \"Clouds\",\n" +
//                "            \"description\": \"overcast clouds\",\n" +
//                "            \"icon\": \"04n\"\n" +
//                "        }\n" +
//                "    ],\n" +
//                "    \"base\": \"stations\",\n" +
//                "    \"main\": {\n" +
//                "        \"temp\": 1.84,\n" +
//                "        \"feels_like\": -0.91,\n" +
//                "        \"temp_min\": 1.84,\n" +
//                "        \"temp_max\": 1.84,\n" +
//                "        \"pressure\": 1019,\n" +
//                "        \"humidity\": 93\n" +
//                "    },\n" +
//                "    \"visibility\": 8000,\n" +
//                "    \"wind\": {\n" +
//                "        \"speed\": 2.57,\n" +
//                "        \"deg\": 180\n" +
//                "    },\n" +
//                "    \"clouds\": {\n" +
//                "        \"all\": 100\n" +
//                "    },\n" +
//                "    \"dt\": 1704981404,\n" +
//                "    \"sys\": {\n" +
//                "        \"type\": 1,\n" +
//                "        \"id\": 8862,\n" +
//                "        \"country\": \"GE\",\n" +
//                "        \"sunrise\": 1704947215,\n" +
//                "        \"sunset\": 1704980952\n" +
//                "    },\n" +
//                "    \"timezone\": 14400,\n" +
//                "    \"id\": 611717,\n" +
//                "    \"name\": \"Tbilisi\",\n" +
//                "    \"cod\": 200}";
//        ResponseEntity<String> mockResponse = ResponseEntity.ok(jsonResponse);
//
//        when(restTemplate.getForEntity(apiUrl, String.class)).thenReturn(mockResponse);
//
//        WeatherData result = weatherService.fetchDataFromApi(cityName);
//
//        assertEquals(cityName, result.getCity());
//        assertNotNull(result.getTemperature());
//        assertNotNull(result.getWeather());
//        assertNotNull(result.getWind());
//
//        verify(restTemplate, times(1)).getForEntity(apiUrl, String.class);
//    }
//}

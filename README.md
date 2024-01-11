# Weather Data Spring Boot Application

This Spring Boot application allows users to retrieve weather data for a given city. The application integrates with the Open Meteo API to fetch real-time weather information and persists the data in a PostgreSQL database using Hibernate. It includes a Spring Scheduler to periodically refresh the weather data from the API.

## Prerequisites

Before running the application, make sure you have the following prerequisites installed:

- Java 8 or later
- Maven
- PostgreSQL database

## Configuration

1. Open the `application.properties` file in the `src/main/resources` directory.
2. Configure the PostgreSQL database connection settings:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update

# Running the app 

1. git clone https://github.com/yourusername/your-repo.git
   cd your-repo
2. mvn clean install
3. java -jar target/weather-app-1.0.0.jar

# API Endpoints

Search for Weather Data by City:
Endpoint: /weather/search
Method: GET
Query Parameter: city (e.g., /weather/search?city=Tbilisi)




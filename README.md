# weather-api

## Architecture overview, limitations, and future implementations

The application uses Spring Boot as itś main framework.

This is a REST application to manipulate sample weather data from sensors. <br>
Regular endpoints are provided to insert, update, retrieve and delete weather samples and also special endpoints to 
retrieve specific data as, for example, hottest or coldest day of the year. <br>
Also, advanced queries are available to retrieve average metric data from sensors in a specific date or date range.  

The application has unit, integration, and contract tests. 

## How to execute

On one terminal window, start the database with docker-compose:

`docker-compose up db`

Run the application as Spring Boot configuration on your IDE or with provided gradle environment:

`./gradlew bootRun`

Once the application is running locally, access Swagger UI for the endpoints documentation:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html#)


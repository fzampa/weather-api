# weather-api

## Architecture overview, limitations, and future implementations

The application uses Spring Boot as it's main framework.

This is a REST application to manipulate sample weather data from sensors. <br>
Regular endpoints are provided to insert, update, retrieve and delete weather samples. <br>
Also, advanced queries are available to retrieve average metric data from sensors in a specific date range.
<br>
The REST API is versioned and documented with Swagger.

The application is covered by unit and integration tests.

### Key aspects from the architecture
The http rest protocol is followed given the Http Status code standards:

* `200 - OK` - response available
* `201 - CREATED` - for insert operations
* `204 - NO_CONTENT` - after the delete operations
* `404 - NOT_FOUND` - when entity is not found on database

Typically, development teams prefer to create business responses with their own `error_code` and `error_message`. <br/>
As this was not described as part of the exercise I opted for following the RFC and the standards so any front end
developer is able to understand the API and iterate over it.  

The application is divided into layers and, with that, different packages and classes are available:

* `api.v1` package contains the classes for the API manipulation.
* `model.v1` is the set of Transfer Objects used by the controllers to request and respond data. 
Here the api is versioned and separated from the entity layer to enable future versioning and improvements on the 
communication or database layers.
* `service` is the package responsible for the thin application logic
* `repository` is responsible for querying the database
* `entity` represents the database tables as java classes

Also, the application has an incremental database script, built with liquibase.

The application contains a `docker-compose` file that allows the database to be started without further installation and, 
therefore, quick installation on any dev environment. 
For the `@SpringBootTest` classes, `Testcontainers` are available so it is not necessary to have 
any executing database to perform the tests.

### Future implementations

As the goal was to implement this application within few hours, some improvements could be done in the future:
* Better separation of tests containers and test classes
* Creation of acceptance tests based on db data
* More data collected beyond Temperature for the samples 
* Helm configuration so the application could be "cloud ready"
* Github actions integrated with CI tool to run the tests

## How to execute

On one terminal window, start the database with docker-compose:

`docker-compose up db`

Run the application as Spring Boot configuration on your IDE or with provided gradle environment:

`./gradlew bootRun`

Once the application is running locally, access Swagger UI for the endpoints documentation:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html#)


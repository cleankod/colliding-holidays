# Colliding holidays API

## About
A simple web application for finding next colliding holidays for given countries and date.

It uses the [Holiday API](https://holidayapi.com/) underneath.

## Getting started
These instructions will get the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system. 

### Prerequisites
Project requires **Java 10** to run and JDK 10 with Gradle to build.

### Building the application
In order to build the application, simply issue:
```bash
gradle build
```

### Running the application
#### Using Gradle
```bash
gradle bootRun
```
### Using JAR
```bash
java -jar build/libs/bs-holidays.jar
```
### Using executable JAR
The resulting artifact is an executable file so it can be run like any other binary or script file:
```bash
build/libs/bs-holidays.jar
```

## Configuration
* `application.supported-countries` - coma separated list of countries supported for colliding holidays lookup.
* `application.holiday-api.key` - an API key value for the [Holiday API](https://holidayapi.com/).
* `application.holiday-api.base-url` - base URL for the [Holiday API](https://holidayapi.com/).
* `application.only-past-supported` - a `boolean` value that states whether only past dates are supported. Keep in mind though, that when set to `true` the Premium account key for the [Holiday API](https://holidayapi.com/) has to be provided. Otherwise the application will issue an error on each request when present or future dates are given. When set to `false` the application will only allow dates one month before the current one. 

## API usage
### Get colliding holidays
Endpoint:
* `GET /holidays`

Parameters:
* `date` (**required**) - start the lookup from this day on
* `countries` (**required**) - look for holidays in these countries (one or more, as long as they are supported by the application)

Sample request:
```
http://localhost:8080/holidays?date=2017-12-03&countries=PL&countries=NO
```

Will produce:
```json
{
    "holidays": [
        {
            "country": "NO",
            "date": "2017-12-25",
            "name": "1. juledag"
        },
        {
            "country": "PL",
            "date": "2017-12-25",
            "name": "Pierwszy dzień Bożego Narodzenia"
        }
    ]
}
```

Which means that starting from `2017-12-03` the first colliding holiday for Poland and Norway is on `2017-12-25`.

### Get supported countries
Endpoint:
* `GET /countries`

Returns a list of countries supported by the application.

## Monitoring
As for the HTTP endpoints, monitoring runs on a different port (8081) for security purposes.
Some endpoints to try out:
* [/health](http://localhost:8081/health) - shows application health information.
* [/info](http://localhost:8081/info) - shows application name and version. The version number is determined and set at build time so it may show a placeholder when started in IDE.

For more information about configuration and a complete list of endpoints, please refer to [this guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-endpoints).

## Logging
Each request causes the `correlationId` to be generated and added to each logged line caused by such request. This eases looking for potential problems in the application. The `correlationId` can be found at the begging of each log line. If the value `[none]` is present it simply means that the log entry was not caused by the API request.

By default, the application logs into three files:
* `web-trace.log` - all requests made to the API
* `holiday-client-requests-trace.log` - all requests made to the [Holiday API](https://holidayapi.com/)
* `application.log` - application specific logs 

## Built with
* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Gradle](https://gradle.org/) - Dependency Management and build tool
* [Feign](https://github.com/OpenFeign/feign) - Java HTTP client binder
* [Hazelcast](https://hazelcast.com/) - Cache manager
* [Spock Framework](http://spockframework.org/) - Testing and specification framework
* [Jadler](https://github.com/jadler-mocking/jadler) - HTTP mock fot testing purposes
* [Project Lombok](https://projectlombok.org/) - Less boilerplate code
* [Spring Boot Actuator](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-project/spring-boot-actuator) - For monitoring Spring Boot Application

## Assumptions and design decisions
### Black-box testing
Black-box testing is mostly used in order to favor refactoring. It is much simpler to completely change the underlying encapsulation of an use case without changing the tests.
### Spring-less tests
Only the `BaseMvcSpec` contains Spring-specific stuff. This approach eases the migration to other potential framework or toolset. The whole specification for the project stays the same. Moreover, the application is tested end-to-end, also with the exposed contract (API).
### Spring-less modules
Wherever possible, no Spring-specific stuff was used inside of actual modules (in exception of controllers and the `application` module). This also eases potential framework change.
### Package-scoped classes
Package scoped access is used whenever possible to encapsulate the internal classes of a module. There is only one exception to this rule - the component with cached results. It has to be wrapped in a AOP-proxy in order to enable caching in a painless way. It has to be therefore declared public in order to enable its construction in the main application class.
### Mocking Holiday API in tests
In order not to stress the actual [Holiday API](https://holidayapi.com/) servers, the mocked responses are used for white-box testing. It also makes the tests complete faster.
### Caching Holiday API results
Some given parameters may stress the [Holiday API](https://holidayapi.com/) servers too much due to many requests sent in order to find colliding holidays. That is why a caching abstraction with a distributable cache manager are used. 

## TODO
There are few thing still pending for improvement:
- [ ] the holiday fetching logic may be looking for whole months instead of day-by-day requests, and then find colliding holidays in such results
- [ ] the code for finding colliding holidays is kinda procedural
- [ ] provide a documentation fot the API ([OpenAPI](https://swagger.io/docs/specification/about/) or [JSON-doc](http://jsondoc.org/)).

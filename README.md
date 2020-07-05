# Coding assignment for Europeana

##Problem Statment

  Find the solution for the following problem:
  Determine the smallest positive number that can be divided by a sequential range of numbers (from 1 to 25)
  without remainder.
  The following examples show the expected outcome:
  ? 2520 is the smallest number that can be divided by 1 to 10
  ? 360360 is the smallest number that can be divided by 1 to 15

## Tech Stack

  * Java 8
  * Spring Boot
  * JUnit 
  * Swagger2
 
## Endpoints implemented
#### POST /setUpperLimit
This will set the upper limit for the given problem.
* Request body
{
"maxLimit":10
}

 * Response body
{
  "maxLimit": 10,
  "result": 2520,
  "timetaken": 3 // this time is in microseconds
}
#### GET /getResult
This will calculate the result and time taken in calculating this result.
* Request Headers
{
  "Accept": "application/json" or "application/xml"
}

 * Response body
{
  "maxLimit": 10,
  "result": 2520,
  "timetaken": 3 // this time is in microseconds
}
 

## Running the application

To build application: 
mvn clean install

To run application: 
mvn spring-boot:run


###To start application: 
http://localhost:9090/

### Documentation

Swagger2 documentation is available.
http://localhost:9090/swagger-ui.html#

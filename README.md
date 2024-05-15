# stock-quote-api

Microservice application designed to interface with the Finnhub API for retrieving and updating stock market data for specified symbols(i.e.AAPL). The application perform two main functions:

* Retrieve and display stored stock data from a database to the user.
* Refresh and update the database with the latest stock data from the Finnhub API.

## Technologies

* Java 21
* SpringBoot
* Gradle
* intelliJ (as IDE)

## Get Start

### Clone project

```
$ git clone https://github.com/benavid/stock-quote-api.git
```

### Compiling

```
$ cd stock-quote-api
$ ./gradlew clean build
```

### Start the application

```
$ cd stock-quote-api
$ java -jar build/libs/stock-quote-api-0.0.1-SNAPSHOT.jar
```

### Test application health
In your browse put the following url
```
http://localhost:8093/api/actuator/health
```
You should see the following that indicate that the application is up and healthy
```
{
   "status":"UP",
   "components":{
      "db":{
         "status":"UP",
         "details":{
            "database":"H2",
            "validationQuery":"isValid()"
         }
      },
      "diskSpace":{
         "status":"UP",
         "details":{
            "total":502392610816,
            "free":400779972608,
            "threshold":10485760,
            "path":"/home/andresbenavides/projects/utoppia/stock-quote-api/.",
            "exists":true
         }
      },
      "ping":{
         "status":"UP"
      }
   }
}
```




### How can you run the APIs?

You can find all the necessary information to exercise the APIs in the swagger documentation

```
http://localhost:8093/api/swagger-ui/index.html
```

### How access to H2 db in memory?

You can access to H2 db console put the following url in your browser

```
http://localhost:8093/api/h2-console/
```

## Code quality and security

### Generate test coverage report

When you compile the applicatioin with build gradle task automatically junit are runned and test coverage is calculated
After execute the build gradle task you can find the test coverage repost in **build/reports/tests/coverageReport/index.html**

```
$ cd stock-quote-api
$ ./gradlew build
$ cd build/reports/tests/coverageReport
```


### Vulnerability Scanner

When you execute the dependencyCheckAnalyze gradle task automatically the vulnerability dependency report is calculated
After execute the dependencyCheckAnalyze gradle task you can find the dependency check repost in **build/reports/dependency-check-report.html**

```
$ cd stock-quote-api
$ ./gradlew dependencyCheckAnalyze
$ cd build/reports
```



# Daily Report RESTful/CRUD service

RESTful Java service for CRUD operations against coronavirus infections rate table.

## Operations
To build this project: 
```
./gradlew clean build
```

To create a distributable package:
```
./gradlew distTar
```

To install locally in the `build` directory:
```
./gradlew installDist
```

## Database
This service was made to run against the AWS RDS/MySQL service. Please deploy RDS before you can continue this exercise.
After the service is deployed, create a schema where the service table is going to be deployed. Table name is *daily* 
and is hardcoded in the service. Single table schema for this table is located here:
 
[Schema for the "daily" table](src/main/resources/daily.sql)

## Configure
Database connection information needs to be provided to the program via environment variables:
```
export RDS_HOSTNAME=<supply value>
export RDS_PORT=<supply value>
export RDS_USERNAME=<supply value>
export RDS_PASSWORD=<supply value>
export RDS_DATABASE=supply value>
```

After the required environment variables are set, validate environment values:
```
env | grep -i rds
``` 

switch to the service installation directory:
```
cd build/install/service-crud-rest-relational/
```

... and launch the application:
```
bin/service-crud-rest-relational
```

There are multiple ways you can interact with the service, you can use "curl" tool, or a REST client like "Postman",
but even putting this into a browser should produce results:
```
localhost:9998/daily/dateLocation/2020-07-23/Jersey
```

# Running the service in production

## Prerequisites

The following components are required: 

* Java 17 or later. 
* MariaDB 11 or later. 
* Nginx to handle HTTPS requests and forward them to the application. 

## Configuration

The following environment variables must be set:

```shell
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
SPRING_MAIL_HOST
SPRING_MAIL_PORT
SPRING_MAIL_USERNAME
SPRING_MAIL_PASSWORD
SPRING_PROFILES_ACTIVE
```

## Running the application

Just use an ordinary `java -jar` command to run the application, better to do it in `tmux`. 

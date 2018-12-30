# spring-thymeleaf-json-persistence
Json data persistence input from Spring thymeleaf UI

## About
* A simple and minimal web app powered by spring boot(2.0.5.RELEASE) to let user to signUp, edit the form and list the registered entries
* Using thymeleaf for UI templates, minimal input validation in place
* Using gson library (2.8.5) for Json marshalling and unmarshalling
* Json data persistece on  the Filesystem, current working dir

## Requirements
* Java 1.8
* Maven 3.5.2 (may be 3+)
* Chrome or Firefox web browser

## build
mvn clean install

## run
mvn spring-boot:run

## test
http://localhost:8080/form

http://localhost:8080/list

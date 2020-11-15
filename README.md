#Project Description: This Spring boot project contains 2 REST endpoints:
    1. GET : return “Up” if service is up
    2. POST : return the concatenated responses of the Get call of Service 2(centime_hello_microservice) and the Post call of Service 3(centime_concatenate_microservice)

#How run application : 

## Using Intellij
1. Clone this repo.
2. Go to project directory and import project or pom.xm in IDE as maven project
4. Make build(clean install)
5. Run spring boot app
7. Open http://localhost in browser 

## using maven
 1.mvn clean install
 2.mvn spring-boot:run
 
# Getting Started
swagger link : http://localhost:8761/swagger-ui/index.html#/
eureka server link : http://localhost:8761/
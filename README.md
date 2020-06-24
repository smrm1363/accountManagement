#Account management project 

In this project I tried to implement a very simple account management. It has APIs for managing the account and transfer money to it.

The operations provided via it are opening account, get account information, deposit money, withdrawal, transfer money, get the transfer transactions records.
It supports a money exchange while transferring between accounts. For simplicity, I supposed the currency unit are the currencies of the source and destination accounts.

I used a third party API for finding current exchange, for that I used the Adapter pattern which allows to change the third party API easier. I just support a limited number of currencies, and we can add to them in an Enum.
###Technologies
I used following technologies in this project:
 - Java 8
 - Spring boot
 - JPA
 - REST
 - Lombok
 - Junit
 - Mockito
 - Swagger
 - H2 DB
 - Maven
 - Docker
###How to run
For building, it you can run bellow command via Maven:
 
 `mvn clean install`
 
 You also can use Docker via bellow command:
 
 `docker build -t springio/gs-spring-boot-docker .`
 
 After running the application you can use a Swagger documentation page to check the APIs, by default the path of the page should be like the bellow:
 
 `http://localhost:8080/swagger-ui.html#`
 
 You also have access to H2 database console via bellow path:
 
  `http://localhost:8080/h2-console`
  
  ###Some notes
  Since this is a sample application it is not very complete and there would be many things to add such as integration tests, more unit tests for all scenarios, security, logging, scaling, etc. 

  

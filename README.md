# Step-by-step walkthrough - Build your project and deploy on heroku with Cloud DB
## Project Set Up
1. (MySQL Workbench) connect to Digital Ocean Cloud with DOAdmin
2. (MYSQL Workbench) Create database and tables
3. (MySQL Workbench) Create a user and assign the database privileges 
4. (DigitalOcean website) Retrieve the connection information
    * Username
    * Password
    * Host
    * Port
    * Database
5. Generate your project with Spring Initializr with the following dependencies
    * Spring DevTools
    * Thymeleaf
    * Spring Web
    * JDBC API
6. Add the additional dependencies for:
    * mysql-connector-java
    * jakarta.json
    * jacoco test report
7. Add the DigitalOcean connection information to application.properties
```
spring.datasource.url=${secrets.SPRING_DATASOURCE_URL}
spring.datasource.username=${secrets.SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${secrets.SPRING_DATASOURCE_PASSWORD}
```
8. Add system.properties file to project
```
    java.runtime.version=17
```
***
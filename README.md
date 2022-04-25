# Step-by-step walkthrough - Build your project and deploy on heroku with Cloud DB
### Project Set Up
1. _(MySQL Workbench)_ connect to Digital Ocean Cloud with DOAdmin
2. _(MYSQL Workbench)_ Create database and tables
3. _(MySQL Workbench)_ Create a user and assign the database privileges 
4. _(DigitalOcean website)_ Retrieve the connection information of created user
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
6. _(IDE)_ Add the additional dependencies for:
    * mysql-connector-java
    * jakarta.json
    * jacoco test report
7. _(IDE)_ Add the DigitalOcean connection information to application.properties
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
### Develop Your Project
9. Create your models
10. Create your repositories & SQL queries
11. Create your Service layer, Transactional and Exceptions
12. Create your controller layer
13. Test your project locally
14. Write test cases
*** 
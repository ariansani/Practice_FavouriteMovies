# Step-by-step walkthrough - Build your project and deploy on heroku with Cloud DB
### Project Set Up
1. _(MySQL Workbench)_ connect to Digital Ocean Cloud with DOAdmin
2. _(MYSQL Workbench)_ Create database and tables
3. _(MySQL Workbench)_ Create a user and assign the database privileges 
4. _(DigitalOcean website)_ Retrieve the connection information of created user
    * USERNAME
    * PASSWORD
    * HOST
    * PORT
    * DATABASE
5. _(Github Secrets)_ Add the connection information to your Github Secrets
    * SPRING_DATASOURCE_URL
    ```
    jdbc:mysql://{HOST}:{PORT}/{DATABASE}
    ```
    * SPRING_DATASOURCE_USERNAME
    ```
    {USERNAME}
    ```
    * SPRING_DATASOURCE_PASSWORD
    ```
    {PASSWORD}
    ```
6. Generate your project with Spring Initializr with the following dependencies
    * Spring DevTools
    * Thymeleaf
    * Spring Web
    * JDBC API
7. _(IDE)_ Add the additional dependencies for:
    * mysql-connector-java
    * jakarta.json
    * jacoco test report
8. _(IDE)_ Add the connection information from your Github Secrets to application.properties
```
spring.datasource.url=${secrets.SPRING_DATASOURCE_URL}
spring.datasource.username=${secrets.SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${secrets.SPRING_DATASOURCE_PASSWORD}
```
9. Add system.properties file to project
```
java.runtime.version=17
```
***
### Develop Your Project
10. Create your models
11. Create your repositories & SQL queries
12. Create your Service layer, Transactional and Exceptions
13. Create your controller layer
14. Test your project locally
15. Write test cases
*** 
### Heroku Deployment
16. test
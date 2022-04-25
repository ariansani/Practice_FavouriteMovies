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
5. _(Github Secrets)_ Add the connection information to your Github Secrets (without the curly braces)

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
8. _(IDE)_ Add the connection information from your Github Secrets to application.properties (WITH the curly braces)
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
16. _(Heroku Dashboard)_ Login to heroku
17. _(Heroku Dashboard)_ Go to account settings and retrieve the API Key
18. _(Github Secrets)_ Add the Heroku connection to your Github Secrets (without the curly braces)

    * HEROKU_API_KEY
    ```
    {YOUR_HEROKU_API_KEY}
    ```
    * HEROKU_EMAIL
    ```
    {YOUR_HEROKU_EMAIL}
    ```
19. _(Heroku Dashboard)_ Create an app with your own app name. We will need this name to put in our YAML file.
20. _(IDE)_ In your YML steps, add the code block below to deploy to heroku
    * Replace the heroku_app_name with your own app name.
    * Beware of proper indentation
```yml
- name: DeployHeroku
        uses: akhileshns/heroku-deploy@v3.12.12 
        with:
          heroku_api_key: ${{ secrets.heroku_api_key }}
          heroku_app_name: testuploadarian2
          heroku_email: ${{ secrets.heroku_email }}
          branch: ${{ github.ref_name }}
        env:
          HD_SPRING_DATASOURCE_URL: ${{ secrets.SPRING_DATASOURCE_URL }}
          HD_SPRING_DATASOURCE_USERNAME: ${{ secrets.SPRING_DATASOURCE_USERNAME }}
          HD_SPRING_DATASOURCE_PASSWORD: ${{ secrets.SPRING_DATASOURCE_PASSWORD }}
```
21. Deploy your code to github
22. Open your heroku URL to check that it runs successfully with Digital Ocean cloud.

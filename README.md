# Aws Web Automation using Selenium/Java

This project is a framework for Web Automation written in Java. Classes and basic scenarios were created for [Amazon Web Page ](https://www.amazon.in/)

## External dependencies

For this project to run, you would need to install below 4 dependencies on your machine:

- **[Java 11](https://openjdk.java.net/projects/jdk/11/)** (as the core programming language)
- **[Maven 3.8.5](https://maven.apache.org/download.cgi)** (for dependency management)
- **[Google Chrome latest version](https://www.google.com/chrome/?brand=CHBD&gclid=Cj0KCQjwr-SSBhC9ARIsANhzu15P0PA-n9Zp4NpxKaOHVGtBD1TZQH0HlQQE6hUfsOFAU1nf-Rzdlf4aAoTJEALw_wcB&gclsrc=aw.ds)** (browser to run your tests)
- **[Docker Engine 20.10.14](https://docs.docker.com/get-docker/)** (for running automations in container)

> If your JAVA_HOME is set to anything other than JDK 11, you would need to update the path. Else your project
> will not run. Also, do remember to set the correct JDK settings in your IDE.

## Getting Started

For easiest way to getting started, extract this project and open it from IntelliJ or Eclipse ([lombo](https://projectlombok.org/setup/eclipse) is needed).
> Then Do a dry run on test in : test -> java -> TestAWSWeb class and see if your setup is correct.  


## Framework - How it works

This framework uses principles of PageObject Pattern and "keyword-driven", it maps HTML components to their class (e.g. "Menu", "Product" - extends AbstractPageComponent) . For each class, we can have specific operations. Example: product.sortBy("Price: Low to High").

## Finding elements

One of the element finding strategy used in this framework is to first find the parent element via its id, and then find the desired element via text.
For example: first we find the ID of the main menu and then we find the desired element using the text ("Mobile", "Eletronics")
This strategy or similiar helps to speed up the creation and maintenance of scripts, as it is more readable and natural.

The main methods available in AbstractPageComponent.

 ### findByParentIdAndText(String parentId, String text)	
	Pass the id of parent element and the text of the desired element.
	findByParentIdAndText("nav-main", "Mobiles")

 ### clickByAttr - find by attribute and click
	Pass the name of the attribute and its value.
	clickByAtt("id","menu-product");
	clickByAtt("class","menu-visible");
	clickByAtt("data-image-index","1");
		
 ### typeByAttr - find by attribute and type
	Pass the name of the attribute,its attribute value and the text to be entered.. 
	typeByAttr("id", "search-bar-id", "IPhone");  
   
 ### clickByXPath - find by xpath and click
    Pass the xpath, sometimes we need a more sophisticated strategy
	clickByXPath("//ul//a//span[normalize-space(text())='Eletronics']//ancestor::a");
		 
 ## typeByXPath - find by xpath and type
    Pass the xpath and the text to be entered..
	typeByXPath("//div[2]/div/form/div[3]/div/span/input","IPhone");
   
## Test Data
The files containing the test data are located in /resources/test_data. Each file name has the same name as the test method (e.g. sortByScenarios.csv).
The fields are separated by "|". The last column field is the assertion, the other fields depend on the test case.

## Scenarios
Four scenarios are available, class TestAWSWeb:
 - sortByScenarios: navigate to a specific product and then sort by all available options.
 - amazonPrimeScenarios: navigate to a product and select "Get it by tomorrow" or "Get it in 2 days".
 - mainFilterCategoriesScenarios: navigate to a product and select any of the main category filters ("Brand- > Samsung", "Item condition- > New",etc)
 - searchBarScenarios: Select different departments in the search bar and then search for a product.

## Docker 
In this section, we will run our automation using Docker.
For this we need a Selenium StandAlone Server and for the report we will use Allure Report Service.

For more information:
- [Docker images using Selenium](https://github.com/SeleniumHQ/docker-selenium)
- [Allure Report](https://docs.qameta.io/allure/)
- [Docker image for Allure Report](https://github.com/fescobar/allure-docker-service)


### Generate the image
Easiest  way: copy the Dockerfile to project root folder and then execute the following command
```bash
docker build -t awswebautomation .
```
Dockerfile
```bash
FROM maven:3.8.5-openjdk-11-slim
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install -DskipTests=true
```

### Running Selenium Standalone with Chrome
We'll run the Selenuium Standalone Server with Chrome. You can check its page after running 
http://localhost:4444/ui.

```bash
docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:4.1.4-20220427
```
### Running Allure report service
```bash
docker run -d -p 5050:5050 -v allure-results:/app/allure-results -v allure-reports:/app/allure-reports -e CHECK_RESULTS_EVERY_SECONDS=5 -e KEEP_HISTORY=20 frankescobar/allure-docker-service
```
Check it: http://localhost:5050/allure-docker-service/projects/default/reports/latest/index.html#

### Running the automation
In this example, we are
 - Setting a specific scenario '-Dtest=TestAWSWeb#amazonPrimeScenarios', but you can change the scenario or remove to execute all.
 - Indicating the browser '-DBROWSER=chrome'. If you change the the Selenium Server to Edge for example, you change the value to 'edge'
 - Indicating the host '-DHOST=host.docker.container'
 - Configuring the volume for allure report
 
```bash
docker run --net host -v allure-results:/usr/src/app/allure-results -v allure-reports:/usr/src/app/allure-reports awswebautomation mvn test -Dtest=TestAWSWeb#sortByScenarios -DBROWSER=chrome -DHOST=host.docker.container
```
### Watch the automation and access the report
 - For watching each scenario: access http://localhost:4444/ui -> Sessions -> Click on 'VideoCam Icon'. The password is 'secret'.
  IMPORTANT: VNC allows sending commands, so do not click on video or send comands while watching , otherwise the automation might broken.
  
 ![image](https://user-images.githubusercontent.com/105996291/169858803-842c557c-1ddc-4b7e-8e1f-9732643c6015.png)

 - Check the report: http://localhost:5050/allure-docker-service/projects/default/reports/latest/index.html#

![image](https://user-images.githubusercontent.com/105996291/169846857-77a09c81-3691-49c0-aee7-bb699cd20efe.png)

![image](https://user-images.githubusercontent.com/105996291/169846901-67bcb85e-4557-4a8b-a68d-80ad418482f2.png)

![image](https://user-images.githubusercontent.com/105996291/169846995-39000f97-e433-4c0c-8793-f7da9db9767b.png)



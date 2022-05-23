# Aws Web automation

This project is a framework for Web Automation written in Java. Classes and basic scerarios were created for [Amazon Web Page ](https://www.amazon.in/)

## External dependencies

For this project to run, you would need to install below 3 dependencies on your machine:

- **[Java 11](https://openjdk.java.net/projects/jdk/11/)** (as the core programming language)
- **[Maven 3.8.5](https://maven.apache.org/download.cgi)** (for dependency management)
- **[Google Chrome latest version](https://www.google.com/chrome/?brand=CHBD&gclid=Cj0KCQjwr-SSBhC9ARIsANhzu15P0PA-n9Zp4NpxKaOHVGtBD1TZQH0HlQQE6hUfsOFAU1nf-Rzdlf4aAoTJEALw_wcB&gclsrc=aw.ds)** (browser to run your tests)
- **[Docker Engine 20.10.14](https://docs.docker.com/get-docker/)** (for running automations in container)

> If your JAVA_HOME is set to anything other than JDK 11, you would need to update the path. Else your project
> will not run. Also, do remember to set the correct JDK settings in your IDE.

## Getting Started

For easiest way to getting started, extract this project and open it from IntelliJ or Eclipse (lombo is needed).
> Then Do a dry run on test in : test -> java -> TestAWSWeb class and see if your setup is correct.  


## Framework - How it works

This framework uses principles of PageObject Pattern and "keyword-driven", it maps HTML components to their class (e.g. "Menu", "Product" - extends AbstractPageComponent) . For each class, we can have specific operations. Example: product.sortBy("Price: Low to High").

## Locating elements

One of the element finding strategy used in this framework is to first find the parent element via parentId, and then find the desired element(item on menu) via text.
For example: first we find the ID of the main menu and then we find the desired element using the text ("Mobile", "Eletronics")
We encourage to use this strategy or similiar. It helps to speed up the creation and maintenance of scripts, as it is more readable and natural.

Main methods available in the AbstractPageComponent.

 ### findByParentIdAndText(String parentId, String text)	
	Pass the id of parent element and the text of the desired element.Examples:
	findByParentIdAndText("nav-main", "Mobiles")

 ### clickByAttr - find by attribute and click
	Pass the name of attribute and its value.
	clickByAtt("id","menu-product");
	clickByAtt("class","menu-visible");
	clickByAtt("data-image-index","1");
		
 ### typeByAttr - find by attribute and type
	Pass the name of attribute, attribute value and the text. 
	typeByAttr("id", "search-bar-id", "IPhone");  
   
 ### clickByXPath - find by xpath and click
    Pass the xpath, sometimes we need a more sophisticated strategy
	clickByXPath("//ul//a//span[normalize-space(text())='Eletronics']//ancestor::a");
		 
 ## typeByXPath - find by xpath and type
    Pass the xpath strategy
	typeByXPath("//div[2]/div/form/div[3]/div/span/input","IPhone");
   
## Test Data
The files containing the test data are located in /resources/test_data. For each file we specify the same test method name (e.g. sortByScenarios.csv).
The fields are sperated by "|". The last field is the assertion, the others fields depend on the test case.

## Scenarios
Four scenarios are avaible, class TestAWSWeb:
 - sortByScenarios: navigate to a specific product and then sort by using all options available
 - amazonPrimeScenarios: navigate to a product and select "Get It by Tomorrow" or "Get It in 2 Days"
 - mainFilterCategoriesScenarios: navigate to a product and select each of main caterogy filter ("Brands->Samsung", "Item Condition->New",etc)
 - searchBarScenarios: select differents departaments in the search bar and then search for a product

## Docker 
In this section, let's execute our automation using Docker.
For that, we need a Selenium Server and for report we'll use Allure Report.

### Generate the image
Generate the image from project folder using the Dockerfile.
```bash
docker build -t awswebautomation .
```
Dockerfile
```bash
FROM maven:3.8.5-openjdk-11-slim
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install -DskipTests=true
CMD ["mvn", "test"]
```


### Running Selenium Standalone with Chrome
```bash
docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:4.1.4-20220427
```
### Running Allure report service
```bash
docker run -p 5050:5050 -v allure-results:/app/allure-results -v allure-reports:/app/allure-reports -e CHECK_RESULTS_EVERY_SECONDS=3 -e KEEP_HISTORY=1 frankescobar/allure-docker-service
```
### Running the automation
In this example, a scenario is specified using '-Dtest=TestAWSWeb#amazonPrimeScenarios', but you can change the scenario or remove to execute all.
```bash
docker run --net host -v allure-results:/usr/src/app/allure-results -v allure-reports:/usr/src/app/allure-reports awswebautomation mvn test -Dtest=TestAWSWeb#amazonPrimeScenarios -DHOST="host.docker.container"
```



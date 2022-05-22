# Aws Web automation

This project represents a Web Automation for AWS Web Site.

## External dependencies

For this project to run, you would need to install below 3 dependencies on your machine:

- **[Java 11](https://openjdk.java.net/projects/jdk/11/)** (as the core programming language)
- **[Maven 3.8.5](https://maven.apache.org/download.cgi)** (for dependency management)
- **[Google Chrome latest version](https://www.google.com/chrome/?brand=CHBD&gclid=Cj0KCQjwr-SSBhC9ARIsANhzu15P0PA-n9Zp4NpxKaOHVGtBD1TZQH0HlQQE6hUfsOFAU1nf-Rzdlf4aAoTJEALw_wcB&gclsrc=aw.ds)** (browser to run your tests)

> If your JAVA_HOME is set to anything other than JDK 11, you would need to update the path. Else your project
> will not run. Also, do remember to set the correct JDK settings in your IDE.

## Getting Started

For easiest way to getting started, extract this project and open it from IntelliJ.
> Then Do a dry run on test in : test -> java -> TestSandbox class and see if your setup is correct.  

Tip: Do remember to update this readme file for anything else that you think needs updating here!

## Framework - How it works

This framework maps HTML components/sections to their class (e.g. "Menu", "Product" - extends AbstractPageComponent) and their operations, a kind of "Page Object Pattern".
We also use "keyword-driven" to provide more readability.

One of the element finding strategy used in this framework is to first find the parent element via parentId, and then find the desired element(item on menu) via text.
For example: first we find the ID of the main menu and then we find the desired element using the text ("Mobile", "Eletronics")
We encourage to use this strategy or similiar. It helps to speed up the creation and maintenance of scripts, as it is more readable and natural.
The method is findByParentIdAndText(String parentId, String text).

Another methods available for locatiing elements on AbstractPageComponent
   - clickByAtt: pass the name of attribute and its value.Examples:
	clickByAtt("id","menu-product");
	clickByAtt("class","menu-visible");
	clickByAtt("data-image-index","1");
		
   - typeByAtt: pass the name of attribute, attribute value and the text. Examples:
		typeByAttr("id", "search-bar-id", "IPhone");  
   
   - clickByXPath: pass the xpath, sometines we need a more sofisticated strategy to locate
		 clickByXPath("//ul//a//span[normalize-space(text())='Eletronics']//ancestor::a");
		 
   - typeByXPath: pass the name of attribute and its value
		typeByXPath("//div[2]/div/form/div[3]/div/span/input","IPhone");

 







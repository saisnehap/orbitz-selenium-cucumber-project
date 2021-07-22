# Orbitz - Cucumber - Selenium - Page Objects Model.

# Cucumber Page Objects Model

A sample project for Orbitz-To book a expensive round trip flight

# Instructions

Clone the repo:

Git:
```
$ git clone https://github.com/saisnehap/orbitz-selenium-cucumber-project.git
```

## Prerequisites

In order to run browser tests, Selenium will need to be able to drive a browser
installed to your system.

* Java JDK setup 
* Cucumber plugins
* Chrome browser

Download chromedriver.exe from [a link](https://chromedriver.chromium.org/downloads) and place it in the following folder `drivers/chromeDriver/chromedriver`

---------------------------------------------------------

## Verify installation

## Use Maven

Open a command window and run:

    mvn install
    
        or
        
    mvn test   

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` 


## Overriding options

The Cucumber runtime parses command line options to know what features to run, where the glue code lives, what plugins to use etc.
When you use the JUnit runner, these options are generated from the `@CucumberOptions` annotation on your test.

Sometimes it can be useful to override these options without changing or recompiling the JUnit class. This can be done with the
`cucumber.options` system property. The general form is:


Using Junit:

    Execute the TestRunner class from `src/test/java/TestRunner.java`  

Using Maven:

    mvn -Dcucumber.options="..." test

Let's look at some things you can do with `cucumber.options`. Try this:

    -Dcucumber.options="--help"

That should list all the available options.

### Run a subset of Features or Scenarios

This works because Maven puts `./src/test/resources` on your `classpath`.
You can also specify files to run by filesystem path:

    -Dcucumber.options="target/cucumber-reporting-html"

You can also specify what to run by *tag*:

    -Dcucumber.options="--tags"


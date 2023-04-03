# Ravine
## What is this about
This is very simple application, which checks the Website of a well-known bike-reseller (Can*on) for bike-models in specific sizes and sends an email message to a configured e-mail address, if the desired bike becomes available.

## Tech-Stack
This app is built as a simple spring-boot console-app using Jsoup to crawl the specific website. No UI needed.

## How to build and run
1. run mvn clean install
2. start the application with the respective java command i.e. on your own web-server or a local machine

## How to configure
Everything is configured in the [application.yaml](src/main/resources/application.yaml) of the app.

### Mail-Server
Simply configure your mail-server providing:
* a username
* a password
* a host
* a port
* and a protocol
* a from-mail address

### Mail-Receiver
In order to configure the mail to receive the alerts, simply specify the **mail-to** property.

### Crawler time-scheme
In order to configure the time-scheme for the crawler, a spring-boot cron expression can be given (⚠️ spring cron-expression are slightly different from regular cron expressions)

### I want bikes - give me bikes!
To specify the bikes, simply gives the bikes-details page to the **bikes.details.urls** property in the [application.yaml](src/main/resources/application.yaml). This parameter takes a comma separated list of urls. So can crawl for more than one bike at once.
Additionally to the bike types, sizes can also be given by the **bikes.details.sizes** parameter. Again, this parameter takes a list of comma-separated sizes.


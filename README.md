# Sports Center Application

REST Web service of the Sports Center is where you can create users,
add schedules for courts and reserve courts for at least 30 minutes 
with an e-mail confirmation.
## Table of contents
* [Technologies](#technologies)
* [Setup](#setup)
* [Security](#security)
* [General instruction](#general-instruction)
* [Body JSON templates](#body-json-templates)

## Technologies
* Spring Boot *version:* 2.3.1.RELEASE
* Java JWT *version:* 3.8.3
* MySQL *version:* 5.6
## Setup
To run this application it is necessary to install MySQL server and 
configure *application.yml* file or use h2 database configured in 
*application-h2.yml* file. To change the email configuration please see 
*application.properties* file. You are welcome to use the existing 
configuration. To run an application working with the h2 database 
enter `-Dspring.profiles.active=h2` in *VM options*.
## Security
This application uses Json Web Tokens for authorization.  
To create a *USER* or an *ADMIN*, make Post request at the URL: localhost:*8080*/clients
using this template as a Body:

```
{
    "name" : "name",
    "surname" : "surname",
    "email" : "name.surname@email.com",
    "password" : "password",
    "phone" : "+48 123 456 789",
    "role" : "user"
}
```

Without *ADMIN* you will not have access to Post and Delete requests in */schedule* and */courts* paths. 
To login use */login* path. Post JSON with an e-mail as your *username*
and your *password*. Do not forget your password. Default *ADMIN* login template:

```
{
    "username" : "admin@mail.com",
    "password" : "password"
}
```
## General instruction
To make a reservation you need to create at least one Client, Court 
and some slots in schedule. Slots are created automatically in a given 
period of time every 30 minutes, e.g. for period 12:00 - 14:00 the service 
will create 4 slots. You have to post date with minutes divisible by 
30 e.g. 13:30, 17:00. Use 'ZonedDateTime' format in JSON and Court id.
In reservation JSON post the first and the last slot you want to reserve and the Client id.
You can reserve a court for more than 30 minutes.

## Body JSON templates
##### Court
```
{
    "name" : "Court Name"
}
```
##### Schedule
```
 {
     "startTime" : "2020-08-23T10:00:00+02:00",
     "endTime" : "2020-08-23T15:30:00+02:00",
     "courtId" : 2
 }
```
##### Reservation
```
{
    "startScheduleId" : 3,
    "endScheduleId" : 4,
    "clientId" : 1
}
```
##### Login
```
{
    "username" : "name.surname@email.com",
    "password" : "password"
}

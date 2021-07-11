# sdutils-api
Utility API's for the common functionalities

# API's available
   1.) An API to give the Accounting/Retail Calendar (445 Calendar) for the given Year & Month.
   
<u> <b> Supported Endpoints  </b> </u>

/sdutils/api/calendar/accounting/year/{year}

/sdutils/api/calendar/accounting/year/{year}/month/{month}

 - {year} : Mandatory Integer (Range from 1 to 3000 - Configurable)
 - {month} : Optional Integer (Range from 1 to 12 - Configurable)
 

<u> <b> Features </b> </u>

   1. Supports both XML & JSON response (based on the Accept header)
   2. EhCache used in the backend to cache the response (Max time to live is 60 seconds, but configurable)
   3. Accepts only whole numbers (Year -  range of 1 to 3000 , Month - range of 1 to 12)
   4. Comes with Unit & Integration test cases

# Technologies Used

Spring Boot (V 2.1.2),
Java 8,
Tomcat

# API Availability

This Utility API has been hosted in Azure Public Cloud and can be accessed by the below URL

https://sdutils-api.azurewebsites.net


# Future Scope

1. Support for passing flavour of the calendar quarter 445, 454 or 544 as an input
2. Support for Swagger Documentation
3. Having OAUTH to authorize/authenticate the callers
4. Having DB support for auditing 


# About the Author

Senthilnathan Durairaj is a passionate programmer currently working as a Solutions/Technical Architect.







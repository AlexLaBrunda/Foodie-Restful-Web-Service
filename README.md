# Foodie-Restful-Web-Service
This is a restful web service which apon receiving a http get request containing a street address will return a JSON object with a list of nearby restaurants.
The main files for this project can be found in complete/src/main/java/hello.
This project is built off of the java spring tutorial and is a mavin project. 
In this project I created a Web Service that takes an address and respond with info of nearby restaurants. First, I created a basic Web Service with a single REST endpoint that returns a response back to the client. Then we modified the requests to the web service to pass a parameter called with some address string. I used the address and geocode it using the Geocod.io API and parsed the response with the Jackson library. After parsing the latitude and longitude of the address, I used the Zomato API to find some nearby restaurants.

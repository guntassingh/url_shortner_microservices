# Url Shortener

Link shortening service will convert long url in shortcode and redirect to actual url using short code url.

## Installation


#Prerequisite:-
Linux Ubuntu 16.04
Docker version 18.09.7
docker-compose version 1.24.1,
java "1.8.0_222"
maven 
 
## Step
##############Download the Project, Build the code base using maven##############

Go to ConfigServerApplication folder and Run command mvn clean install -DskipTests
Go to EurekaServer folder and Run command mvn clean install -DskipTests
Go to ZuulGatewayService folder and Run command mvn clean install -DskipTests
Go to URLShortener folder and Run command mvn clean install -DskipTests

##############Create network in Docker##############
**************Run the command**************
docker network create springapp


##############Start Microservices in Docker##############
Browse to Viafora folder open in terminal.
**************Run the command**************
export IP=(`hostname -I | awk '{print $1}'`)
IP=$IP docker-compose -f docker-compose.yml up -d

## Testing instructions
**************Test using Post Man**************
 Since we are using self signed certificate, switch off SSL verification check in postman 
 Go to postman Settings->General and switch off SSL certifictaion verification check
 
 refer poc doc to get more details 
 
 **GET request(s) can be tested by browser also.
 
## To run Junit Test Cases on local

**Run ConfigServerApplication

**Replace the following code in bootstrap.yml in the resources folder of UrlShoretner 

cloud:
    config:
      uri: http://config:8888
      
with 

cloud:
    config:
      uri: http://localhost:8888



## Usage

1- Spring boot 2.1.8.
2- Java 8
3- SSL Certificate


## License
[Rsystem](https://www.rsystems.com/)

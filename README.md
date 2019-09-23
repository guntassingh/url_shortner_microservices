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

##############Create network in Docker##############
**************Run the command**************
docker network create springapp

##############Start ELK on Docker##############
Browse to ELK folder 
**************Run the command**************
sysctl -w vm.max_map_count=262144 (NOTE*************)
docker-compose -f docker-compose-ELK.yml up

##############Start FileBeat on Host Machine(LINUX)##############
Install filebeat 

**************Run the command to install FileBeat**************
apt-get install apt-transport-https
apt update
apt install filebeat

**************Copy FileBeat configurations**************
Browse to FileBeat folder
Copy filebeat.yml to /etc/filebeat 

**************Start FileBeat Service**************
filebeat -e -c /etc/filebeat/filebeat.yml

##############Create Index in Kibana##############
Open link http://hostmachineip:5601 in a browser.
Click on Index Patterns
Click on Create index
Define Index Pattern as filebeat*
In the next page, click on @timestamp

##############Start Microservices in Docker##############
Browse to Viafora folder open in terminal.
**************Run the command**************
export IP=(`hostname -I | awk '{print $1}'`)
IP=$IP docker-compose -f docker-compose.yml up



## Usage

1- Spring boot 2.1.8.
2- Java 8
3- SSL Certificate


## License
[Rsystem](https://www.rsystems.com/)
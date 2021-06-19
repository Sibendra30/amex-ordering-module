# AMEX Ordering Module
*Author* : *Sibendra Pratap Singh ; sibendra30singh@gmail.com*

This is a Java-Springboot based project containing APIs to create order, view all orders and view  order by orderId.

## API Details
1. *POST /order* - This API will create the order and return s the order details with orderId, item details and pricing information.
2. *GET /order* - This API will return all the orders with item details and pricing information.
3. *GET /order/{orderId}* - This API will return the order with given order Id with item details and pricing information.

** Please refer to api.spec.yaml for more details.

## Pre-Configured Catalog Items:

| Item Id | Item Name |
| -- | -- |
|1001|Apple|
|1002|Orange|

## Pre-requisite to install, test and tun the project
1. Jdk 1.8+
2. Maven
3. Docker (Optional)

## How to build the project?
mvn clean install

## How to run unit test cases?
mvn clean test

## How to run the project? 
mvn spring-boot:run

## Build Docker Image
mvn clean install
docker build . -t amex-ordering-module:1.0

## Run docker image
docker run -p 8082:8082 amex-ordering-module:1.0
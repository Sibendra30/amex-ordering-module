# AMEX Ordering Module
*Author* : *Sibendra Pratap Singh ; sibendra30singh@gmail.com*

This is a Java-Springboot based project containing APIs to create order, view all orders and view  order by orderId.

## API Details
1. *POST /order* - This API will create the order and return s the order details with orderId, item details and pricing information.
Sample Request Body:
`{"customerId":"1234","items":[{"itemId":1001,"qty":5}]}`

Sample Response Body:
`{"id":10000,"customerId":1234,"items":[{"itemId":1001,"itemName":"Apple","qty":5,"amount":1.8}],"totalAmount":1.8}`

2. *GET /order* - This API will return all the orders with item details and pricing information.
Sample Response Body:
`[{"id":10000,"customerId":1234,"items":[{"itemId":1001,"itemName":"Apple","qty":5,"amount":1.8}],"totalAmount":1.8},{"id":10001,"customerId":1234,"items":[{"itemId":1001,"itemName":"Apple","qty":7,"amount":2.4}],"totalAmount":2.4}]`
3. *GET /order/{orderId}* - This API will return the order with given order Id with item details and pricing information.
Sample Response Body:
`{"id":10000,"customerId":1234,"items":[{"itemId":1001,"itemName":"Apple","qty":5,"amount":1.8}],"totalAmount":1.8}`

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
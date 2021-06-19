# AMEX Ordering Module

This is a Java-Springboot based project containing APIs to create order, view all orders and view  order by orderId.

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
docker build . -t amex-ordering-module:1.0

## Run docker image

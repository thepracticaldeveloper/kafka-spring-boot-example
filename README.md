# Spring Boot Kafka Example - The Practical Developer

## Basic configuration

This sample application shows how to use basic Spring Boot configuration to set up a producer to a topic with multiple partitions and a consumer group with three different consumers.

The full explanation is on The Practical Developer website: [Spring Boot and Kafka - Practical Configuration Examples](https://thepracticaldeveloper.com/2018/11/24/spring-boot-kafka-config/).

[![Kafka Configuration Example](img/kafka-configuration-example.jpg)](https://thepracticaldeveloper.com/2018/11/24/spring-boot-kafka-config/)

## Multiple serialization / deserialization formats

To illustrate the different configuration options, this application deserializes Kafka messages in three different ways:

* As a JSON to Java object.
* As a simple String (plain JSON).
* As a byte array.

## Docker compose

This code includes a `docker-compose.yml` file so you can use Docker Compose to start up Kafka, no installation needed.

## Did I help you?

Give a star to this project and/or [buy me a coffee](https://buymeacoff.ee/ZyLJNUR) 😄

# Spring Boot Kafka Example - The Practical Developer

## Basic configuration

This sample application shows how to use basic Spring Boot configuration to set up a producer to a topic with multiple partitions and a consumer group with three different consumers.

The complete post with details is on The Practical Developer website: [Spring Boot and Kafka - Practical Configuration Examples](https://thepracticaldeveloper.com/spring-boot-kafka-config/).

[![Kafka Configuration Example](img/kafka-configuration-example.jpg)](https://thepracticaldeveloper.com/spring-boot-kafka-config/)

## Multiple serialization / deserialization formats

To illustrate the different configuration options, this application deserializes Kafka messages in three different ways:

* As a JSON to Java object.
* As a simple String (plain JSON).
* As a byte array.

## Docker compose

This code includes a `docker-compose.yml` file, so you can use Docker Compose to start up Kafka without installing anything.

## Was it useful?

Give a star to this project, and consider some extra readings:

* [My practical book about building a microservices architecture from scratch](https://amzn.to/3nADn4q).
* [The Full Reactive Stack Guide](https://leanpub.com/full-reactive/).
* [The Practical Architecture Guide](https://leanpub.com/practical-software-architecture).

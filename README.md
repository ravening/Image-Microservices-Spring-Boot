# Image microservices using Java

This is a sample image microservice developed using Spring Boot Spring Cloud and Java

## Description

This contains an image service which stores the IMDB images.\
The gallery service fetches the details from image service.\
Authentication service provides authentication and authorization using JWT
Zuul service provides client side load balancing.

For more details, refer to https://medium.com/omarelgabrys-blog/microservices-with-spring-boot-intro-to-microservices-part-1-c0d24cd422c3

## Getting started

1 . Clone the respository

2 . Start the below services in order
```
Start the eureka server
cd eureka-server
./mvnw spring-boot:run


Start the image service

cd image-service
./mvnw spring-boot:run

You can start multiple instance of image service using
java -jar -Dserver.port=<> target/image-service-*.jar

Start the gallery service

cd gallery-service
./mvnw spring-boot:run

You can start the second instance of gallery service using
java -jar -Dserver.port=8300 target/gallery-service-*.jar

Start the auth service

cd auth-service
./mvnw spring-boot:run

Finally start the zuul-server

cd zuul-server
./mvnw spring-boot:run
```

3 . Once all the services are started successfully, make sure they are registered with\
    eureka server by navigating to
```
http://localhost:8761
```

4 . In order to access the service, first you need to get the JWT by providing your credentials\
    I have stored two users in the file ```UserDetailsServiceImpl.java```.\
    Send a POST request to below link with the following body
```
http://localhost:8762/auth
```

Body should be
```
{
    "username": "<one of the username from the above java file>"
    "password": "password for that user"
}
```

If you are authenticated successfully, you should receive a JWT in the header.\
Copy just the encrypted token without including "Bearer" keyword.

5 . Now to access the endpoints you need to pass this encrypted token in the header

6 . In postman create a new get request for the below end point
```
http://localhost:8762/gallery/1
```

Authorization should be "Bearer Token" and paste the above copied token in the "Token" field

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Authors
* **Rakesh Venkatesh** - [rakgenius](https://github.com/rakgenius)
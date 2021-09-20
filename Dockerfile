FROM openjdk:14-alpine

ADD target/Spring-shop.jar Spring-shop.jar

ENTRYPOINT ["java","-jar","Spring-shop.jar" ]

EXPOSE 8080
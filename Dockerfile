FROM ubuntu:latest

MAINTAINER Your Name "contact.mihir2213@gmail.com"

RUN apt-get update && apt-get install -y openjdk-8-jdk

ENV version=aws-db-usage
ENV dbuser=postgres
ENV dbpass=password321
ENV jdbcurl=jdbc:postgresql://pmadatabaseaws.c2gw39lro8tm.us-east-1.rds.amazonaws.com:5432/postgres


WORKDIR /usr/local/bin/

ADD target/pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]
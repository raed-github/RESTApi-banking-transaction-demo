FROM openjdk:17
WORKDIR /
ADD ./target/transaction-service-0.0.1-SNAPSHOT.jar transaction-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/transaction-service-0.0.1-SNAPSHOT.jar"]
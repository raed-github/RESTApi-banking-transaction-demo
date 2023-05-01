# Web application for banking transactions

# Table of contents
- [Introduction](#Introduction)
- [Summary](#Summary)
- [Design](#Design)
- [ObjectModel](#ObjectModel)
- [Representation](#Representation)
- [transaction-service](#transaction-service)
- [TechStack](#TechStack)
- [Maven](#Maven)
- [Testing](#Testing)
___
### Spring Boot Banking Transactions App

---
### Introduction
This Api  receive bank transaction data and store it into the mysql database

### Summary
This Api was created to process and execute transactions.

#### Requirements
- The API will expose an endpoint which accepts the transaction information (currency,
amount, source account id).
- all fields are required
- currency cannot be negative
- account number is between 7 to 11 characters

- ensure quality by providing necessary test coverage
- follow clean code principles
- ready to be deployed as a container
- having health checks, probes, etc

### Design
The application has 1 api inside a maven parent project
* transaction-service

```html
POST /api/transactions - processTransaction
```
#### ObjectModel
- Transaction

#### Representation
{
    "currency":"USD",
    "accountNumber":"123456789",
    "amount":11110.6
}

##### transaction-service

| API Name | HTTP Method | Model URIs | Status code | Description | 
| --------------- | --------------- | --------------- | --------------- | --------------- |
| transaction-service | POST| /api/transactions | 200 (CREATED)| Creating a transaction |
| transaction-service | POST| /api/transactions | 400 (Bad Request)| field validation error |


JUnit & integration, Acceptance tests coverage.

### TechStack

---
- Java 11
- Spring Boot
- Spring Data JPA
- Restful API
- Lombok
- MySql  
- Docker
- Docker compose
- JUnit 5
- kubernetes
- google jib plugin

### Prerequisites

---
- Maven
- Docker
- kubernetes
- Java 17
- Lombok
- mysql
- google jib plugin

### Run & Build

---
There are 2 ways of run & build the application.

#### Docker Compose

For docker compose usage, docker images already push to docker.io

You just need to run `docker-compose up` command
___
*$PORT: *
```ssh
$ cd  RESTApi-banking-transaction-demo
$ docker-compose up
```

#### Maven

___
*$PORT: *
```ssh
$ cd  c:/RESTApi-banking-transaction-demo/transaction-service
$ mvn clean install
$ mvn spring-boot:run

```

### Testing
- Integration and Acceptance testing are coverd for this api and can be executed successfully

- Postman Testing

test case 1: Create a correct Transaction Test Case
```ssh
{
    "currency":"USD",
    "accountNumber":"123456789",
    "amount":11110.6
}

{
    "message": "success"
}
```

test case 2: Create a Transaction with Negative amount should return validation exception message
```ssh
{
    "currency":"LBP",
    "accountNumber":"123456789",
    "amount":-2
}	

{
    "fieldErrors": [
        {
            "message": "transaction amount cannot be negative",
            "date": "2023-05-01"
        }
    ]
}
```

testcase 3:Create a Transaction with wrong accounnt number should return validation exception message
```ssh
{
    "currency":"LBP",
    "accountNumber":"12",
    "amount":11110.6
}

{
    "fieldErrors": [
        {
            "message": "accountNumber must consist of 7 to 11 numbers",
            "date": "2023-05-01"
        }
    ]
}
```

testcase 4:Create a Transaction with no amount should return validation exception message
```ssh
{
    "currency":"LBP",
    "accountNumber":"123456789"
}

{
    "fieldErrors": [
        {
            "message": "amount field is required",
            "date": "2023-05-01"
        }
    ]
}
```

testcase 5: Create a Transaction with no currency should return validation exception message
```ssh
request:
{
    "accountNumber":"123456789",
    "amount":11110.6
}

response:
{
    "fieldErrors": [
        {
            "message": "currency field is required",
            "date": "2023-05-01"
        }
    ]
}
```

test case 6: create a transaction without account id
```ssh
{
    "currency":"LBP",
    "amount":11110.6
}

{
    "fieldErrors": [
        {
            "message": "accountNumber id is required",
            "date": "2023-05-01"
        }
    ]
}
```

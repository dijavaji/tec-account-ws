# tec-account-ws

# Technical Interview Proposal

This is my solution for Technical Backend Test Here's an overview of the application structure:

## Problem Description
Creación de Api Rest “Aplication Programming  Interface” 
 
Manejar los verbos: Get, Post, Put, Push, delete
Persona 
•Implementar la clase persona con los siguientes datos: nombre, genero, edad, identificación, dirección, teléfono 
•Debe manera su clave primaria (PK)
Cliente 
•Cliente debe manejar una entidad, que herede de la clase persona. 
•Un cliente tiene:  clienteid, contraseña, estado. 
•El cliente debe tener una clave única. (PK) 
Cuenta. 
•Cuenta debe manejar una entidad
•Una cuenta tiene: número cuenta, tipo cuenta, saldo Inicial, estado. 
•Debe manejar su Clave única 
Movimientos 
•Movimientos debe manejar una entidad
•Un movimiento tiene: Fecha, tipo movimiento, valor, saldo 
•Debe manejar su Clave única 

## Solution Overview
For development used the following technologies.
Development Ide
Spring Tool Suite
Source control
• GIT
Database
• MySQL
Backend
•Java Spring boot
Test
Postman
Junit
Methodology
• Scrum


The tec-account-ws solution contains a project:

tec-account-ws
Is the backend project this project contains all the classes which are used to manipulate the logic business of application, necessary data model and components the rest-api type.

##Solution Software Design Document
Provides a description of software tec-account in terms of architecture of software with various components with specified functionality.
https://drive.google.com/file/d/1JAblUEzI6Sq3n4NkZnGU54ASqEZqsw1N/view?usp=sharing

## Available Scripts

In the project directory tec-account-ws , you can run:

##Data Base scripts
The database script, entities and schema data, in the route
/tec-account-ws/src/main/resources/dataModel


## Development server

### `mvn clean install`

Builds the app for production to the `target` folder.\
It correctly bundles jar in production mode and optimizes the build for the best performance.

### `mvn spring-boot:run`
Run Spring Boot applications Navigate to `http://127.0.0.1:8080/`. You have the nexts apis built for creating the operation.

`127.0.0.1:8080/api/accounts/`
`127.0.0.1:8080/api/customers/`
`127.0.0.1:8080/api/transactions/`

### `mvn test`

### `mvn -q test`

Launches the test runner in the interactive watch mode.\

Test input:

The postman test:
/tec-account-ws/src/main/resources/test

Execute and init the container
### `docker build --tag=tec-account-ws:latest .`

Run the container with image
### `docker run -p8888:8080 tec-account-ws:latest`

## Production server
Run docker compose aplication execute for init the containers

### `docker compose up`

### `docker compose up build`

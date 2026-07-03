# Airport System REST API

A highly relational, paginated Spring Boot backend engineered to manage complex airport operations and passenger manifests. This repository serves as the core data layer for the Airport Operations System.

## System Architecture

The application is built on a strict four-layer architecture to enforce separation of concerns and maintain scalable data flows:
* **Controllers:** Handle incoming HTTP requests, input validation, and HTTP status routing.
* **Services:** Contain the core business logic and transactional boundaries.
* **Repositories:** Interface with the database using Spring Data JPA for data persistence.
* **Entities:** Define the database schema and complex relational mappings.

## Relational Domain Model

The database schema models real-world aviation networks through specialized relationship mappings:
* **City & Airport:** A One-to-Many mapping linking metropolitan areas to specific terminal hubs.
* **Passenger & Aircraft:** A Many-to-Many mapping that tracks granular flight manifests and passenger transit histories.
* **Aircraft & Airport:** A Many-to-Many mapping logging fleet movements across global terminals.

## Core Features

* **Advanced Data Relationships:** Seamlessly handles multi-layered JSON serialization and deserialization across Many-to-Many entity boundaries.
* **Endpoint Pagination:** Implements Spring `Pageable` on heavy data endpoints to ensure optimal query performance and prevent payload bloating.
* **Decoupled Design:** Built specifically to be consumed by external clients, such as our companion Java CLI application.

## Development Methodology

This API was developed under a strict cross-functional structural constraint:
* **Inverted Ownership:** Developer A architects the API entities, while Developer B builds the client queries that consume them. This guarantees mutual system comprehension.
* **Mandatory Code Review:** Zero self-merging is permitted. All architectural changes require peer approval.
* **Pair-Programmed Logic:** Complex implementations like endpoint pagination are pair-programmed to eliminate knowledge silos.

## Installation & Build

**Prerequisites:**
* Java Development Kit (JDK 17+)
* Apache Maven
* Git

**Build Instructions:**

1. Clone the repository and checkout the development branch:
   git clone https://github.com/your-username/airport-api.git
   cd airport-api
   git checkout development

2. Build the project using Maven:
   mvn clean install

## Execution

You can run the Spring Boot application directly from your terminal. The server will initialize on the default port defined in your application properties.

```cmd
mvn spring-boot:run
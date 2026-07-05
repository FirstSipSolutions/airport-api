# Airport API

A Spring Boot REST API for an airport system — manages cities, airports, passengers, and aircraft, with full CRUD and relationships. Backed by MySQL.

## How to run

1. Have MySQL running and a database created (`Airport_Database`).
2. Set your DB username/password in `src/main/resources/application.properties`.
3. Run `RestServiceApplication`. Tables auto-create on startup.
4. API runs on `http://localhost:8080`.

## Endpoints

All support GET, POST, PUT, DELETE:
- `/api/cities`
- `/api/airports`
- `/api/passengers`
- `/api/aircraft`

Pagination + sorting: `/api/cities/paged?page=0&size=2&sort=name,asc`

## Relationships

- City → many Airports
- Passenger ↔ Aircraft (many-to-many)
- Aircraft ↔ Airport (many-to-many)

## Team

FirstSipSolutions — Chris & Justin, SD 15, 2026
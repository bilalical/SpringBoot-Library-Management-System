# Spring Boot Library Management System

A Spring Boot-based library management application for managing books, members, and borrowing/returning transactions. The project combines a Java backend with a lightweight static frontend and a MySQL database.

> Note: The actual Spring Boot application lives in the `library/` directory at the repository root.

## Features

- Manage books with CRUD operations
- Search books by title or keyword
- Filter books by availability and genre
- Manage library members and departments
- Track borrow and return transactions
- REST API endpoints for books, members, and issues
- MySQL persistence with Spring Data JPA
- OpenAPI/Swagger documentation
- Simple HTML/JavaScript frontend for library operations

## Tech Stack

- Java 21
- Spring Boot 3.5.11
- Spring Data JPA
- MySQL
- Maven
- Springdoc OpenAPI (Swagger UI)
- HTML/CSS/JavaScript

## Project Structure

```text
SpringBoot-Library-Management-System/
├── library/
│   ├── .mvn/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/project/library/
│   │   │   │   ├── controller/
│   │   │   │   ├── model/
│   │   │   │   ├── repository/
│   │   │   │   └── service/
│   │   │   └── resources/
│   │   │       ├── static/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   ├── mvnw
│   ├── mvnw.cmd
│   └── .gitignore
└── README.md
```

## Main Modules

### Backend

The backend is structured into the standard Spring Boot layers:

- `controller` — REST API endpoints
- `service` — business logic
- `repository` — database access via JPA repositories
- `model` — entities such as `Book`, `Member`, and `Issue`

### Frontend

Static pages are located under:

- `library/src/main/resources/static/index.html`
- `library/src/main/resources/static/book.html`
- `library/src/main/resources/static/member.html`

These pages interact with the backend APIs using JavaScript files in the same folder.

## API Overview

### Books

- `GET /api/books` — fetch all books
- `GET /api/books/{id}` — fetch a book by ID
- `GET /api/books/search?query=...` — search books
- `GET /api/books/available` — fetch available books
- `GET /api/books/genre/{genre}` — fetch books by genre
- `POST /api/books` — add a book
- `PUT /api/books/{id}` — update book
- `PUT /api/books/{id}/toggle` — toggle availability
- `DELETE /api/books/{id}` — delete a book

### Members

- `GET /api/members` — fetch all members
- `GET /api/members/{id}` — fetch a member by ID
- `GET /api/members/search?query=...` — search members
- `GET /api/members/dept/{department}` — fetch by department
- `POST /api/members` — add a member
- `PUT /api/members/{id}` — update member
- `DELETE /api/members/{id}` — delete member

### Issues / Transactions

- `GET /api/issue` — fetch all issues
- `GET /api/issue/member/{id}` — issues by member
- `GET /api/issue/book/{id}` — issues by book
- `POST /api/issue/{memberID}/{bookID}` — borrow a book
- `PUT /api/issue/{id}` — return a book

## Database Configuration

This project expects a MySQL database named `lib`.

Update the datasource credentials in:

`library/src/main/resources/application.properties`

Example:

```properties
spring.application.name=library
spring.datasource.url = jdbc:mysql://localhost:3306/lib
spring.datasource.username = root
spring.datasource.password = your_password
spring.jpa.hibernate.ddl-auto = update
```

## Prerequisites

Before running the project, ensure you have:

- JDK 21+
- Maven
- MySQL installed and running

## Run Locally

```bash
cd library
./mvnw spring-boot:run
```

Then open:

- Frontend: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Sample MySQL Setup

```sql
CREATE DATABASE lib;
```

If needed, create or grant the appropriate user access before running the app.

## Notes

- The project uses Spring Boot auto-configuration for the application setup.
- The frontend is deliberately simple and is suitable for learning or demo purposes.
- The application is designed for a library workflow: adding books, adding members, issuing books, and returning them.

## Future Improvements

- Add authentication and authorization
- Add book categories and finer reporting
- Add front-end validation and user-friendly forms
- Add unit and integration tests
- Improve error handling and security

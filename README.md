# 🎤 Spring Boot Backend Project for Concert Ticket Store

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Hibernate](https://img.shields.io/badge/Hibernate-5.x-59666C?style=flat&logo=hibernate)](https://hibernate.org/)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-6DB33F?style=flat&logo=spring-security)](https://spring.io/projects/spring-security)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

This project is a robust backend built with Spring Boot, leveraging Hibernate for ORM and Spring Security for authentication and authorization.

## 📋 Table of Contents

1. [Main Features](#-main-features)
2. [Technologies Used](#-technologies-used)
3. [Quick Start](#-quick-start)
4. [Project Configuration](#-project-configuration)
5. [Database Schema](#-database-schema)
6. [Security and Authentication](#-security-and-authentication)
7. [API Endpoints](#-api-endpoints)
8. [Exception Handling](#-exception-handling)
9. [Contributing](#-contributing)
10. [License](#-license)


## 🚀 Main Features

- 🔐 JWT-based authentication with access and refresh tokens
- 📊 RESTful API endpoints for concert ticket booking operations
- 🗃️ Database integration with Hibernate ORM and MySQL
- 📈 Efficient data querying with lazy loading
- 🔄 Asynchronous request processing
- 📝 Comprehensive API documentation with Swagger UI
- 🛡️ Robust exception handling and logging

## 🛠 Technologies Used

<table>
  <tr>
    <th>Category</th>
    <th>Technologies</th>
  </tr>
  <tr>
    <td>Core</td>
    <td>
      <img src="https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat&logo=spring-boot" alt="Spring Boot">
      <img src="https://img.shields.io/badge/Java-17-007396?style=flat&logo=java" alt="Java 17">
    </td>
  </tr>
  <tr>
    <td>Security</td>
    <td>
      <img src="https://img.shields.io/badge/Spring%20Security-6.x-6DB33F?style=flat&logo=spring-security" alt="Spring Security">
      <img src="https://img.shields.io/badge/JWT-0.11.x-000000?style=flat&logo=json-web-tokens" alt="JWT">
    </td>
  </tr>
  <tr>
    <td>Database</td>
    <td>
      <img src="https://img.shields.io/badge/Hibernate-5.x-59666C?style=flat&logo=hibernate" alt="Hibernate">
      <img src="https://img.shields.io/badge/MySQL-8.x-4479A1?style=flat&logo=mysql" alt="MySQL">
    </td>
  </tr>
  <tr>
    <td>API Documentation</td>
    <td>
      <img src="https://img.shields.io/badge/Springdoc-OpenAPI-6BA539?style=flat&logo=openapi-initiative" alt="Springdoc OpenAPI">
    </td>
  </tr>
</table>

## 🚀 Quick Start

```bash
# Clone the repository
git clone https://github.com/saraobialero/SpringBoot3.3.1-HibernateDB_Concerts-TicketStore-App.git
cd SpringBoot3.3.1-HibernateDB_Concerts-TicketStore-App

# Configure application properties (see Project Configuration section)

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

## ⚙️ Project Configuration

Before running the application, ensure you have set up the following configuration files:

1. `application-dev.properties`, `application-prod.properties`, `application-test.properties`:
    - Configure database connection
    - Set JWT secret and token expiration times
    - Configure logging levels

2. `logback-spring.xml`:
    - Configure detailed logging behavior for different profiles

Example `application-dev.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db?createDatabaseIfNotExist=true&useUnicode=yes&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=create-drop

application.security.jwt-secret-key=your_secret_key
application.security.jwt-expired-access-token=900000
application.security.jwt-expired-refresh-token=86400000
```

## 🗄 Database Schema

The project uses the following main entities:

- User: OneToMany relationship with Order
- Reply: OneToOne relationship with Ticket
- Ticket:
    - OneToOne with Reply
    - ManyToOne with Seller
    - OneToMany with Order
- Order:
    - ManyToOne with User
    - ManyToOne with Ticket
- Seller: OneToMany with Ticket

Notable changes from the original schema:
- Use of `BigDecimal` for prices
- `LocalDate` for dates
- Integer IDs with auto-increment
- `PaymentType` moved to the Order entity

## 🔒 Security and Authentication

- JWT-based authentication with access and refresh tokens
- Tokens are automatically validated by a filter
- In Swagger UI, simply insert the token in the "Authorize" section to authenticate requests

To access protected endpoints for example in Postman, include the JWT token in the Authorization header:

```
Authorization: Bearer <your_token_here>
```

## 🌐 API Endpoints

Protected endpoints are defined in `ApiUtils.java`. Some key endpoints include:

- `/api/auth/login`: User login
- `/api/auth/signup`: User registration
- `/api/tickets/`: View available tickets
- `/api/orders/`: Manage orders

For a complete list of endpoints, refer to the Swagger documentation at `http://localhost:8080/swagger-ui.html` when the application is running.


## ❗ Exception Handling

The project includes a comprehensive exception handling system:

- Custom exceptions for various scenarios (e.g., `UserException`, `OrderException`)
- Global exception handler (`ExceptionHandlerConfig`) to manage and return appropriate error responses
- Detailed logging of exceptions for troubleshooting

## 🤝 Contributing

Contributions are welcome! Please ensure that your contributions maintain the existing code structure and follow the established patterns for exception handling and API design.

## 📄 License

This project is licensed under the [MIT License](LICENSE).
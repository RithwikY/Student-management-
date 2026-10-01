# Student Management System

A beginner-friendly Java full-stack application using:

- HTML
- CSS
- JavaScript
- Java Servlets
- JDBC
- MySQL
- Maven

## Architecture

Frontend → Java Servlet → DAO → JDBC → MySQL

## Requirements

- JDK 17+
- Maven
- MySQL 8+
- Apache Tomcat 10.1+

## Database setup

Run:

```sql
SOURCE database/studentdb.sql;
```

Then edit:

`src/main/java/com/student/util/DBConnection.java`

Change:

```java
private static final String USER = "root";
private static final String PASSWORD = "password";
```

to your MySQL username and password.

## Build

```bash
mvn clean package
```

The WAR file will be created in:

```text
target/student-management.war
```

Deploy that WAR to Tomcat 10.1+.

Open:

```text
http://localhost:8080/student-management/
```

## GitHub

Do not commit database passwords or other secrets to a public repository. For a real deployment, use environment variables or a secrets manager.

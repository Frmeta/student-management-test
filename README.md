Demo
====

A small Gradle-based demo project with Student Management System featuring MongoDB, Redis, and Kafka integration.

Quick start
-----------
Prerequisites: Java 11+ and the Gradle wrapper (included) & Docker

Start services:
  docker compose up -d

Build (Windows):
  .\gradlew.bat build

Build (macOS/Linux):
  ./gradlew build

API Endpoints
-------------

### Students API (/students)
- **POST /students** - Create a new student
- **GET /students** - Get all students
- **GET /students/{id}** - Get student by ID (cached from Redis, falls back to MongoDB)
- **PUT /students/{id}** - Update student details
- **DELETE /students/{id}** - Delete a student
*Note: Student operations publish Kafka events and use Redis caching (10 min TTL)*

### Subjects API (/subjects)
- **POST /subjects** - Create a new subject
- **GET /subjects** - Get all subjects
- **GET /subjects/{id}** - Get subject by ID
- **PUT /subjects/{id}** - Update subject details
- **DELETE /subjects/{id}** - Delete a subject

### Enrollments API (/enrollments)
- **POST /enrollments** - Create a new enrollment
- **GET /enrollments** - Get all enrollments
- **GET /enrollments/{id}** - Get enrollment by ID
- **GET /enrollments/student/{studentId}** - Get all enrollments for a student
- **GET /enrollments/subject/{subjectId}** - Get all enrollments for a subject
- **PUT /enrollments/{id}** - Update enrollment details
- **DELETE /enrollments/{id}** - Delete an enrollment
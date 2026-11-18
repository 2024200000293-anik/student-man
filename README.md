# Student Management System (Spring Boot + Thymeleaf + HTML/CSS)

A small CRUD app for learning Spring Boot: add, list, search, edit and delete students.

## Run it
Needs Java 17+ and Maven (or open the folder as a Maven project in IntelliJ / VS Code / Eclipse).

    mvn spring-boot:run

Then open http://localhost:8080
Database viewer: http://localhost:8080/h2-console  (JDBC URL: jdbc:h2:mem:studentdb, user: sa, no password)

## How a request flows
Browser -> Controller -> Service -> Repository -> Database
          (returns an HTML template name) <- (Thymeleaf fills it with data)

| Layer | File | Job |
|---|---|---|
| Model | model/Student.java | Table + validation rules |
| Repository | repository/StudentRepository.java | Database access (no SQL needed) |
| Service | service/StudentService.java | Business logic |
| Controller | controller/StudentController.java | Handles URLs, picks the HTML page |
| View | templates/*.html | Pages (Thymeleaf) |
| Style | static/css/style.css | Look and feel |

## URLs
| Method | URL | What it does |
|---|---|---|
| GET | /students | List (add ?keyword=name to search) |
| GET | /students/new | Empty add form |
| GET | /students/{id}/edit | Edit form |
| POST | /students/save | Create or update |
| POST | /students/{id}/delete | Delete |

## Try next
- Add a `phone` field to Student, the form and the table.
- Add a `GPA` field with @DecimalMin / @DecimalMax validation.
- Sort the list by name: `repository.findAll(Sort.by("name"))`.
- Switch from H2 to MySQL: add the MySQL driver and change the `spring.datasource.*` lines.

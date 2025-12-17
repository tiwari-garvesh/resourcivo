# Resourcivo Backend Testing Guide

## Overview

This guide provides comprehensive testing options for the Resourcivo Spring Boot backend project.

---

## 1. Project Setup & Build

### Clean and Build
```bash
# Clean the project
mvn clean

# Compile the project
mvn compile

# Package the application (creates WAR file)
mvn package

# Skip tests during build (faster)
mvn package -DskipTests
```

### Run the Application
```bash
# Run with Maven
mvn spring-boot:run

# Or run the WAR file directly
java -jar target/Resourcivo-0.0.1-SNAPSHOT.war
```

The application will start on **http://localhost:8080** (default port).

---

## 2. Unit Testing

### Run All Tests
```bash
# Run all unit tests
mvn test

# Run tests with coverage report
mvn test jacoco:report

# Run specific test class
mvn test -Dtest=FacultyServiceTest

# Run specific test method
mvn test -Dtest=FacultyServiceTest#testCreateFaculty
```

### Create Unit Tests

Create test files in `src/test/java/com/project/resourcivo/`:

**Example: Service Test**
```java
@SpringBootTest
class FacultyServiceTest {
    
    @Autowired
    private IFacultyService facultyService;
    
    @Test
    void testCreateFaculty() {
        FacultyCreateDTO dto = new FacultyCreateDTO();
        // Set DTO fields
        
        FacultyResponseDTO response = facultyService.createFromDto(dto);
        
        assertNotNull(response);
        assertNotNull(response.getId());
    }
}
```

---

## 3. Integration Testing

### Database Integration Tests

Use `@DataJpaTest` for repository testing:

```java
@DataJpaTest
class FacultyRepositoryTest {
    
    @Autowired
    private FacultyRepository facultyRepository;
    
    @Test
    void testSaveFaculty() {
        Faculty faculty = new Faculty();
        // Set fields
        
        Faculty saved = facultyRepository.save(faculty);
        
        assertNotNull(saved.getId());
    }
}
```

### Full Application Context Tests

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class FacultyControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void testGetAllFaculty() throws Exception {
        mockMvc.perform(get("/api/faculty"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
```

---

## 4. API Testing with Postman

### Setup Postman Collection

1. **Base URL**: `http://localhost:8080`
2. **Create requests for each endpoint**

### Example Endpoints

#### Faculty Endpoints

**GET All Faculty**
```
GET http://localhost:8080/api/faculty
```

**GET Faculty by ID**
```
GET http://localhost:8080/api/faculty/{id}
```

**POST Create Faculty**
```
POST http://localhost:8080/api/faculty
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "1980-01-15",
  "gender": "Male",
  "dateOfJoining": "2020-01-01",
  "level": "Professor",
  "yearOfExperience": 15
}
```

**PUT Update Faculty**
```
PUT http://localhost:8080/api/faculty/{id}
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Smith"
}
```

**DELETE Faculty**
```
DELETE http://localhost:8080/api/faculty/{id}
```

#### Student Endpoints

**POST Create Student**
```
POST http://localhost:8080/api/students
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Doe",
  "dateOfBirth": "2000-05-20",
  "gender": "Female",
  "dateOfJoining": "2023-08-01"
}
```

#### Subject Endpoints

**GET All Subjects**
```
GET http://localhost:8080/api/subjects
```

**POST Create Subject**
```
POST http://localhost:8080/api/subjects
Content-Type: application/json

{
  "name": "Computer Science",
  "code": "CS101"
}
```

---

## 5. API Testing with cURL

### Faculty API Examples

```bash
# GET all faculty
curl http://localhost:8080/api/faculty

# GET faculty by ID
curl http://localhost:8080/api/faculty/1

# POST create faculty
curl -X POST http://localhost:8080/api/faculty \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "dateOfBirth": "1980-01-15",
    "gender": "Male",
    "dateOfJoining": "2020-01-01",
    "level": "Professor",
    "yearOfExperience": 15
  }'

# PUT update faculty
curl -X PUT http://localhost:8080/api/faculty/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Smith"
  }'

# DELETE faculty
curl -X DELETE http://localhost:8080/api/faculty/1
```

---

## 6. Database Testing

### H2 Console (If using H2 database)

1. Add to `application.properties`:
```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

2. Access at: **http://localhost:8080/h2-console**

### MySQL/PostgreSQL Testing

```bash
# Connect to database
mysql -u root -p resourcivo_db

# Or for PostgreSQL
psql -U postgres -d resourcivo_db

# Check tables
SHOW TABLES;

# Query data
SELECT * FROM faculty;
SELECT * FROM student;
SELECT * FROM subject;
```

---

## 7. Performance Testing

### Apache JMeter

1. Download JMeter
2. Create Test Plan
3. Add Thread Group (simulate users)
4. Add HTTP Request samplers for your endpoints
5. Run and analyze results

### Simple Load Test with cURL

```bash
# Run 100 requests
for i in {1..100}; do
  curl http://localhost:8080/api/faculty &
done
wait
```

---

## 8. Swagger/OpenAPI Documentation

### Add Swagger Dependencies

Add to `pom.xml`:
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

### Access Swagger UI

After adding the dependency and restarting:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs

---

## 9. Testing Checklist

### Before Testing
- [ ] Database is running and accessible
- [ ] `application.properties` configured correctly
- [ ] Project compiles successfully (`mvn compile`)
- [ ] Application starts without errors

### Unit Tests
- [ ] Service layer tests written
- [ ] Repository tests written
- [ ] Mapper tests written
- [ ] All tests pass (`mvn test`)

### Integration Tests
- [ ] Controller integration tests written
- [ ] Database integration tests pass
- [ ] End-to-end workflows tested

### API Tests
- [ ] All CRUD operations tested
- [ ] Error handling tested (404, 400, 500)
- [ ] Edge cases tested (null values, invalid data)
- [ ] Search/filter endpoints tested

### Performance
- [ ] Load testing completed
- [ ] Response times acceptable
- [ ] No memory leaks

---

## 10. Common Testing Commands

```bash
# Full build and test
mvn clean install

# Run only integration tests
mvn verify -DskipUnitTests

# Generate test coverage report
mvn clean test jacoco:report
# View report at: target/site/jacoco/index.html

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=test

# Debug mode
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

---

## 11. Recommended Testing Tools

1. **Postman** - API testing and documentation
2. **JUnit 5** - Unit testing framework
3. **Mockito** - Mocking framework
4. **RestAssured** - REST API testing
5. **TestContainers** - Database integration testing
6. **JMeter** - Performance testing
7. **SonarQube** - Code quality analysis

---

## 12. Next Steps

1. **Write Unit Tests** for all services
2. **Create Integration Tests** for controllers
3. **Set up Postman Collection** with all endpoints
4. **Add Swagger Documentation** for API exploration
5. **Configure CI/CD** pipeline for automated testing
6. **Add Test Coverage** reporting (aim for >80%)

---

## Troubleshooting

### Application Won't Start
- Check database connection in `application.properties`
- Verify port 8080 is not in use
- Check logs in console for errors

### Tests Failing
- Ensure test database is configured
- Check for missing dependencies
- Verify test data setup

### API Returns 404
- Check controller mapping paths
- Verify application context path
- Ensure endpoints are registered

---

**Happy Testing! 🚀**

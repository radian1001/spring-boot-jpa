# College Management System — Spring Data JPA Practice Project

## Goal

Build a complete Spring Boot + Spring Data JPA project using:

* Entity relationships
* Bidirectional mappings
* DTOs
* Pagination
* Sorting
* Projections
* Specifications / dynamic filtering
* JPQL
* Native queries
* Cascading
* Fetch strategies
* OrderBy
* Transactions
* Validation
* Exception handling
* Custom responses
* REST APIs

This project is designed specifically to polish advanced JPA skills.

---

# Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* MySQL/PostgreSQL
* Lombok
* MapStruct (optional)
* Validation API
* Spring Web
* Hibernate

---

# Entities

## 1. Student

### Fields

* id
* name
* email
* age
* createdAt
* status (ACTIVE/INACTIVE)

### Relationships

* ManyToMany -> Subject
* ManyToMany -> Professor
* OneToOne -> AdmissionRecord

---

## 2. Professor

### Fields

* id
* name
* experience
* salary

### Relationships

* ManyToOne -> Subject
* ManyToMany -> Student

---

## 3. Subject

### Fields

* id
* title
* credits
* department

### Relationships

* OneToMany -> Professor
* ManyToMany -> Student

---

## 4. AdmissionRecord

### Fields

* id
* fees
* admissionDate
* scholarship

### Relationships

* OneToOne -> Student

---

# Relationship Requirements

## Student ↔ Subject

### Type

ManyToMany

### Features to Practice

* Join table
* Cascading
* Bidirectional mapping
* Helper methods

---

## Student ↔ Professor

### Type

ManyToMany

### Features to Practice

* Owning side
* Inverse side
* Manual synchronization
* Transaction handling

---

## Professor ↔ Subject

### Type

ManyToOne / OneToMany

### Features to Practice

* Foreign keys
* Lazy loading
* Fetch joins

---

## Student ↔ AdmissionRecord

### Type

OneToOne

### Features to Practice

* Cascading ALL
* Orphan removal
* Parent-child lifecycle

---

# Project Structure

```text
src/main/java
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 ├── mapper
 ├── exception
 ├── specification
 ├── projection
 └── config
```

---

# APIs To Implement

# STUDENT APIs

## Create Student

```http
POST /api/students
```

### Features

* Validation
* DTO mapping
* Cascade persist
* AdmissionRecord auto-save

---

## Get All Students

```http
GET /api/students
```

### Add Support For

* Pagination
* Sorting
* Dynamic filtering

### Example

```http
GET /api/students?page=0&size=5&sort=name,asc
```

---

## Get Student By Id

```http
GET /api/students/{id}
```

### Practice

* Lazy loading
* DTO conversion
* Exception handling

---

## Update Student

```http
PUT /api/students/{id}
```

### Practice

* Merge behavior
* Transactional updates

---

## Delete Student

```http
DELETE /api/students/{id}
```

### Practice

* Cascading
* ManyToMany unlinking
* Orphan removal

---

## Assign Subject To Student

```http
POST /api/students/{studentId}/subjects/{subjectId}
```

### Practice

* ManyToMany synchronization
* Bidirectional helper methods

---

## Assign Professor To Student

```http
POST /api/students/{studentId}/professors/{professorId}
```

### Practice

* Join table management
* Transactional relationship updates

---

## Remove Subject From Student

```http
DELETE /api/students/{studentId}/subjects/{subjectId}
```

---

## Remove Professor From Student

```http
DELETE /api/students/{studentId}/professors/{professorId}
```

---

# PROFESSOR APIs

## Create Professor

```http
POST /api/professors
```

---

## Assign Subject To Professor

```http
POST /api/professors/{professorId}/subjects/{subjectId}
```

### Practice

* ManyToOne relationship
* FK management

---

## Get Professors By Subject

```http
GET /api/professors/by-subject/{subjectId}
```

### Practice

* Derived query methods
* JPQL

---

## Get Top Experienced Professors

```http
GET /api/professors/top-experienced
```

### Practice

* Sorting
* Pageable
* Custom queries

---

# SUBJECT APIs

## Create Subject

```http
POST /api/subjects
```

---

## Get Subjects With Pagination

```http
GET /api/subjects?page=0&size=10
```

---

## Search Subject By Title

```http
GET /api/subjects/search?title=Math
```

### Practice

* Containing
* Ignore case
* Specifications

---

## Get Students Enrolled In Subject

```http
GET /api/subjects/{id}/students
```

---

# ADMISSION APIs

## Create Admission Record

```http
POST /api/admissions
```

---

## Get Student Admission Details

```http
GET /api/admissions/student/{studentId}
```

---

# Pagination Practice

## Repository

```java
Page<Student> findAll(Pageable pageable);
```

---

## API Examples

```http
GET /api/students?page=0&size=5
```

```http
GET /api/students?page=1&size=10&sort=name,asc
```

```http
GET /api/students?page=0&size=5&sort=createdAt,desc
```

---

# Sorting Practice

## Multiple Sorting

```http
GET /api/students?sort=name,asc&sort=age,desc
```

---

# Projection Practice

# Interface Projection

## StudentNameProjection

```java
public interface StudentNameProjection {
    Long getId();
    String getName();
}
```

---

## Repository

```java
List<StudentNameProjection> findByStatus(StudentStatus status);
```

---

# DTO Projection

## StudentSummaryDto

```java
public record StudentSummaryDto(
    Long id,
    String name,
    String email
) {}
```

---

## JPQL DTO Projection

```java
@Query("""
SELECT new com.project.dto.StudentSummaryDto(
    s.id,
    s.name,
    s.email
)
FROM Student s
""")
List<StudentSummaryDto> getStudentSummary();
```

---

# Query Practice

# Derived Queries

```java
findByNameContainingIgnoreCase()
findByAgeGreaterThan()
findByStatus()
findBySubjectListTitle()
findByProfessorListNameContaining()
```

---

# JPQL Queries

```java
@Query("SELECT s FROM Student s WHERE s.age > :age")
```

---

# Native Queries

```java
@Query(value = "SELECT * FROM student", nativeQuery = true)
```

---

# Specifications Practice

## Filters

* name
* age
* subject
* professor
* status

### Example

```http
GET /api/students/filter?name=john&age=20
```

---

# Fetch Strategies

## Practice

* Lazy loading
* Eager loading
* N+1 problem
* Fetch joins
* EntityGraph

---

# OrderBy Practice

## Example

```java
@OneToMany(mappedBy = "subject")
@OrderBy("experience DESC")
private List<Professor> professorList;
```

---

# Cascading Practice

## Use Cases

### Student -> AdmissionRecord

```java
cascade = CascadeType.ALL
orphanRemoval = true
```

### Student -> Subject

Try:

```java
CascadeType.PERSIST
CascadeType.MERGE
```

Then observe behavior.

---

# Transaction Practice

## Use @Transactional In

* Assign professor
* Assign subject
* Remove mappings
* Bulk updates

---

# Exception Handling

## Create

* ResourceNotFoundException
* DuplicateResourceException
* GlobalExceptionHandler

---

# Validation Practice

## Use

* @NotBlank
* @Email
* @Min
* @Max
* @Positive

---

# Advanced APIs To Build

## Get Students Along With Subjects And Professors

```http
GET /api/students/full-details
```

### Practice

* DTO nesting
* Fetch joins
* Avoid infinite recursion

---

## Bulk Assign Subjects

```http
POST /api/students/{id}/bulk-subjects
```

---

## Transfer Student To Another Professor

```http
PUT /api/students/{studentId}/transfer-professor/{newProfessorId}
```

---

## Get Subject Wise Student Count

```http
GET /api/subjects/student-count
```

### Practice

* GROUP BY
* Aggregation
* Projection

---

# Important Concepts To Practice

## JPA Lifecycle

* transient
* managed
* detached
* removed

---

## Persistence Context

* dirty checking
* flushing
* transactional boundaries

---

## Avoid Infinite Recursion

Practice:

* @JsonManagedReference
* @JsonBackReference
* @JsonIgnore
* DTOs

---

# Bonus Challenges

## Add Attendance Entity

* Student
* Subject
* AttendanceDate
* Status

---

## Add Department Entity

* One department
* Many subjects
* Many professors

---

## Add Audit Fields

Use:

* @CreatedDate
* @LastModifiedDate

---

# Final Objective

By completing this project, you should become comfortable with:

* Entity relationships
* Owning vs inverse side
* Cascading
* Transaction management
* Query optimization
* Pagination
* Sorting
* Specifications
* DTOs
* Projections
* JPQL
* Native queries
* Fetch strategies
* Real-world JPA architecture

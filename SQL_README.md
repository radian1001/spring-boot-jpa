# SQL Seed Data

The app now inserts sample data automatically on startup through [data.sql](C:/Users/KIIT/Downloads/JpaTuts/JpaTuts/src/main/resources/data.sql).

## What Gets Inserted

- `subject`: 3 rows
- `student`: 3 rows
- `professor`: 3 rows
- `admission_list`: 3 rows
- `student_subject`: sample many-to-many mappings
- `student_professor`: sample many-to-many mappings

## How It Works

- `spring.sql.init.mode=always` enables SQL initialization for PostgreSQL.
- `spring.jpa.defer-datasource-initialization=true` makes Hibernate create or update tables before `data.sql` runs.
- The inserts are idempotent. Restarting the app will not duplicate the same seeded rows.

## Sample Rows

### Subject

```sql
INSERT INTO subject (id, name) VALUES
(1, 'Mathematics'),
(2, 'Physics'),
(3, 'Computer Science');
```

### Student

```sql
INSERT INTO student (id, name) VALUES
(1, 'Aman Sharma'),
(2, 'Priya Singh'),
(3, 'Rahul Das');
```

### Professor

```sql
INSERT INTO professor (id, title, subject_id) VALUES
(1, 'Assistant Professor', 1),
(2, 'Associate Professor', 2),
(3, 'Head of Department', 3);
```

### Admission List

```sql
INSERT INTO admission_list (id, fees, student_id) VALUES
(1, 45000, 1),
(2, 52000, 2),
(3, 61000, 3);
```

## To Insert Through The App

1. Start PostgreSQL with the database from [application.yaml](C:/Users/KIIT/Downloads/JpaTuts/JpaTuts/src/main/resources/application.yaml).
2. Run the Spring Boot app.
3. Spring will execute `data.sql` automatically during startup.

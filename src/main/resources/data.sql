INSERT INTO subject (id, name)
SELECT 1, 'Mathematics'
WHERE NOT EXISTS (SELECT 1 FROM subject WHERE id = 1);

INSERT INTO subject (id, name)
SELECT 2, 'Physics'
WHERE NOT EXISTS (SELECT 1 FROM subject WHERE id = 2);

INSERT INTO subject (id, name)
SELECT 3, 'Computer Science'
WHERE NOT EXISTS (SELECT 1 FROM subject WHERE id = 3);

INSERT INTO student (id, name)
SELECT 1, 'Aman Sharma'
WHERE NOT EXISTS (SELECT 1 FROM student WHERE id = 1);

INSERT INTO student (id, name)
SELECT 2, 'Priya Singh'
WHERE NOT EXISTS (SELECT 1 FROM student WHERE id = 2);

INSERT INTO student (id, name)
SELECT 3, 'Rahul Das'
WHERE NOT EXISTS (SELECT 1 FROM student WHERE id = 3);

INSERT INTO professor (id, title, subject_id)
SELECT 1, 'Assistant Professor', 1
WHERE NOT EXISTS (SELECT 1 FROM professor WHERE id = 1);

INSERT INTO professor (id, title, subject_id)
SELECT 2, 'Associate Professor', 2
WHERE NOT EXISTS (SELECT 1 FROM professor WHERE id = 2);

INSERT INTO professor (id, title, subject_id)
SELECT 3, 'Head of Department', 3
WHERE NOT EXISTS (SELECT 1 FROM professor WHERE id = 3);

INSERT INTO admission_list (id, fees, student_id)
SELECT 1, 45000, 1
WHERE NOT EXISTS (SELECT 1 FROM admission_list WHERE id = 1);

INSERT INTO admission_list (id, fees, student_id)
SELECT 2, 52000, 2
WHERE NOT EXISTS (SELECT 1 FROM admission_list WHERE id = 2);

INSERT INTO admission_list (id, fees, student_id)
SELECT 3, 61000, 3
WHERE NOT EXISTS (SELECT 1 FROM admission_list WHERE id = 3);

INSERT INTO student_subject (subject_id, student_id)
SELECT 1, 1
WHERE NOT EXISTS (
    SELECT 1 FROM student_subject WHERE subject_id = 1 AND student_id = 1
);

INSERT INTO student_subject (subject_id, student_id)
SELECT 2, 1
WHERE NOT EXISTS (
    SELECT 1 FROM student_subject WHERE subject_id = 2 AND student_id = 1
);

INSERT INTO student_subject (subject_id, student_id)
SELECT 2, 2
WHERE NOT EXISTS (
    SELECT 1 FROM student_subject WHERE subject_id = 2 AND student_id = 2
);

INSERT INTO student_subject (subject_id, student_id)
SELECT 3, 2
WHERE NOT EXISTS (
    SELECT 1 FROM student_subject WHERE subject_id = 3 AND student_id = 2
);

INSERT INTO student_subject (subject_id, student_id)
SELECT 1, 3
WHERE NOT EXISTS (
    SELECT 1 FROM student_subject WHERE subject_id = 1 AND student_id = 3
);

INSERT INTO student_subject (subject_id, student_id)
SELECT 3, 3
WHERE NOT EXISTS (
    SELECT 1 FROM student_subject WHERE subject_id = 3 AND student_id = 3
);

INSERT INTO student_professor (student_id, professor_id)
SELECT 1, 1
WHERE NOT EXISTS (
    SELECT 1 FROM student_professor WHERE student_id = 1 AND professor_id = 1
);

INSERT INTO student_professor (student_id, professor_id)
SELECT 1, 2
WHERE NOT EXISTS (
    SELECT 1 FROM student_professor WHERE student_id = 1 AND professor_id = 2
);

INSERT INTO student_professor (student_id, professor_id)
SELECT 2, 2
WHERE NOT EXISTS (
    SELECT 1 FROM student_professor WHERE student_id = 2 AND professor_id = 2
);

INSERT INTO student_professor (student_id, professor_id)
SELECT 2, 3
WHERE NOT EXISTS (
    SELECT 1 FROM student_professor WHERE student_id = 2 AND professor_id = 3
);

INSERT INTO student_professor (student_id, professor_id)
SELECT 3, 1
WHERE NOT EXISTS (
    SELECT 1 FROM student_professor WHERE student_id = 3 AND professor_id = 1
);

INSERT INTO student_professor (student_id, professor_id)
SELECT 3, 3
WHERE NOT EXISTS (
    SELECT 1 FROM student_professor WHERE student_id = 3 AND professor_id = 3
);

SELECT setval(pg_get_serial_sequence('student', 'id'), COALESCE((SELECT MAX(id) FROM student), 1), true);
SELECT setval(pg_get_serial_sequence('subject', 'id'), COALESCE((SELECT MAX(id) FROM subject), 1), true);
SELECT setval(pg_get_serial_sequence('professor', 'id'), COALESCE((SELECT MAX(id) FROM professor), 1), true);
SELECT setval(pg_get_serial_sequence('admission_list', 'id'), COALESCE((SELECT MAX(id) FROM admission_list), 1), true);

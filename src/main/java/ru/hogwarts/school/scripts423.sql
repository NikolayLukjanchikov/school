SELECT student.name, student.age, faculty.name
FROM student
         INNER JOIN faculty ON faculty_id = faculty.id
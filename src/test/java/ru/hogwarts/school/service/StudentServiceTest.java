package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.*;
import static ru.hogwarts.school.constant.Constants.*;


class StudentServiceTest {
    private final StudentService out = new StudentService();

    @BeforeEach
    void createRepo() {
        out.addNew(STUDENT_1);
    }

    @Test
    void shouldReturnFacultyThenAddNewFaculty() {
        Student result = out.addNew(STUDENT_1);
        assertEquals(result, STUDENT_1);
    }

    @Test
    void shouldReturnFacultyThenEditFaculty() {
        Student result = out.updateStudent(STUDENT_2);
        assertEquals(result, STUDENT_2);
        assertNotEquals(result, STUDENT_1);
    }

    @Test
    void shouldReturnFacultyById() {
        Student result = out.getStudentById(ID_1);
        assertEquals(result, STUDENT_1);
    }

    @Test
    void shouldReturnDeletedFaculty() {
        Student result = out.deleteStudent(ID_1);
        assertEquals(result, STUDENT_1);
    }

    @Test
    void shouldReturnCollectionOfFacultyByColor() {
        out.addNew(STUDENT_1);
        out.addNew(STUDENT_1);
        out.addNew(STUDENT_1);
        assertEquals(out.getStudentsByAge(AGE_1), STUDENTS_BY_AGE);
    }

}
package ru.hogwarts.school.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hogwarts.school.model.Faculty;

import static org.junit.jupiter.api.Assertions.*;

import static ru.hogwarts.school.constant.Constants.*;

class FacultyServiceTest {
    private final FacultyService out = new FacultyService();


    @BeforeEach
    void createRepo() {
        out.addNewFaculty(FACULTY_1);
    }

    @Test
    void shouldReturnFacultyThenAddNewFaculty() {
        Faculty result = out.addNewFaculty(FACULTY_1);
        assertEquals(result, FACULTY_1);
    }

    @Test
    void shouldReturnFacultyThenEditFaculty() {
        Faculty result = out.updateFaculty(FACULTY_2);
        assertEquals(result, FACULTY_2);
        assertNotEquals(result, FACULTY_1);
    }

    @Test
    void shouldReturnFacultyById() {
        Faculty result = out.getFacultyById(ID_1);
        assertEquals(result, FACULTY_1);
    }

    @Test
    void shouldReturnDeletedFaculty() {
        Faculty result = out.deleteFaculty(ID_1);
        assertEquals(result, FACULTY_1);
    }

    @Test
    void shouldReturnCollectionOfFacultyByColor() {
        out.addNewFaculty(FACULTY_1);
        out.addNewFaculty(FACULTY_1);
        out.addNewFaculty(FACULTY_1);
        assertEquals(out.getFacultyByColor(COLOR_1), FC_BY_COLOR);
    }
}
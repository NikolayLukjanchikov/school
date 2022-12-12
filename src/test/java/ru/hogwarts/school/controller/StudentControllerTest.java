package ru.hogwarts.school.controller;

import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {
    @LocalServerPort
    private int testPort;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void contextLoads() throws Exception {
        assertThat(studentService).isNotNull();
    }

    @Test
    void createStudent() {
    }

    @Test
    public void shouldReturnStudentWhenAdd() throws Exception {
        Student st1 = new Student();
        st1.setId(1L);
        st1.setName("TestStudent");
        st1.setAge(16);
        assertThat(this.restTemplate.postForObject("http://localhost:" + testPort + "/student", st1, Student.class))
                .isNotNull();
    }
    @Test
    void readStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void getAllStudentsByAge() {
    }

    @Test
    void getAllStudentsByAgeBetween() {
    }

    @Test
    void getStudentsByFacultyId() {
    }
}
package ru.hogwarts.school.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    public void createStudent() throws Exception {
        Student st1 = new Student();
        st1.setId(1L);
        st1.setName("TestStudent");
        st1.setAge(16);
        assertThat(this.restTemplate.postForObject("http://localhost:" + testPort + "/student", st1, Student.class))
                .isNotNull();
    }

    @Test
    void readStudent() throws Exception {
        Student st1 = new Student();
        st1.setId(1L);
        st1.setName("TestStudent");
        st1.setAge(16);
        studentService.updateStudent(st1);

        assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/student/" + 1, Student.class))
                .isEqualTo(st1);
    }

    @Test
    void updateStudent() throws Exception {
        Student st1 = new Student();
        st1.setId(1L);
        st1.setName("TestStudent2");
        st1.setAge(18);
        assertThat(this.restTemplate.postForObject("http://localhost:" + testPort + "/student", st1, Student.class))
                .isEqualTo(st1);
    }

    @Test
    void deleteStudent() throws Exception {  //данный тест удаляет студента, который используется в первых тестах, что делает невозможным повторное ис-ие тестов update
        this.restTemplate.delete("http://localhost:" + testPort + "/student/" + 1);
        assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/student/" + 1, Student.class))
                .isNull();

    }

    @Test
    void getAllStudentsByAge() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/student/age?age=15", Collection.class))
                .isInstanceOf(Collection.class);
    }

    @Test
    void getAllStudentsByAgeBetween() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/student/age/between?min=10&max=15", Collection.class))
                .isInstanceOf(Collection.class);
    }

    @Test
    void getStudentsByFacultyId() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + testPort + "/student/by_faculty_id?id=12", Collection.class))
                .isInstanceOf(Collection.class);
    }
}
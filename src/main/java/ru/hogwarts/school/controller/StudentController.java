package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createFaculty(@RequestBody Student student) {
        return studentService.addNew(student);
    }

    @GetMapping("{id}")
    public Student readFaculty(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping
    public Student updateFaculty(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteFaculty(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("age")
    public Collection<Student> getAllStudentsByAge(@RequestParam int age) {
        return studentService.getStudentsByAge(age);
    }

    @GetMapping("age/between")
    public Collection<Student> getAllStudentsByAgeBetween(@RequestParam int min, int max) {
        return studentService.getStudentsByAgeBetween(min, max);
    }

}

package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

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

    @GetMapping({"id"})
    public Student readFaculty(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping
    public Student updateFaculty(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping({"id"})
    public Student deleteFaculty(@PathVariable long id) {
        return studentService.deleteStudent(id);
    }

    @GetMapping({"age"})
    public Collection<Student> getAllStudentsByAge(@PathVariable int age) {
        return studentService.getStudentsByAge(age);
    }

}

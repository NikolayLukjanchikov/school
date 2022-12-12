package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addNew(student);
    }

    @GetMapping("{id}")
    public Student readStudent(@PathVariable long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student foundStudent = studentService.updateStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
        avatarService.deleteAvatar(id);
        studentService.deleteStudent(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("age")
    public ResponseEntity<Collection<Student>> getAllStudentsByAge(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.getStudentsByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("age/between")
    public Collection<Student> getAllStudentsByAgeBetween(@RequestParam int min, int max) {
        return studentService.getStudentsByAgeBetween(min, max);
    }

    @GetMapping("by_faculty_id")
    public List<Student> getStudentsByFacultyId(@RequestParam Long id) {
        return studentService.getStudentsByFacultyId(id);
    }
}

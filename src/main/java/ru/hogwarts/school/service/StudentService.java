package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;

    }

    public Student addNew(Student student) {
        return studentRepository.save(student);
    }


    public Student getStudentById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByAge(int age) {
        return studentRepository.findAll().stream()
                .sorted(Comparator.comparing(Student::getAge))
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toUnmodifiableList());
    }

    public Collection<Student> getStudentsByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public List<Student> getStudentsByFacultyId(Long id) {
        return studentRepository.findDistinctStudentByFaculty_Id(id);
    }


}

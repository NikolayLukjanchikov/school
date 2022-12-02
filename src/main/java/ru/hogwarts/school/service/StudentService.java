package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    public Student addNew(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student getStudentById(long id) {
        return students.get(id);
    }

    public Student updateStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public Collection<Student> getStudentsByAge(int age) {
        return students.values().stream()
                .sorted(Comparator.comparing(Student::getAge))
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toUnmodifiableList());

    }

}

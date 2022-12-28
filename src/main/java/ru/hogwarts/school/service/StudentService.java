package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student addNew(Student student) {
        logger.info("Was invoked method for add new student");
        return studentRepository.save(student);
    }


    public Student getStudentById(long id) {
        logger.info("Was invoked method for find student by id");
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Student student) {
        logger.info("Was invoked method for update student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByAge(int age) {
        logger.info("Was invoked method for get students by age {}", age);
        return studentRepository.findAll().stream()
                .sorted(Comparator.comparing(Student::getAge))
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toUnmodifiableList());
    }

    public Collection<String> getStudentsByFirstLetter(String letter) {
        logger.info("Was invoked method for get students by first letter {}", letter);
        return studentRepository.findAll().stream()
                .sorted(Comparator.comparing(Student::getName))
                .filter(student -> student.getName().toLowerCase().startsWith(letter.toLowerCase()))
                .map(student -> student.getName().toUpperCase())
                .toList();
    }

    public Collection<Student> getStudentsByAgeBetween(int min, int max) {
        logger.info("Was invoked method to find students between age {} and {}", min, max);

        return studentRepository.findByAgeBetween(min, max);
    }

    public List<Student> getStudentsByFacultyId(Long id) {
        logger.info("Was invoked method to find all students in faculty");
        return studentRepository.findDistinctStudentByFaculty_Id(id);
    }

    public int getStudentsAmount() {
        logger.info("Was invoked method to get amount students ");
        return studentRepository.getStudentsAmount();
    }

    public int getStudentsAverageAge() {
        logger.info("Was invoked method to get avg students age");
        return studentRepository.getStudentsAverageAge();
    }

    public int getStudentsAverageAgeByStream() {
        logger.info("Was invoked method to get avgStream students age");
        return (int) studentRepository.findAll().stream()
                .mapToInt(a -> a.getAge())
                .average().orElse(0);
    }

    public List<Student> getLastFiveStudent() {
        logger.info("Was invoked method to find last five students");
        return studentRepository.getLastFiveStudent();
    }

}

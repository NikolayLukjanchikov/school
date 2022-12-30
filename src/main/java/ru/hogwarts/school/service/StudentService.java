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
    private static int print_count = 0;

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
                .mapToInt(Student::getAge)
                .average().orElse(0);
    }

    public List<Student> getLastFiveStudent() {
        logger.info("Was invoked method to find last five students");
        return studentRepository.getLastFiveStudent();
    }


    public void printAllStudentInDiffThreads() {
        logger.info("Was invoked method to find all students threads");
        print_count = 0;
        List<Student> students = studentRepository.findAll().stream().toList();
        System.out.println(students);
        System.out.println("теперь в потоках");

        printStud(students.get(0));
        printStud(students.get(1));
        new Thread(() -> {
            printStud(students.get(2));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            printStud(students.get(3));
        }).start();
        new Thread(() -> {
            printStud(students.get(4));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            printStud(students.get(5));
        }).start();

    }

    private void printStud(Student student) {
        System.out.println(student);
        ;

    }

}

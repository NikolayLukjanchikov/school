package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    List<Student> findDistinctStudentByFaculty_Id(Long id);

    @Query(value = "SELECT COUNT (*) FROM student", nativeQuery = true)
    int getStudentsAmount();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    int getStudentsAverageAge();

    @Query(value = "SELECT * FROM student ORDER BY id desc LIMIT 5" , nativeQuery = true)
    List<Student> getLastFiveStudent();

}

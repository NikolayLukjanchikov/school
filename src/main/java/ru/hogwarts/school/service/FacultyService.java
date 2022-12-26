package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    Logger logger = LoggerFactory.getLogger(Faculty.class);

    public Faculty addNewFaculty(Faculty faculty) {
        logger.info("Was invoked method for add new faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(long id) {
        logger.info("Was invoked method for find faculty by id");
        return facultyRepository.findById(id).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        logger.info("Was invoked method for find faculty by color: {}", color);
        return facultyRepository.findAll().stream()
                .sorted(Comparator.comparing(Faculty::getColor))
                .filter(student -> student.getColor().equals(color))
                .collect(Collectors.toUnmodifiableList());

    }

    public Collection<Faculty> getFacultyByColorOrName(String color, String name) {
        logger.info("Was invoked method for find faculty by color or name ");
        return facultyRepository.findFacultiesByColorIgnoreCaseOrNameContainingIgnoreCase(color, name);
    }

    public Faculty getFacultyByStudentId(Long id) {
        logger.info("Was invoked method for find faculty by student id");
        return facultyRepository.findDistinctFacultyByStudents_Id(id);
    }
}

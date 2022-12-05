package ru.hogwarts.school.service;

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

    public Faculty addNewFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return facultyRepository.findAll().stream()
                .sorted(Comparator.comparing(Faculty::getColor))
                .filter(student -> student.getColor().equals(color))
                .collect(Collectors.toUnmodifiableList());

    }

    public Collection<Faculty> getFacultyByColorOrName(String value) {
        return facultyRepository.findFacultiesByColorIgnoreCaseOrColorContainingIgnoreCase(value);
    }
}

package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    long lastId = 0;

    public Faculty addNewFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty getFacultyById(long id) {
        return faculties.get(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return faculties.values().stream()
                .sorted(Comparator.comparing(Faculty::getColor))
                .filter(student -> student.getColor().equals(color))
                .collect(Collectors.toUnmodifiableList());

    }
}

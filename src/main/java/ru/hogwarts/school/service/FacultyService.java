package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;
@Service
public class FacultyService {
    private final Map<Long, Faculty> facultets = new HashMap<>();
    long lastId = 0;

    public Faculty addNewFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        facultets.put(lastId, faculty);
        return faculty;
    }

    public Faculty getFacultyById(long id) {
        return facultets.get(id);
    }

    public Faculty updateFaculty(Faculty faculty) {
        facultets.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return facultets.remove(id);
    }
}

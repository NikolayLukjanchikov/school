package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;


@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addNewFaculty(faculty);
    }

    @GetMapping("{id}")
    public Faculty readFaculty(@PathVariable long id) {
        return facultyService.getFacultyById(id);
    }

    @PutMapping
    public Faculty updateFaculty(@RequestBody Faculty faculty) {
        return facultyService.updateFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("color")
    public Collection<Faculty> getFacultyByColor(@RequestParam String color) {
        return facultyService.getFacultyByColor(color);
    }

    @GetMapping("search")
    public Collection<Faculty> getFacultyByColorOrName(@RequestParam String color, String name) {
        return facultyService.getFacultyByColorOrName(color, name);
    }

    @GetMapping("by_student_id")
    public Faculty getFacultyByStudentId(Long id) {
        return facultyService.getFacultyByStudentId(id);
    }

    @GetMapping("/longestname")
    public String getLongestFacultyName() {
        return facultyService.getLongestFacultyName();
    }
}

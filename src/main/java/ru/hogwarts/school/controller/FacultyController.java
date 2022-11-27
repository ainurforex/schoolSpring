package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;


import java.util.Collection;


@RestController
@RequestMapping(path = "facultys")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }


    @GetMapping
    public ResponseEntity<Collection<Faculty>> getFacultys(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String color) {

        if (name != null && color != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.getFacultyByName(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.getFacultyByColor(color));
        }
        return ResponseEntity.ok(facultyService.getAllFacultys());
    }

    @GetMapping(path = "/findByNameAndColor/")
    public ResponseEntity<Collection<Faculty>> getFacultysByNameAndColor(@RequestParam(required = true) String name,
                                                                         @RequestParam(required = true) String color) {

        return ResponseEntity.ok(facultyService.getFacultyByNameAndColor(name, color));
    }

    @GetMapping(path = "/longestNameFaculty/")
    public ResponseEntity<String> getLongestNameFaculty() {
        String longestName=facultyService.getLongestNameFaculty();
        if (longestName==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(facultyService.getLongestNameFaculty());
    }
    @PostMapping
    public Faculty creatFaculty(@RequestBody Faculty faculty) {
        return facultyService.creatFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok(faculty);
    }

}

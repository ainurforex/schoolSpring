package ru.hogwarts.school.controller;

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

    @GetMapping(path = " {id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }


    @GetMapping
    public ResponseEntity<Collection<Faculty>> getFacultys(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String color) {
        if (name != null && color != null) {
            return ResponseEntity.badRequest().build();
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.getFacultyByName(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.getFacultyByColor(color));
        }
        return ResponseEntity.ok(facultyService.getAllFacultys());
    }


    @PostMapping
    public Faculty creatFaculty(@RequestBody Faculty faculty) {
        return facultyService.creatFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editStudent(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

}

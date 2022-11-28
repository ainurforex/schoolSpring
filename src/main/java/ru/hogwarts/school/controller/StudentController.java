package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.OptionalDouble;

@RestController
@RequestMapping(path = "students")


public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping(path = "/studentsBeginASorted")
    public ResponseEntity<Collection<Student>> getStudentsBeginASorted() {
        return ResponseEntity.ok(studentService.getStudentsBeginASorted());
    }

    @GetMapping(path = "avarageAgeOfStudents")
    public ResponseEntity<OptionalDouble> getAvarageAgeOfStudents() {
        return ResponseEntity.ok(studentService.avarageAgeOfStudents());
    }
    @GetMapping(path = "{studentId}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long studentId) {
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }


    @GetMapping(path = "/name/{name}")
    public ResponseEntity<Collection<Student>> getStudentByName(@PathVariable String name) {
        Collection<Student> students = studentService.getStudentByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping(path = "facultyId/{studentId}")
    public ResponseEntity<Long> getFacultyIdByStudentId(@PathVariable Long studentId) {
        Long facultyId = null;
        try {
            facultyId = studentService.getFacultyIdByStudentId(studentId);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok(facultyId);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getStudents(@RequestParam(required = false) Long facultyId) {
        if (facultyId != null) {
            return ResponseEntity.ok(studentService.getStudentsByFacultyId(facultyId));
        }
        return ResponseEntity.ok(studentService.getAllStudent());
    }


    @GetMapping(path = "byAge/{age}")
    public ResponseEntity<Collection<Student>> getStudentsByAge(@PathVariable int age) {
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }

    @GetMapping(path = "byAgeBetween")
    public ResponseEntity<Collection<Student>> getStudentsByAgeBetween(
            @RequestParam("min") int min,
            @RequestParam("max") int max) {
        return ResponseEntity.ok(studentService.getByAgeBetween(min, max));
    }

    @GetMapping(path = "numberOfStudents")
    public ResponseEntity<Integer> getNumberOfStudents() {
        return ResponseEntity.ok(studentService.getNumberOfStudents());
    }



    @GetMapping(path = "lastFiveStudentsById")
    public ResponseEntity<Collection<Student>> getLastFiveStudentsById() {
        return ResponseEntity.ok(studentService.getLastFiveStudentsById());
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @PostMapping
    public Student creatStudent(@RequestBody Student student) {
        return studentService.creatStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId) {
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok(student);
    }



}

package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping(path = "students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = " {studentId}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long studentId) {
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
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

    @GetMapping(path = "ByAgeBetween")
    public ResponseEntity<Collection<Student>> getStudentsByAgeBetween(
            @RequestParam("min") int min,
            @RequestParam("max") int max) {
        return ResponseEntity.ok(studentService.getByAgeBetween(min, max));
    }

    @PostMapping
    public Student creatStudent(@RequestBody Student student) {
        return studentService.creatStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId) {
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }


}

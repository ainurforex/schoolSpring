package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student creatStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id);
    }

    public Student editStudent(Student student) {

        if (studentRepository.findById(student.getId()) == null) {
            return null;
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }


    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentsByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public long getFacultyIdByStudentId(long id) {
        return studentRepository.findById(id).takeFacultyId();
    }

    public Collection<Student> getStudentsByFacultyId(long id) {
        return studentRepository.findByFaculty_Id(id);
    }


    public int getNumberOfStudents() {
        return studentRepository.getNumberOfStudents();
    }

    public int avarageAgeOfStudents() {
        return studentRepository.avarageAgeOfStudents();
    }

    public Collection<Student> getLastFiveStudentsById() {
        return studentRepository.getLastFiveStudentsById();
    }
}

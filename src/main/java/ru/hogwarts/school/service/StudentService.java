package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;



@Service
public class StudentService {

    private final StudentRepository studentRepository;

    Logger logger= LoggerFactory.getLogger(StudentService.class);
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student creatStudent(Student student) {
        logger.info("Was invoked method for creatStudent");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for findStudent");
        return studentRepository.findById(id);
    }

    public Collection<Student> getStudentByName(String name) {
        logger.info("Was invoked method for getStudentByName");
        return studentRepository.getStudentByName(name);
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for editStudent");
        if (studentRepository.findById(student.getId()) == null) {
            logger.error("There is no such student");
            return null;
        }
        logger.debug("Student id {} is edited",student.getId());
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for deleteStudent");
        studentRepository.deleteById(id);
    }


    public Collection<Student> getAllStudent() {
        logger.info("Was invoked method for getAllStudent");
        return studentRepository.findAll();
    }

    public Collection<Student> getStudentsByAge(int age) {
        logger.info("Was invoked method for getStudentsByAge");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> getByAgeBetween(int min, int max) {
        logger.info("Was invoked method for getByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public long getFacultyIdByStudentId(long id) {
        logger.info("Was invoked method for getFacultyIdByStudentId");
        return studentRepository.findById(id).takeFacultyId();
    }

    public Collection<Student> getStudentsByFacultyId(long id) {
        logger.info("Was invoked method for getStudentsByFacultyId");
        return studentRepository.findByFaculty_Id(id);
    }


    public int getNumberOfStudents() {
        logger.info("Was invoked method for getNumberOfStudents");
        return studentRepository.getNumberOfStudents();
    }

    public int avarageAgeOfStudents() {
        logger.info("Was invoked method for avarageAgeOfStudents");
        return studentRepository.avarageAgeOfStudents();
    }

    public Collection<Student> getLastFiveStudentsById() {
        logger.info("Was invoked method for getLastFiveStudentsById");
        return studentRepository.getLastFiveStudentsById();
    }
}

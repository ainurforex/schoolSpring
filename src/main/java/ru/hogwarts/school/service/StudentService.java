package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public static int count = 0;
    Object flag = new Object();
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Collection<Student> getStudentsBeginASorted() {
        logger.info("Was invoked method for getStudentsBeginASorted");
        Stream<Student> streamAllStudent = studentRepository.findAll().stream();
        return streamAllStudent
                .parallel()
                .filter(e -> e.getName().charAt(0) == 'A')
                .sorted((n1, n2) -> n1.getName().compareTo(n2.getName()))
                .collect(Collectors.toList());
    }

    public OptionalDouble avarageAgeOfStudents() {
        logger.info("Was invoked method for avarageAgeOfStudents");
        Stream<Student> streamAllStudent = studentRepository.findAll().stream();
        return streamAllStudent
                .parallel()
                .mapToInt(e -> e.getAge()).average();
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
        logger.debug("Student id {} is edited", student.getId());
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


    public Collection<Student> getLastFiveStudentsById() {
        logger.info("Was invoked method for getLastFiveStudentsById");
        return studentRepository.getLastFiveStudentsById();
    }

    public void threadTest() {

        count = 1;
        printStudentNameByIndex(0);
        printStudentNameByIndex(1);
        new Thread(() -> {
            printStudentNameByIndex(2);
            printStudentNameByIndex(3);
        }).start();

        new Thread(() -> {
            printStudentNameByIndex(4);
            printStudentNameByIndex(5);
        }).start();

    }

    public void threadSynchroTest() {
        count = 1;
        printStudentNameByIndexSynchro(0);
        printStudentNameByIndexSynchro(1);
        new Thread(() -> {
            printStudentNameByIndexSynchro(2);
            printStudentNameByIndexSynchro(3);
        }).start();

        new Thread(() -> {
            printStudentNameByIndexSynchro(4);
            printStudentNameByIndexSynchro(5);
        }).start();

    }

    private void printStudentNameByIndex(int index) {
        List<Student> students = studentRepository.findAll();
        String name = students.get(index).getName();

        System.out.println("Index " + (index + 1) + " "
                + "Student " + name
                + " Count " + count);
        count++;

    }

    private synchronized void printStudentNameByIndexSynchro(int index) {
        {

            List<Student> students = studentRepository.findAll();
            String name = students.get(index).getName();
            synchronized (flag) {
                System.out.println("Index " + (index + 1) + " "
                        + "Student " + name
                        + " Count " + count);
                count++;
            }

        }
    }
}

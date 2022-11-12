package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findByAge(int age);

    Student findById(long id);

    Collection<Student> findByFaculty_Id(long id);
    @Query(value = "SELECT COUNT(*) FROM student", nativeQuery = true)
    int getNumberOfStudents();
    @Query(value = "SELECT AVG(age) from student", nativeQuery = true)
    int avarageAgeOfStudents();

    @Query(value = "SELECT * from student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> getLastFiveStudentsById();
}

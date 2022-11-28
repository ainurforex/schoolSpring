package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.model.Faculty;


import java.util.Collection;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findFacultyByColorIgnoreCase(String color);

    Collection<Faculty> findFacultyByNameIgnoreCase(String name);

    @Transactional
    @Query(value = "SELECT name from faculty", nativeQuery = true)
    Collection<String> getFacultysName();


    Faculty findById(long id);

    @Transactional
    @Query(value = "SELECT * from faculty WHERE name LIKE :findName and color LIKE :findColor", nativeQuery = true)
    Collection<Faculty> getFacultyByNameAndColor(String findName, String findColor);


}

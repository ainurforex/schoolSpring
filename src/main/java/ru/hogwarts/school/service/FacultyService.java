package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.stream.Stream;


@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty creatFaculty(Faculty faculty) {
        logger.info("Was invoked method for creatFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method for findFaculty");
        return facultyRepository.findById(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for editFaculty");
        if (facultyRepository.findById(faculty.getId()) == null) {
            logger.error("There is no such faculty");
            return null;
        }
        logger.debug("Faculty id {} is edited", faculty.getId());
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for deleteFaculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFacultys() {
        logger.info("Was invoked method for getAllFacultys");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        logger.info("Was invoked method for getFacultyByColor");
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }

    public Collection<Faculty> getFacultyByName(String name) {
        logger.info("Was invoked method for getFacultyByName");
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }

    public Collection<Faculty> getFacultyByNameAndColor(String name, String color) {
        logger.info("Was invoked method for getFacultyByNameAndColor");
        return facultyRepository.getFacultyByNameAndColor(name, color);
    }


    public String getLongestNameFaculty() {
        logger.info("Was invoked method for getLongestNameFaculty");
        Stream<String> streamAllNameFacultys = facultyRepository.getFacultysName().stream();
        if (streamAllNameFacultys == null) {
            return null;
        }
        return streamAllNameFacultys
                .sorted((n1, n2) -> n2.length() - n1.length())
                .toList().get(0);
    }
}


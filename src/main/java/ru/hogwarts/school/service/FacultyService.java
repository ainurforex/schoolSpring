package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty creatFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        if (facultyRepository.findById(faculty.getId()).equals(Optional.empty())) {
            return null;
        }
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFacultys() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        return Collections.unmodifiableCollection(facultyRepository.findAll().stream()
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toList()));

    }
}


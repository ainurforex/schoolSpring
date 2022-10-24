package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private Map<Long, Faculty> facultys = new HashMap<>();
    private long lastId = 0;

    public Faculty creatFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        facultys.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultys.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (facultys.containsKey(faculty.getId())) {
            facultys.put(faculty.getId(),faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long id) {
        return facultys.remove(id);
    }

    public Collection<Faculty> getAllFacultys() {
        return facultys.values();
    }

    public Collection<Faculty>getFacultyByColor(String color) {
        return Collections.unmodifiableCollection(facultys.values().stream()
                .filter(s -> s.getColor().equals(color))
                .collect(Collectors.toList()));

    }
}


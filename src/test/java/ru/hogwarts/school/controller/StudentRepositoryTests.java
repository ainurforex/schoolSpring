package ru.hogwarts.school.controller;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentRepositoryTests {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentService studentService;
    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentService).isNotNull();
    }

    @Test
    public void getStudentInfoTest() throws Exception {
        String testedId = "1";
        Assertions
                .assertThat( restTemplate.getForObject("http://localhost:" + port
                        + "/students" + "/" + testedId, String.class))
                .isNotNull();
    }

    @Test
    public void getFacultyIdByStudentIdTest() throws Exception {
        String testedId = "1";
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port
                        + "/students" + "/facultyId" + "/" + testedId, String.class))
                .isNotNull();
        ;
    }


    @Test
    public void getStudentsTest() throws Exception {
        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port + "/students", String.class))
                .isNotNull();
        ;
    }

    @Test
    public void getStudentsByAgeTest() throws Exception {
        String testedAge = "40";

        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port
                        + "/students" + "/byAge" + "/" + testedAge, String.class))
                .isNotNull();
        ;
    }


    @Test
    public void getStudentsByAgeBetweenTest() throws Exception {
        String testedAgeMin = "1";
        String testedAgeMax = "40";

        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port
                        + "/students" + "/ByAgeBetween?min=" + testedAgeMin + "&max=" + testedAgeMax, String.class))
                .isNotNull();
        ;
    }

    @Test
    public void postEditDeleteStudent() throws Exception {
        Student student = new Student();
        student.setName("TestStudent");
        student.setAge(33);

        String responseEntityPost = restTemplate.postForObject("http://localhost:" + port
                + "/students", student, String.class);
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(responseEntityPost);
        m.find();
        long id = Long.parseLong(m.group());
        Assertions
                .assertThat(responseEntityPost)
                .isNotNull();


        Student editStudent = new Student();
        editStudent.setName("EditTestStudent");
        editStudent.setAge(55);

        restTemplate.put("http://localhost:" + port
                + "/students/" + id, editStudent, String.class);

        Assertions
                .assertThat(55)
                .isEqualTo(editStudent.getAge());

        restTemplate.delete("http://localhost:" + port
                + "/students/" + id, student, String.class);


        Assertions
                .assertThat(restTemplate.getForObject("http://localhost:" + port
                        + "/students" + "/" + id, String.class))
                .isNull();
    }
}

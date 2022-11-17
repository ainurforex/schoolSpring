CREATE TABLE car
(
    car_ID INTEGER     NOT NULL,
    mark   varchar(30) NOT NULL,
    model  varchar(30) NOT NULL,
    cost   INTEGER,
    CONSTRAINT car_primary_key PRIMARY KEY (car_ID)
)

CREATE TABLE human
(
    human_ID       INTEGER     NOT NULL,
    name           varchar(30) NOT NULL,
    age            INTEGER,
    car_ID         INTEGER,
    driversLicense BOOLEAN,
    FOREIGN KEY (car_ID) REFERENCES car (car_ID),
    CONSTRAINT human_primary_key PRIMARY KEY (human_ID)
)

SELECT student.name, student.age, faculty.name FROM student JOIN faculty ON student.faculty_id = faculty.id
SELECT student.name FROM student JOIN avatar ON avatar.student_id = student.id
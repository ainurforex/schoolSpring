-- liquibase formatted sql

-- changeset ainurforex:1
CREATE TABLE students(
    id SERIAL,
    name TEXT
)


-- changeset ainurforex:2
CREATE TABLE facultys(
                         id SERIAL,
                         name TEXT,
                         color TEXT
)

-- changeset ainurforex:3
CREATE INDEX students_name_index ON students(name);

-- changeset ainurforex:4
CREATE INDEX facultys_name_color_index ON facultys(name,color);
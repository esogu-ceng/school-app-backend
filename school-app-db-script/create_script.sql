CREATE DATABASE school_app;
USE school_app;

-- User table
CREATE TABLE "user" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    mail VARCHAR(30) UNIQUE,
    password VARCHAR(15) NOT NULL
);

-- Student table
CREATE TABLE student (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    surname VARCHAR(32) NOT NULL,
    grade INTEGER NOT NULL
);

-- Term table
CREATE TABLE term (
    id SERIAL PRIMARY KEY,
    term_name VARCHAR(30) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);


-- Payment table
CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    amount DOUBLE PRECISION NOT NULL,
    payment_date DATE NOT NULL
);

-- Installment table
CREATE TABLE installment (
    id SERIAL PRIMARY KEY,
    amount DOUBLE PRECISION NOT NULL,
    due_date DATE NOT NULL,
    term_id INTEGER NOT NULL,
    student_id INTEGER NOT NULL,
    payment_id INTEGER,
    FOREIGN KEY (term_id) REFERENCES term(id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (payment_id) REFERENCES payment(id)
);


-- student_relationships Join Table for ManyToMany relationship between User and Student
CREATE TABLE student_relationships (
    user_id INTEGER NOT NULL,
    student_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, student_id),
    FOREIGN KEY (user_id) REFERENCES "user"(id),
    FOREIGN KEY (student_id) REFERENCES student(id)
);

-- enrollments Join Table for ManyToMany relationship between Student and Term
CREATE TABLE enrollments (
    student_id INTEGER NOT NULL,
    term_id INTEGER NOT NULL,
    PRIMARY KEY (student_id, term_id),
    FOREIGN KEY (student_id) REFERENCES student(id),
    FOREIGN KEY (term_id) REFERENCES term(id)
);

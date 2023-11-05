ALTER TABLE student_relationships RENAME TO student_user;

CREATE TABLE settings
(
    id SERIAL PRIMARY KEY,
    key VARCHAR(50) NOT NULL UNIQUE,
    value VARCHAR(50),
    create_date TIMESTAMP WITH TIME ZONE,
    update_date TIMESTAMP WITH TIME ZONE
);
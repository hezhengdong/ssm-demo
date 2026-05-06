create database ssmlab_tyut;
use ssmlab_tyut;
CREATE TABLE students (
                          id int(11) NOT NULL AUTO_INCREMENT,
                          name varchar(20) NOT NULL,
                          age int(11) NOT NULL,
                          gender varchar(255) DEFAULT NULL,
                          number varchar(20) DEFAULT NULL,
                          address varchar(20) DEFAULT NULL,
                          status int(11) NOT NULL DEFAULT 1,
                          PRIMARY KEY (id)
);
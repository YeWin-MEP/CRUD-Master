database employee

CREATE TABLE employees (
    employee_id int NOT NULL AUTO_INCREMENT,
	password varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    employee_name varchar(100) NOT NULL,
    phone varchar(30),
    address varchar(150),
    remark varchar(255) ,
    PRIMARY KEY (employee_id)
);
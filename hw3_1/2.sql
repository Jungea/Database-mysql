create table if not exists EMP (ID int unsigned auto_increment primary key, LAST_NAME varchar(25), FIRST_NAME varchar(20), DEPT_ID int unsigned, FOREIGN KEY (DEPT_ID) REFERENCES DEPT(ID));
desc emp;
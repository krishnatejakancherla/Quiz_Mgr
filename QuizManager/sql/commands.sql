create table QUIZ(id bigint auto_increment, name varchar(255));

select ID, NAME from QUIZ


insert into QUIZ (name) values ('Java')
insert into QUIZ (name) values ('Python')
insert into QUIZ (name) values ('SQL')
insert into QUIZ (name) values ('SAP')
insert into QUIZ (name) values ('Angular')
insert into QUIZ (name) values ('Unix')


UPDATE QUIZ SET NAME='Quiz Test 2' WHERE ID = 1;
DELETE QUIZ WHERE ID = 1;


create table QUESTION(id number(20),  qid bigint auto_increment, content varchar(255),  topics varchar(255),  difficulty number(10), 
ANSWER varchar(255), CHOICEA varchar(255), CHOICEB varchar(255), CHOICEA varchar(255), CHOICEB varchar(255), CONSTRAINT id_fk
    FOREIGN KEY (id)
    REFERENCES QUIZ(id));
    
    
 create table ADMIN(UNAME VARCHAR(255), PASSWD VARCHAR(255))
 INSERT INTO ADMIN VALUES('ADM', 'ADM')
 
 create table CANDIDATE(UNAME VARCHAR(255), PASSWD VARCHAR(255))
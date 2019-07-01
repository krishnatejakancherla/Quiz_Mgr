create table QUIZ(id bigint auto_increment, name varchar(255));

select ID, NAME from QUIZ


insert into QUIZ (name) values ('Test Quiz')

UPDATE QUIZ SET NAME='Quiz Test 2' WHERE ID = 1;
DELETE QUIZ  WHERE ID = 1;


create table QUESTION(id number(20),  qid bigint auto_increment, content varchar(255),  topics varchar(255),  difficulty number(10), CONSTRAINT id_fk
    FOREIGN KEY (id)
    REFERENCES QUIZ(id));
CREATE TABLE Hospital_Schedule(
	seq NUMBER(10) PRIMARY KEY,
	user_id VARCHAR2(15) not null,
	diction VARCHAR2(20) not null,
	doctor_id VARCHAR2(15) not null,
	preorder_date DATE not null,
	isend number(1) not null,
	del number(1) not null
);

create sequence Hospital_Schedule_SEQ
start with 1 increment by 1;

ALTER TABLE Hospital_Schedule
ADD CONSTRAINT Hospital_Schedule_USER_ID_FK FOREIGN KEY(USER_ID)
REFERENCES MEMBER(ID)
ADD CONSTRAINT Hospital_Schedule_doctor_id_FK FOREIGN KEY(doctor_id)
REFERENCES MEMBER(ID);

ALTER TABLE Hospital_Schedule
modify(user_id VARCHAR2(15))
ALTER TABLE Hospital_Schedule
modify(doctor_id VARCHAR2(15))

INSERT INTO Hospital_Schedule(seq,user_id,diction,doctor_id,preorder_date,isend,del)
VALUES(Hospital_Schedule_SEQ.NEXTVAL,'1234','필러','doct1',SYSDATE,0,0);
INSERT INTO Hospital_Schedule(seq,user_id,diction,doctor_id,preorder_date,isend,del)
VALUES(Hospital_Schedule_SEQ.NEXTVAL,'1234','보톡스','doct1',SYSDATE,0,0);
INSERT INTO Hospital_Schedule(seq,user_id,diction,doctor_id,preorder_date,isend,del)
VALUES(Hospital_Schedule_SEQ.NEXTVAL,'1234','보톡스','doct2',SYSDATE,0,0);

INSERT INTO Hospital_Schedule(seq,user_id,diction,doctor_id,preorder_date,isend,del)
VALUES(Hospital_Schedule_SEQ.NEXTVAL,'1234','보톡스','doct2',TO_DATE('2017-05-10 15', 'YYYY-MM-DD HH24'),1,0);

INSERT INTO Hospital_Schedule(seq,user_id,diction,doctor_id,preorder_date,isend,del)
VALUES(Hospital_Schedule_SEQ.NEXTVAL,'1234','보톡스','doct2',SYSDATE,1,0);

DROP TABLE Hospital_Schedule;

alter table Hospital_Schedule
add(del number(1));

alter table Hospital_Schedule 
modify del not null;

update Hospital_Schedule SET del=0

delete from hospital_Schedule;



SELECT * FROM Hospital_Schedule;